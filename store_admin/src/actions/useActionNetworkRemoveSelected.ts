import { reactive } from "vue";
import { useAuthStore } from "../store/authStore";
import { structureRemote } from '../remotes/structureRemote'

export function useActionNetworkRemoveSelected() {
  const authStore = useAuthStore()

  const state = reactive({
    isLoading: false,
    isLoaded: false,
    isFailed: false,
  })

  const execute = async (payload?: any) => {
    const token = await authStore.requireToken()
    try {
      Object.assign(state, {
        isLoading: true,
        isFailed: false
      })
      const { data } = await structureRemote.request({
        method: 'POST',
        url: '/system/public_network/removeAll',
        data: Object.assign([], payload),
        params: {},
        headers: {
          Authorization: `Bearer ${token}`
        }
      })
      Object.assign(state, {
        isLoading: false,
        isFailed: false,
        isLoaded: true,
      })
      return data
    } catch (e) {
      Object.assign(state, {
        isLoading: false,
        isFailed: true
      })
      throw e
    }
  }

  return { execute, state }
}
