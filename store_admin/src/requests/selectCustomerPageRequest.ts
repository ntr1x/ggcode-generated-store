import { customersRemote } from '../remotes/customersRemote'

export type RequestSelectCustomerPageProps = {
  token: string
  query?: Record<string, any>
  payload?: Record<string, any>
}
export type RequestSelectCustomerPageResponse = any

export const selectCustomerPageRequest = async (props: RequestSelectCustomerPageProps): Promise<RequestSelectCustomerPageResponse> => {

  const { data } = await customersRemote.request<RequestSelectCustomerPageResponse>({
    method: `POST`,
    url: `/system/public_customer/select`,
    data: Object.assign({}, props.payload),
    params: Object.assign({"size":50,"sort":"fullName"}, props.query),
    paramsSerializer: {
      indexes: null
    },
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
