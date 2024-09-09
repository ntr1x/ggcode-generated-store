import { paymentsRemote } from '../remotes/paymentsRemote'

export type RequestSelectOrderStatusPageProps = {
  token: string
  payload: Record<string, any>
}

export const selectOrderStatusPageRequest = async (props: RequestSelectOrderStatusPageProps) => {

  const { data } = await paymentsRemote.request({
    method: `POST`,
    url: `/system/public_order_status/select`,
    data: Object.assign({}, props.payload),
    params: {"size":50,"sort":"id,asc"},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
