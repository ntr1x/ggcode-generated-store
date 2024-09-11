import { customersRemote } from '../remotes/customersRemote'

export type RequestGetCustomerProps = {
  token: string
  customerId: string
}
export type RequestGetCustomerResponse = any

export const getCustomerRequest = async (props: RequestGetCustomerProps): Promise<RequestGetCustomerResponse> => {

  const { data } = await customersRemote.request<RequestGetCustomerResponse>({
    method: `GET`,
    url: `/system/public_customer/i/${props.customerId}`,
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
