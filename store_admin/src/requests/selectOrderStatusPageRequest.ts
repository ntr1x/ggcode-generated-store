import { paymentsRemote } from '../remotes/paymentsRemote'

export type RequestSelectOrderStatusPageProps = {
  token: string
  query?: Record<string, any>
  payload?: Record<string, any>
}
export type RequestSelectOrderStatusPageResponse = any

export const selectOrderStatusPageRequest = async (props: RequestSelectOrderStatusPageProps): Promise<RequestSelectOrderStatusPageResponse> => {

  const { data } = await paymentsRemote.request<RequestSelectOrderStatusPageResponse>({
    method: `POST`,
    url: `/system/public_order_status/select`,
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
