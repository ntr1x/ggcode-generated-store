import { useAxiosRequest } from "./useAxiosRequest"

export function useAxiosManualRequest<T>(request: () => Promise<T>) {
  return useAxiosRequest<T>(request, { auto: false })
}
