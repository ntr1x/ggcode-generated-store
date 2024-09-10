import { customersRemote } from '../remotes/customersRemote'

export type RequestGetAgentProps = {
  token: string
  agentId: string
}
export type RequestGetAgentResponse = any

export const getAgentRequest = async (props: RequestGetAgentProps): Promise<RequestGetAgentResponse> => {

  const { data } = await customersRemote.request<RequestGetAgentResponse>({
    method: `GET`,
    url: `/system/public_agent/i/${props.agentId}`,
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
