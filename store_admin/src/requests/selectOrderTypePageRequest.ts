import { paymentsRemote } from '../remotes/paymentsRemote'

export type RequestSelectOrderTypePageProps = {
  token: string
  query?: Record<string, any>
  payload?: Record<string, any>
}
export type RequestSelectOrderTypePageResponse = any

export const selectOrderTypePageRequest = async (props: RequestSelectOrderTypePageProps): Promise<RequestSelectOrderTypePageResponse> => {

  const { data } = await paymentsRemote.request<RequestSelectOrderTypePageResponse>({
    method: `POST`,
    url: `/system/public_order_type/select`,
    data: Object.assign({}, props.payload),
    params: Object.assign({}, props.query),
    paramsSerializer: {
      indexes: null
    },
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
