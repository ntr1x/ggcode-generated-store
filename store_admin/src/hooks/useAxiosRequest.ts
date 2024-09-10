import { onMounted, reactive } from "vue"

export type State<T> = {
  isLoading: false,
  isLoaded: false,
  isFailed: false,
  data?: T
}

export type Config = {
  auto: boolean
}

export function useAxiosRequest<T>(request: () => Promise<T>, config: Config) {
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
      const data = await request()
      Object.assign(state, {
        isLoading: false,
        isFailed: false,
        isLoaded: true,
        data,
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
