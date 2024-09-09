import { eventsRemote } from '../remotes/eventsRemote'

export type RequestSelectSubscriptionPageProps = {
  token: string
  payload: Record<string, any>
}

export const selectSubscriptionPageRequest = async (props: RequestSelectSubscriptionPageProps) => {

  const { data } = await eventsRemote.request({
    method: `POST`,
    url: `/system/public_subscription/select`,
    data: Object.assign({}, props.payload),
    params: {},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
