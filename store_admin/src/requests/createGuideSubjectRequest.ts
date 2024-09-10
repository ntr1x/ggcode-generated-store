import { structureRemote } from '../remotes/structureRemote'

export type RequestCreateGuideSubjectProps = {
  token: string
  payload: Record<string, any>
}
export type RequestCreateGuideSubjectResponse = any

export const createGuideSubjectRequest = async (props: RequestCreateGuideSubjectProps): Promise<RequestCreateGuideSubjectResponse> => {

  const { data } = await structureRemote.request<RequestCreateGuideSubjectResponse>({
    method: `POST`,
    url: `/system/public_guide_subject`,
    data: Object.assign({}, props.payload),
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
