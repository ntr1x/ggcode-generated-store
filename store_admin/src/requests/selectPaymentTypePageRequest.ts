import { paymentsRemote } from '../remotes/paymentsRemote'

export type RequestSelectPaymentTypePageProps = {
  token: string
  query?: Record<string, any>
  payload?: Record<string, any>
}
export type RequestSelectPaymentTypePageResponse = any

export const selectPaymentTypePageRequest = async (props: RequestSelectPaymentTypePageProps): Promise<RequestSelectPaymentTypePageResponse> => {

  const { data } = await paymentsRemote.request<RequestSelectPaymentTypePageResponse>({
    method: `POST`,
    url: `/system/public_payment_type/select`,
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
