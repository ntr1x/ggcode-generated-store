import { eventsRemote } from '../remotes/eventsRemote'

export type RequestSelectSubscriptionPageProps = {
  token: string
  query?: Record<string, any>
  payload?: Record<string, any>
}
export type RequestSelectSubscriptionPageResponse = any

export const selectSubscriptionPageRequest = async (props: RequestSelectSubscriptionPageProps): Promise<RequestSelectSubscriptionPageResponse> => {

  const { data } = await eventsRemote.request<RequestSelectSubscriptionPageResponse>({
    method: `POST`,
    url: `/system/public_subscription/select`,
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
