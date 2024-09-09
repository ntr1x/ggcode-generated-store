import { paymentsRemote } from '../remotes/paymentsRemote'

export type RequestSelectPaymentPageProps = {
  token: string
  payload: Record<string, any>
}

export const selectPaymentPageRequest = async (props: RequestSelectPaymentPageProps) => {

  const { data } = await paymentsRemote.request({
    method: `POST`,
    url: `/system/public_payment/select`,
    data: Object.assign({}, props.payload),
    params: {"size":50,"sort":"id,asc"},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
