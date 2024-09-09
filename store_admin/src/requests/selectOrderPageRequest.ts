import { paymentsRemote } from '../remotes/paymentsRemote'

export type RequestSelectOrderPageProps = {
  token: string
  payload: Record<string, any>
}

export const selectOrderPageRequest = async (props: RequestSelectOrderPageProps) => {

  const { data } = await paymentsRemote.request({
    method: `POST`,
    url: `/system/public_order/select`,
    data: Object.assign({}, props.payload),
    params: {"size":50,"sort":"id,asc"},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
