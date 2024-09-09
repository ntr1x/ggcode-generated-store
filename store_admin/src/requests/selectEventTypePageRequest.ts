import { eventsRemote } from '../remotes/eventsRemote'

export type RequestSelectEventTypePageProps = {
  token: string
  payload: Record<string, any>
}

export const selectEventTypePageRequest = async (props: RequestSelectEventTypePageProps) => {

  const { data } = await eventsRemote.request({
    method: `POST`,
    url: `/system/public_event_type_info/select`,
    data: Object.assign({}, props.payload),
    params: {},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
