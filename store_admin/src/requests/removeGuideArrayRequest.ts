import { structureRemote } from '../remotes/structureRemote'
import { StructureWidthIdRecord } from '../structures/StructureWidthIdRecord';

export type RequestRemoveGuideArrayProps = {
  token: string
  payload: StructureWidthIdRecord[]
}
export type RequestRemoveGuideArrayResponse = any

export const removeGuideArrayRequest = async (props: RequestRemoveGuideArrayProps): Promise<RequestRemoveGuideArrayResponse> => {

  const { data } = await structureRemote.request<RequestRemoveGuideArrayResponse>({
    method: `POST`,
    url: `/system/public_guide/removeAll`,
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
