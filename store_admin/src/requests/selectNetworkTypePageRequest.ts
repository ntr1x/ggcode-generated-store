import { structureRemote } from '../remotes/structureRemote'

export type RequestSelectNetworkTypePageProps = {
  token: string
  query?: Record<string, any>
  payload?: Record<string, any>
}
export type RequestSelectNetworkTypePageResponse = any

export const selectNetworkTypePageRequest = async (props: RequestSelectNetworkTypePageProps): Promise<RequestSelectNetworkTypePageResponse> => {

  const { data } = await structureRemote.request<RequestSelectNetworkTypePageResponse>({
    method: `POST`,
    url: `/system/public_network_type/select`,
    data: Object.assign({}, props.payload),
    params: Object.assign({}, props.query),
    paramsSerializer: {
      indexes: null
    },
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
