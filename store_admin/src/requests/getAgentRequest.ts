import { customersRemote } from '../remotes/customersRemote'

export type RequestGetAgentProps = {
  token: string
  agentId: string
}

export const getAgentRequest = async (props: RequestGetAgentProps) => {

  const { data } = await customersRemote.request({
    method: `GET`,
    url: `/system/public_agent/i/${props.agentId}`,
    params: {},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}