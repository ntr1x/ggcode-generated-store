import { onMounted, reactive } from "vue"
import type { AxiosInstance, AxiosRequestConfig } from "axios"

export type AxiosRequestConfigFactory = () => Promise<AxiosRequestConfig>

export type State<T> = {
  isLoading: false,
  isLoaded: false,
  isFailed: false,
  data?: T
}

export function useAxiosRequest<T>(instance: AxiosInstance, requestFactory: AxiosRequestConfigFactory, manual: boolean = false) {
  const state = reactive<State<T>>({
    isLoading: false,
    isLoaded: false,
    isFailed: false,
  })

  const refresh = async () => {
    try {
      Object.assign(state, {
        isLoading: true,
        isFailed: false
      })
      const request = await requestFactory()
      const { data } = await instance.request(request)
      Object.assign(state, {
        isLoading: false,
        isFailed: false,
        isLoaded: true,
        data: data as undefined as T
      })
    } catch {
      Object.assign(state, {
        isLoading: false,
        isFailed: true
      })
    }
    return state
  }

  if (!manual) {
    onMounted(refresh)
  }

  return { state, refresh }
}
