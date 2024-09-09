import { eventsRemote } from '../remotes/eventsRemote'

export type RequestSelectEventTopicPageProps = {
  token: string
  payload: Record<string, any>
}

export const selectEventTopicPageRequest = async (props: RequestSelectEventTopicPageProps) => {

  const { data } = await eventsRemote.request({
    method: `POST`,
    url: `/system/public_event_topic_info/select`,
    data: Object.assign({}, props.payload),
    params: {},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
