import type { AxiosInstance, AxiosRequestConfig } from "axios"
import { useAxiosRequest } from "./useAxiosRequest"

export type AxiosRequestConfigFactory = () => Promise<AxiosRequestConfig>

export function useAxiosManualRequest<T>(instance: AxiosInstance, requestFactory: AxiosRequestConfigFactory) {
  return useAxiosRequest<T>(instance, requestFactory, { auto: false })
}
