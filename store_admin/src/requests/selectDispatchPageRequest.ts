import { eventsRemote } from '../remotes/eventsRemote'

export type RequestSelectDispatchPageProps = {
  token: string
  payload: Record<string, any>
}

export const selectDispatchPageRequest = async (props: RequestSelectDispatchPageProps) => {

  const { data } = await eventsRemote.request({
    method: `POST`,
    url: `/system/public_dispatch/select`,
    data: Object.assign({}, props.payload),
    params: {"size":50,"sort":"createdAt,desc"},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
