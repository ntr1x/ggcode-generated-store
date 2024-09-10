import { eventsRemote } from '../remotes/eventsRemote'

export type RequestSelectDispatchTypePageProps = {
  token: string
  query?: Record<string, any>
  payload?: Record<string, any>
}
export type RequestSelectDispatchTypePageResponse = any

export const selectDispatchTypePageRequest = async (props: RequestSelectDispatchTypePageProps): Promise<RequestSelectDispatchTypePageResponse> => {

  const { data } = await eventsRemote.request<RequestSelectDispatchTypePageResponse>({
    method: `POST`,
    url: `/system/public_dispatch_type/select`,
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
