import { eventsRemote } from '../remotes/eventsRemote'

export type RequestSelectEventTopicPageProps = {
  token: string
  query?: Record<string, any>
  payload?: Record<string, any>
}
export type RequestSelectEventTopicPageResponse = any

export const selectEventTopicPageRequest = async (props: RequestSelectEventTopicPageProps): Promise<RequestSelectEventTopicPageResponse> => {

  const { data } = await eventsRemote.request<RequestSelectEventTopicPageResponse>({
    method: `POST`,
    url: `/system/public_event_topic_info/select`,
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
