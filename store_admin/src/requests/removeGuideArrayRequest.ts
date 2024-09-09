import { structureRemote } from '../remotes/structureRemote'
import { StructureWidthIdRecord } from '../structures/StructureWidthIdRecord';

export type RequestRemoveGuideArrayProps = {
  token: string
  payload: StructureWidthIdRecord[]
}

export const removeGuideArrayRequest = async (props: RequestRemoveGuideArrayProps) => {

  const { data } = await structureRemote.request({
    method: `POST`,
    url: `/system/public_guide/removeAll`,
    data: Object.assign([], props.payload),
    params: {},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
