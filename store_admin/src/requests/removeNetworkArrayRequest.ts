import { structureRemote } from '../remotes/structureRemote'
import { StructureWidthIdRecord } from '../structures/StructureWidthIdRecord';

export type RequestRemoveNetworkArrayProps = {
  token: string
  payload: StructureWidthIdRecord[]
}
export type RequestRemoveNetworkArrayResponse = any

export const removeNetworkArrayRequest = async (props: RequestRemoveNetworkArrayProps): Promise<RequestRemoveNetworkArrayResponse> => {

  const { data } = await structureRemote.request<RequestRemoveNetworkArrayResponse>({
    method: `POST`,
    url: `/system/public_network/removeAll`,
    data: Object.assign([], props.payload),
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
