import { eventsRemote } from '../remotes/eventsRemote'

export type RequestSelectDispatchStatusPageProps = {
  token: string
  payload: Record<string, any>
}

export const selectDispatchStatusPageRequest = async (props: RequestSelectDispatchStatusPageProps) => {

  const { data } = await eventsRemote.request({
    method: `POST`,
    url: `/system/public_dispatch_status/select`,
    data: Object.assign({}, props.payload),
    params: {},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
