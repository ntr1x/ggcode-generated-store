import { eventsRemote } from '../remotes/eventsRemote'

export type RequestSelectSubscriptionTypePageProps = {
  token: string
  query?: Record<string, any>
  payload?: Record<string, any>
}
export type RequestSelectSubscriptionTypePageResponse = any

export const selectSubscriptionTypePageRequest = async (props: RequestSelectSubscriptionTypePageProps): Promise<RequestSelectSubscriptionTypePageResponse> => {

  const { data } = await eventsRemote.request<RequestSelectSubscriptionTypePageResponse>({
    method: `POST`,
    url: `/system/public_subscription_type/select`,
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
