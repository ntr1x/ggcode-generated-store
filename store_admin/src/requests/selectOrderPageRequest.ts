import { paymentsRemote } from '../remotes/paymentsRemote'

export type RequestSelectOrderPageProps = {
  token: string
  query?: Record<string, any>
  payload?: Record<string, any>
}
export type RequestSelectOrderPageResponse = any

export const selectOrderPageRequest = async (props: RequestSelectOrderPageProps): Promise<RequestSelectOrderPageResponse> => {

  const { data } = await paymentsRemote.request<RequestSelectOrderPageResponse>({
    method: `POST`,
    url: `/system/public_order/select`,
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
