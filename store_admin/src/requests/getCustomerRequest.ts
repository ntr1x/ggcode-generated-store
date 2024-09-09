import { customersRemote } from '../remotes/customersRemote'

export type RequestGetCustomerProps = {
  token: string
  customerId: string
}

export const getCustomerRequest = async (props: RequestGetCustomerProps) => {

  const { data } = await customersRemote.request({
    method: `GET`,
    url: `/system/public_customer/i/${props.customerId}`,
    params: {},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
