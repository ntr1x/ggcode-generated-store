import { eventsRemote } from '../remotes/eventsRemote'

export type RequestSelectEventSourcePageProps = {
  token: string
  query?: Record<string, any>
  payload?: Record<string, any>
}
export type RequestSelectEventSourcePageResponse = any

export const selectEventSourcePageRequest = async (props: RequestSelectEventSourcePageProps): Promise<RequestSelectEventSourcePageResponse> => {

  const { data } = await eventsRemote.request<RequestSelectEventSourcePageResponse>({
    method: `POST`,
    url: `/system/public_event_source_info/select`,
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
