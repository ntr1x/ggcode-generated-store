import { productsRemote } from '../remotes/productsRemote'

export type RequestSelectShopPageProps = {
  token: string
  payload: Record<string, any>
}

export const selectShopPageRequest = async (props: RequestSelectShopPageProps) => {

  const { data } = await productsRemote.request({
    method: `POST`,
    url: `/system/public_shop/select`,
    data: Object.assign({}, props.payload),
    params: {},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
