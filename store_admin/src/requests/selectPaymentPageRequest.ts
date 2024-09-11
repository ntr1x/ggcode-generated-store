import { paymentsRemote } from '../remotes/paymentsRemote'

export type RequestSelectPaymentPageProps = {
  token: string
  query?: Record<string, any>
  payload?: Record<string, any>
}
export type RequestSelectPaymentPageResponse = any

export const selectPaymentPageRequest = async (props: RequestSelectPaymentPageProps): Promise<RequestSelectPaymentPageResponse> => {

  const { data } = await paymentsRemote.request<RequestSelectPaymentPageResponse>({
    method: `POST`,
    url: `/system/public_payment/select`,
    data: Object.assign({}, props.payload),
    params: Object.assign({"size":50,"sort":"id,asc"}, props.query),
    paramsSerializer: {
      indexes: null
    },
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
