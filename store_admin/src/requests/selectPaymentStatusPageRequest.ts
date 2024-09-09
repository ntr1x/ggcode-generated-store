import { paymentsRemote } from '../remotes/paymentsRemote'

export type RequestSelectPaymentStatusPageProps = {
  token: string
  payload: Record<string, any>
}

export const selectPaymentStatusPageRequest = async (props: RequestSelectPaymentStatusPageProps) => {

  const { data } = await paymentsRemote.request({
    method: `POST`,
    url: `/system/public_payment_status/select`,
    data: Object.assign({}, props.payload),
    params: {},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
