import { structureRemote } from '../remotes/structureRemote'
import { StructurePublicNetworkPage } from '../structures/StructurePublicNetworkPage'

export type RequestSelectNetworkPageProps = {
  token: string
  query?: Record<string, any>
  payload?: Record<string, any>
}
export type RequestSelectNetworkPageResponse = StructurePublicNetworkPage

export const selectNetworkPageRequest = async (props: RequestSelectNetworkPageProps): Promise<RequestSelectNetworkPageResponse> => {

  const { data } = await structureRemote.request<RequestSelectNetworkPageResponse>({
    method: `POST`,
    url: `/system/public_network/select`,
    data: Object.assign({}, props.payload),
    params: Object.assign({"size":50,"sort":"id,desc"}, props.query),
    paramsSerializer: {
      indexes: null
    },
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
