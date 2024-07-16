import { onMounted, reactive } from "vue"
import type { AxiosInstance, AxiosRequestConfig } from "axios"

export type AxiosRequestConfigFactory = () => Promise<AxiosRequestConfig>

export type State<T> = {
  isLoading: false,
  isLoaded: false,
  isFailed: false,
  data?: T
}

export type Config = {
  auto: boolean
}

export function useAxiosRequest<T>(instance: AxiosInstance, requestFactory: AxiosRequestConfigFactory, config: Config) {
  const state = reactive<State<T>>({
    isLoading: false,
    isLoaded: false,
    isFailed: false,
  })

  const execute = async () => {
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

  if (config.auto) {
    onMounted(execute)
  }

  return { state, execute, refresh: execute }
}

export function useAxiosAutoRequest<T>(instance: AxiosInstance, requestFactory: AxiosRequestConfigFactory) {
  return useAxiosRequest<T>(instance, requestFactory, { auto: true })
}

export function useAxiosManualRequest<T>(instance: AxiosInstance, requestFactory: AxiosRequestConfigFactory) {
  return useAxiosRequest<T>(instance, requestFactory, { auto: false })
}
