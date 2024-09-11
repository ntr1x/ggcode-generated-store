import { paymentsRemote } from '../remotes/paymentsRemote'

export type RequestGetOrderProps = {
  token: string
  orderId: string
}
export type RequestGetOrderResponse = any

export const getOrderRequest = async (props: RequestGetOrderProps): Promise<RequestGetOrderResponse> => {

  const { data } = await paymentsRemote.request<RequestGetOrderResponse>({
    method: `GET`,
    url: `/system/public_order/i/${props.orderId}`,
    params: {},
    paramsSerializer: {
      indexes: null
    },
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
