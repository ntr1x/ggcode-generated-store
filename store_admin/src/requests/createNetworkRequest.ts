import { structureRemote } from '../remotes/structureRemote'

export type RequestCreateNetworkProps = {
  token: string
  payload: Record<string, any>
}
export type RequestCreateNetworkResponse = any

export const createNetworkRequest = async (props: RequestCreateNetworkProps): Promise<RequestCreateNetworkResponse> => {

  const { data } = await structureRemote.request<RequestCreateNetworkResponse>({
    method: `POST`,
    url: `/system/public_network`,
    data: Object.assign({}, props.payload),
    params: {},
    paramsSerializer: {
      indexes: null
    },
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
