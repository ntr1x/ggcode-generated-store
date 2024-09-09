import { structureRemote } from '../remotes/structureRemote'

export type RequestGetGuideSubjectProps = {
  token: string
  subjectId: string
}

export const getGuideSubjectRequest = async (props: RequestGetGuideSubjectProps) => {

  const { data } = await structureRemote.request({
    method: `GET`,
    url: `/system/public_guide_subject/i/${props.subjectId}`,
    params: {},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
