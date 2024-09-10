import { eventsRemote } from '../remotes/eventsRemote'

export type RequestCreateDispatchProps = {
  token: string
  payload: Record<string, any>
}
export type RequestCreateDispatchResponse = any

export const createDispatchRequest = async (props: RequestCreateDispatchProps): Promise<RequestCreateDispatchResponse> => {

  const { data } = await eventsRemote.request<RequestCreateDispatchResponse>({
    method: `POST`,
    url: `/system/public_dispatch`,
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
