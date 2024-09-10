import { productsRemote } from '../remotes/productsRemote'

export type RequestSelectRegionPageProps = {
  token: string
  payload: Record<string, any>
}

export const selectRegionPageRequest = async (props: RequestSelectRegionPageProps) => {

  const { data } = await productsRemote.request({
    method: `POST`,
    url: `/system/public_region/select`,
    data: Object.assign({}, props.payload),
    params: {"size":50,"sort":"name,asc"},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
