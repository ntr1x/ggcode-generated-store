import { defineStore } from "pinia"
import { securityRemote } from "../remotes/securityRemote"
import { useAuthStore } from "./authStore"

export type PrincipalRequest = {
}

export type PrincipalResponse = {
  issuer: string
  subject: string
  username: string
  fullName: string
  email: string
  token: string
  authorities: string[]
}

export const useProfileStore = defineStore('profile', {
  actions: {
    async fetchMe() {
      const authStore = useAuthStore()
      const accessToken = await authStore.requireToken()
      const { data } = await securityRemote.get<PrincipalResponse>('/security/me/principal', {
        headers: {
          Authorization: `Bearer ${accessToken}`
        }
      })
      return data
    },
  }
})
