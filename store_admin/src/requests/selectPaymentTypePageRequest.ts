import { paymentsRemote } from '../remotes/paymentsRemote'

export type RequestSelectPaymentTypePageProps = {
  token: string
  payload: Record<string, any>
}

export const selectPaymentTypePageRequest = async (props: RequestSelectPaymentTypePageProps) => {

  const { data } = await paymentsRemote.request({
    method: `POST`,
    url: `/system/public_payment_type/select`,
    data: Object.assign({}, props.payload),
    params: {},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
