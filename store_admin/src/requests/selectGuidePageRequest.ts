import { structureRemote } from '../remotes/structureRemote'
import { StructurePublicGuidePage } from '../structures/StructurePublicGuidePage'

export type RequestSelectGuidePageProps = {
  token: string
  query?: Record<string, any>
  payload?: Record<string, any>
}
export type RequestSelectGuidePageResponse = StructurePublicGuidePage

export const selectGuidePageRequest = async (props: RequestSelectGuidePageProps): Promise<RequestSelectGuidePageResponse> => {

  const { data } = await structureRemote.request<RequestSelectGuidePageResponse>({
    method: `POST`,
    url: `/system/public_guide/select`,
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
