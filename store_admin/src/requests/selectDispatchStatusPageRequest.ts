import { eventsRemote } from '../remotes/eventsRemote'

export type RequestSelectDispatchStatusPageProps = {
  token: string
  query?: Record<string, any>
  payload?: Record<string, any>
}
export type RequestSelectDispatchStatusPageResponse = any

export const selectDispatchStatusPageRequest = async (props: RequestSelectDispatchStatusPageProps): Promise<RequestSelectDispatchStatusPageResponse> => {

  const { data } = await eventsRemote.request<RequestSelectDispatchStatusPageResponse>({
    method: `POST`,
    url: `/system/public_dispatch_status/select`,
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
