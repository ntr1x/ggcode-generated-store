import { customersRemote } from '../remotes/customersRemote'

export type RequestSelectAgentPageProps = {
  token: string
  query?: Record<string, any>
  payload?: Record<string, any>
}
export type RequestSelectAgentPageResponse = any

export const selectAgentPageRequest = async (props: RequestSelectAgentPageProps): Promise<RequestSelectAgentPageResponse> => {

  const { data } = await customersRemote.request<RequestSelectAgentPageResponse>({
    method: `POST`,
    url: `/system/public_agent/select`,
    data: Object.assign({}, props.payload),
    params: Object.assign({"size":50}, props.query),
    paramsSerializer: {
      indexes: null
    },
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
