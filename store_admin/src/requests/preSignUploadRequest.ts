import { uploadsRemote } from '../remotes/uploadsRemote'

export type RequestPreSignUploadProps = {
  token: string
  payload: Record<string, any>
}
export type RequestPreSignUploadResponse = any

export const preSignUploadRequest = async (props: RequestPreSignUploadProps): Promise<RequestPreSignUploadResponse> => {

  const { data } = await uploadsRemote.request<RequestPreSignUploadResponse>({
    method: `POST`,
    url: `/profile/uploads/preSign`,
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
