import { structureRemote } from '../remotes/structureRemote'

export type RequestSelectGuideSubjectPageProps = {
  token: string
  payload: Record<string, any>
}

export const selectGuideSubjectPageRequest = async (props: RequestSelectGuideSubjectPageProps) => {

  const { data } = await structureRemote.request({
    method: `POST`,
    url: `/system/public_guide_subject/select`,
    data: Object.assign({}, props.payload),
    params: {"size":50,"sort":"position,asc"},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
