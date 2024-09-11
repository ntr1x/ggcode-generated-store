import { structureRemote } from '../remotes/structureRemote'

export type RequestGetGuideProps = {
  token: string
  guideId: string
}
export type RequestGetGuideResponse = any

export const getGuideRequest = async (props: RequestGetGuideProps): Promise<RequestGetGuideResponse> => {

  const { data } = await structureRemote.request<RequestGetGuideResponse>({
    method: `GET`,
    url: `/system/public_guide/i/${props.guideId}`,
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
