import { eventsRemote } from '../remotes/eventsRemote'

export type RequestSelectEventPageProps = {
  token: string
  payload: Record<string, any>
}

export const selectEventPageRequest = async (props: RequestSelectEventPageProps) => {

  const { data } = await eventsRemote.request({
    method: `POST`,
    url: `/system/public_event/select`,
    data: Object.assign({}, props.payload),
    params: {"size":50,"sort":"createdAt,desc"},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
