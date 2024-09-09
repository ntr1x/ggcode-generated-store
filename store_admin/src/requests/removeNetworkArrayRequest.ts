import { structureRemote } from '../remotes/structureRemote'
import { StructureWidthIdRecord } from '../structures/StructureWidthIdRecord';

export type RequestRemoveNetworkArrayProps = {
  token: string
  payload: StructureWidthIdRecord[]
}

export const removeNetworkArrayRequest = async (props: RequestRemoveNetworkArrayProps) => {

  const { data } = await structureRemote.request({
    method: `POST`,
    url: `/system/public_network/removeAll`,
    data: Object.assign([], props.payload),
    params: {},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
