import { eventsRemote } from '../remotes/eventsRemote'

export type RequestSelectEventSourcePageProps = {
  token: string
  payload: Record<string, any>
}

export const selectEventSourcePageRequest = async (props: RequestSelectEventSourcePageProps) => {

  const { data } = await eventsRemote.request({
    method: `POST`,
    url: `/system/public_event_source_info/select`,
    data: Object.assign({}, props.payload),
    params: {},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
