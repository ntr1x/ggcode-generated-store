import { paymentsRemote } from '../remotes/paymentsRemote'

export type RequestSelectOrderTypePageProps = {
  token: string
  payload: Record<string, any>
}

export const selectOrderTypePageRequest = async (props: RequestSelectOrderTypePageProps) => {

  const { data } = await paymentsRemote.request({
    method: `POST`,
    url: `/system/public_order_type/select`,
    data: Object.assign({}, props.payload),
    params: {},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
