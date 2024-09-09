import { paymentsRemote } from '../remotes/paymentsRemote'

export type RequestGetOrderProps = {
  token: string
  orderId: string
}

export const getOrderRequest = async (props: RequestGetOrderProps) => {

  const { data } = await paymentsRemote.request({
    method: `GET`,
    url: `/system/public_order/i/${props.orderId}`,
    params: {},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
