import { eventsRemote } from '../remotes/eventsRemote'

export type RequestSelectEventTypePageProps = {
  token: string
  query?: Record<string, any>
  payload?: Record<string, any>
}
export type RequestSelectEventTypePageResponse = any

export const selectEventTypePageRequest = async (props: RequestSelectEventTypePageProps): Promise<RequestSelectEventTypePageResponse> => {

  const { data } = await eventsRemote.request<RequestSelectEventTypePageResponse>({
    method: `POST`,
    url: `/system/public_event_type_info/select`,
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
