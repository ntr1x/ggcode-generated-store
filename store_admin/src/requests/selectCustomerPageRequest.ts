import { customersRemote } from '../remotes/customersRemote'

export type RequestSelectCustomerPageProps = {
  token: string
  payload: Record<string, any>
}

export const selectCustomerPageRequest = async (props: RequestSelectCustomerPageProps) => {

  const { data } = await customersRemote.request({
    method: `POST`,
    url: `/system/public_customer/select`,
    data: Object.assign({}, props.payload),
    params: {"size":50,"sort":"fullName"},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
