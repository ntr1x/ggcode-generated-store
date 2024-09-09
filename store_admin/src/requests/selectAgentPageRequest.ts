import { customersRemote } from '../remotes/customersRemote'

export type RequestSelectAgentPageProps = {
  token: string
  payload: Record<string, any>
}

export const selectAgentPageRequest = async (props: RequestSelectAgentPageProps) => {

  const { data } = await customersRemote.request({
    method: `POST`,
    url: `/system/public_agent/select`,
    data: Object.assign({}, props.payload),
    params: {"size":50},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
