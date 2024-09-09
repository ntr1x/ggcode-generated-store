import { eventsRemote } from '../remotes/eventsRemote'

export type RequestSelectSubscriptionTypePageProps = {
  token: string
  payload: Record<string, any>
}

export const selectSubscriptionTypePageRequest = async (props: RequestSelectSubscriptionTypePageProps) => {

  const { data } = await eventsRemote.request({
    method: `POST`,
    url: `/system/public_subscription_type/select`,
    data: Object.assign({}, props.payload),
    params: {},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
