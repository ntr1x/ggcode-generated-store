import { structureRemote } from '../remotes/structureRemote'

export type RequestCreateNetworkProps = {
  token: string
  payload: Record<string, any>
}

export const createNetworkRequest = async (props: RequestCreateNetworkProps) => {

  const { data } = await structureRemote.request({
    method: `POST`,
    url: `/system/public_network`,
    data: Object.assign({}, props.payload),
    params: {},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
