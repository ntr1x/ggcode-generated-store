import { structureRemote } from '../remotes/structureRemote'

export type RequestSelectNetworkTypePageProps = {
  token: string
  payload: Record<string, any>
}

export const selectNetworkTypePageRequest = async (props: RequestSelectNetworkTypePageProps) => {

  const { data } = await structureRemote.request({
    method: `POST`,
    url: `/system/public_network_type/select`,
    data: Object.assign({}, props.payload),
    params: {},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
