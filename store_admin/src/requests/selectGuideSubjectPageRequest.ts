import { structureRemote } from '../remotes/structureRemote'
import { StructurePublicGuideSubjectPage } from '../structures/StructurePublicGuideSubjectPage'

export type RequestSelectGuideSubjectPageProps = {
  token: string
  query?: Record<string, any>
  payload?: Record<string, any>
}
export type RequestSelectGuideSubjectPageResponse = StructurePublicGuideSubjectPage

export const selectGuideSubjectPageRequest = async (props: RequestSelectGuideSubjectPageProps): Promise<RequestSelectGuideSubjectPageResponse> => {

  const { data } = await structureRemote.request<RequestSelectGuideSubjectPageResponse>({
    method: `POST`,
    url: `/system/public_guide_subject/select`,
    data: Object.assign({}, props.payload),
    params: Object.assign({"size":50,"sort":"position,asc"}, props.query),
    paramsSerializer: {
      indexes: null
    },
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
