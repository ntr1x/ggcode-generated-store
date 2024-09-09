import { structureRemote } from '../remotes/structureRemote'

export type RequestSelectNetworkPageProps = {
  token: string
  payload: Record<string, any>
}

export const selectNetworkPageRequest = async (props: RequestSelectNetworkPageProps) => {

  const { data } = await structureRemote.request({
    method: `POST`,
    url: `/system/public_network/select`,
    data: Object.assign({}, props.payload),
    params: {"size":50,"sort":"id,desc"},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
