import { useAxiosRequest } from "./useAxiosRequest"

export function useAxiosAutoRequest<T>(request: () => Promise<T>) {
  return useAxiosRequest<T>(request, { auto: true })
}
