import { paymentsRemote } from '../remotes/paymentsRemote'

export type RequestSelectPaymentStatusPageProps = {
  token: string
  query?: Record<string, any>
  payload?: Record<string, any>
}
export type RequestSelectPaymentStatusPageResponse = any

export const selectPaymentStatusPageRequest = async (props: RequestSelectPaymentStatusPageProps): Promise<RequestSelectPaymentStatusPageResponse> => {

  const { data } = await paymentsRemote.request<RequestSelectPaymentStatusPageResponse>({
    method: `POST`,
    url: `/system/public_payment_status/select`,
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
