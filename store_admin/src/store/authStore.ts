import { defineStore } from "pinia"
import { securityRemote } from "../remotes/securityRemote"
import { StorageSerializers, useStorage } from "@vueuse/core"
import { Ref } from "vue"

export type AuthRequest = {
  state: string | null
  redirectUri: string | null
}

export type AuthResponse = {
  authUri: string
}

export type RefreshRequest = {
  refreshToken: string
}

export type RefreshResponse = {
  accessToken: string
  accessExpiresIn: number
  refreshToken: string
  refreshExpiresIn: number
}

export type CallbackRequest = {
  state: string
  code: string
  redirectUri: string
  sessionState: string
}

export type CallbackResponse = {
  accessToken: string
  accessExpiresIn: number
  refreshToken: string
  refreshExpiresIn: number
}

export type RevokeRequest = {
  accessToken: string
  refreshToken: string
}

export type RevokeResponse = {
  accessTokenRevoked: string
  refreshTokenRevoked: number
}

export type TokenInfo = {
  token: string
  expiresIn: number
  expiresAt: number
  duration: number
}

export type AuthState = {
  accessToken: TokenInfo | null,
  refreshToken: Ref<TokenInfo | null>,
}

export const useAuthStore = defineStore('auth', {
  state: (): AuthState => ({
    accessToken: null,
    refreshToken: useStorage('pinia/auth/refresh', null, localStorage || sessionStorage, { serializer: StorageSerializers.object })
  }),
  actions: {
    async requireToken() {
      const now = Date.now()

      if (this.accessToken != null && this.accessToken.expiresAt != null) {
        if (now < this.accessToken.expiresAt - this.accessToken.duration) {
          return this.accessToken.token
        }
      }

      if (this.refreshToken != null && this.refreshToken.expiresAt != null) {
        if (now < this.refreshToken.expiresAt - this.refreshToken.duration) {
          await this.doRefresh()
          return this.accessToken!.token
        }
      }

      await this.doAuth()

      throw new Error('Not authorized')
    },
    async doRefresh() {
      const now = Date.now()
      const { data } = await securityRemote.post<RefreshResponse>('/public/security/refresh', {
        refreshToken: this.refreshToken!.token,
      })

      this.accessToken = {
        token: data.accessToken,
        expiresIn: data.accessExpiresIn,
        expiresAt: now + data.accessExpiresIn * 1000,
        duration: 10 * 1000
      }

      this.refreshToken = {
        token: data.refreshToken,
        expiresIn: data.refreshExpiresIn,
        expiresAt: now + data.refreshExpiresIn * 1000,
        duration: 10 * 1000
      }
    },
    async doAuth() {
      const redirectUri = new URL('/auth/callback', window.location.origin)
      const { data } = await securityRemote.post<AuthResponse>('/public/security/auth', {
        state: this.$router.currentRoute.value.fullPath,
        redirectUri: redirectUri.toString(),
      })

      window.location.href = data.authUri
    },
    async doLogout() {
      await securityRemote.post<RevokeRequest, RevokeResponse>('/public/security/revoke', {
        accessToken: this.accessToken?.token,
        refreshToken: this.refreshToken?.token,
      })

      this.accessToken = null
      this.refreshToken = null

      await this.$router.push('/')
    },
    async doCallback(request: Omit<CallbackRequest, "redirectUri">) {
      const now = Date.now()
      const redirectUri = new URL('/auth/callback', window.location.origin)
      const { data } = await securityRemote.post<CallbackResponse>('/public/security/callback', {
        ...request,
        redirectUri: redirectUri.toString()
      })

      this.accessToken = {
        token: data.accessToken,
        expiresIn: data.accessExpiresIn,
        expiresAt: now + data.accessExpiresIn * 1000,
        duration: 10 * 1000
      }

      this.refreshToken = {
        token: data.refreshToken,
        expiresIn: data.refreshExpiresIn,
        expiresAt: now + data.refreshExpiresIn * 1000,
        duration: 10 * 1000
      }
    }
  }
})
