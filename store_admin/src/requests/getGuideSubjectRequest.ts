import { structureRemote } from '../remotes/structureRemote'

export type RequestGetGuideSubjectProps = {
  token: string
  subjectId: string
}
export type RequestGetGuideSubjectResponse = any

export const getGuideSubjectRequest = async (props: RequestGetGuideSubjectProps): Promise<RequestGetGuideSubjectResponse> => {

  const { data } = await structureRemote.request<RequestGetGuideSubjectResponse>({
    method: `GET`,
    url: `/system/public_guide_subject/i/${props.subjectId}`,
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
