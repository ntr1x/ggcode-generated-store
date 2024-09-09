import { eventsRemote } from '../remotes/eventsRemote'

export type RequestSelectDispatchTypePageProps = {
  token: string
  payload: Record<string, any>
}

export const selectDispatchTypePageRequest = async (props: RequestSelectDispatchTypePageProps) => {

  const { data } = await eventsRemote.request({
    method: `POST`,
    url: `/system/public_dispatch_type/select`,
    data: Object.assign({}, props.payload),
    params: {},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
