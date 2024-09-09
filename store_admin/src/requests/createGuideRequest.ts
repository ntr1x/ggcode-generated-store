import { structureRemote } from '../remotes/structureRemote'

export type RequestCreateGuideProps = {
  token: string
  payload: Record<string, any>
}

export const createGuideRequest = async (props: RequestCreateGuideProps) => {

  const { data } = await structureRemote.request({
    method: `POST`,
    url: `/system/public_guide`,
    data: Object.assign({}, props.payload),
    params: {},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
