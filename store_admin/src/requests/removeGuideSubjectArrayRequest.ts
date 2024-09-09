import { structureRemote } from '../remotes/structureRemote'
import { StructureWidthIdRecord } from '../structures/StructureWidthIdRecord';

export type RequestRemoveGuideSubjectArrayProps = {
  token: string
  payload: StructureWidthIdRecord[]
}

export const removeGuideSubjectArrayRequest = async (props: RequestRemoveGuideSubjectArrayProps) => {

  const { data } = await structureRemote.request({
    method: `POST`,
    url: `/system/public_guide_subject/removeAll`,
    data: Object.assign([], props.payload),
    params: {},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
