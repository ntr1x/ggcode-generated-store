import { productsRemote } from '../remotes/productsRemote'

export type RequestSelectRegionPageProps = {
  token: string
  query?: Record<string, any>
  payload?: Record<string, any>
}
export type RequestSelectRegionPageResponse = any

export const selectRegionPageRequest = async (props: RequestSelectRegionPageProps): Promise<RequestSelectRegionPageResponse> => {

  const { data } = await productsRemote.request<RequestSelectRegionPageResponse>({
    method: `POST`,
    url: `/system/public_region/select`,
    data: Object.assign({}, props.payload),
    params: Object.assign({"size":50,"sort":"name,asc"}, props.query),
    paramsSerializer: {
      indexes: null
    },
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
