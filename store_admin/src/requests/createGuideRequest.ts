import { structureRemote } from '../remotes/structureRemote'

export type RequestCreateGuideProps = {
  token: string
  payload: Record<string, any>
}
export type RequestCreateGuideResponse = any

export const createGuideRequest = async (props: RequestCreateGuideProps): Promise<RequestCreateGuideResponse> => {

  const { data } = await structureRemote.request<RequestCreateGuideResponse>({
    method: `POST`,
    url: `/system/public_guide`,
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
