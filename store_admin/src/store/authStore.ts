import { Ref } from "vue"
import { defineStore } from "pinia"
import { StorageSerializers, useStorage } from "@vueuse/core"
import { securityRemote } from "../remotes/securityRemote"


export type Principal = {
  issuer: string
  subject: string
  username: string
  fullName: string
  email: string
  token: string
  authorities: string[]
}

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
  principal: Principal
}

export type SignInRequest = {
  username: string
  password: string
}

export type SignInResponse = {
  accessToken: string
  accessExpiresIn: number
  refreshToken: string
  refreshExpiresIn: number
  principal: Principal
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
  principal: Principal
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
  principal: Principal | null
  accessToken: TokenInfo | null
  refreshToken: Ref<TokenInfo | null>
}

export const useAuthStore = defineStore('auth', {
  state: (): AuthState => ({
    principal: null,
    accessToken: null,
    refreshToken: useStorage('pinia/auth/refresh', null, localStorage || sessionStorage, { serializer: StorageSerializers.object })
  }),
  actions: {
    async requireAuth() {
      const now = Date.now()

      if (this.accessToken != null && this.accessToken.expiresAt != null) {
        if (now < this.accessToken.expiresAt - this.accessToken.duration) {
          return {
            principal: this.principal,
            accessToken: this.accessToken,
            refreshToken: this.refreshToken,
          }
        }
      }

      if (this.refreshToken != null && this.refreshToken.expiresAt != null) {
        if (now < this.refreshToken.expiresAt - this.refreshToken.duration) {
          await this.doRefresh()
          return {
            principal: this.principal,
            accessToken: this.accessToken,
            refreshToken: this.refreshToken,
          }
        }
      }

      await this.doAuth()

      throw new Error('Not authorized')
    },
    async requireToken() {
      const { accessToken } = await this.requireAuth()
      return accessToken!.token
    },
    async doRefresh() {
      const now = Date.now()

      const { data } = await securityRemote.post<RefreshResponse>('/public/security/refresh', {
        refreshToken: this.refreshToken!.token,
      })

      this.principal = data.principal

      this.accessToken = {
        token: data.accessToken,
        expiresIn: data.accessExpiresIn,
        expiresAt: now + data.accessExpiresIn * 1000,
        duration: 10 * 1000,
      }

      this.refreshToken = {
        token: data.refreshToken,
        expiresIn: data.refreshExpiresIn,
        expiresAt: now + data.refreshExpiresIn * 1000,
        duration: 10 * 1000
      }
    },
    async doSignIn(request: SignInRequest) {
      const now = Date.now()

      const { data } = await securityRemote.post<SignInResponse>('/public/security/signIn', request)

      this.principal = data.principal

      this.accessToken = {
        token: data.accessToken,
        expiresIn: data.accessExpiresIn,
        expiresAt: now + data.accessExpiresIn * 1000,
        duration: 10 * 1000,
      }

      this.refreshToken = {
        token: data.refreshToken,
        expiresIn: data.refreshExpiresIn,
        expiresAt: now + data.refreshExpiresIn * 1000,
        duration: 10 * 1000
      }
    },
    async doAuth(provider?: string, state?: string) {
      const redirectUri = new URL('/auth/callback', window.location.origin)
      const { data } = await securityRemote.post<AuthResponse>('/public/security/auth', {
        state: state != null
          ? state
          : this.$router.currentRoute.value.fullPath,
        redirectUri: redirectUri.toString(),
        provider
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

      this.principal = data.principal

      this.accessToken = {
        token: data.accessToken,
        expiresIn: data.accessExpiresIn,
        expiresAt: now + data.accessExpiresIn * 1000,
        duration: 10 * 1000,
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
