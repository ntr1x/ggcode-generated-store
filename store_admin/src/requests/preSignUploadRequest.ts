import { uploadsRemote } from '../remotes/uploadsRemote'

export type RequestPreSignUploadProps = {
  token: string
  payload: Record<string, any>
}

export const preSignUploadRequest = async (props: RequestPreSignUploadProps) => {

  const { data } = await uploadsRemote.request({
    method: `POST`,
    url: `/profile/uploads/preSign`,
    data: Object.assign({}, props.payload),
    params: {},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
