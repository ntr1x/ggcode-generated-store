import { structureRemote } from '../remotes/structureRemote'

export type RequestCreateGuideSubjectProps = {
  token: string
  payload: Record<string, any>
}

export const createGuideSubjectRequest = async (props: RequestCreateGuideSubjectProps) => {

  const { data } = await structureRemote.request({
    method: `POST`,
    url: `/system/public_guide_subject`,
    data: Object.assign({}, props.payload),
    params: {},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
