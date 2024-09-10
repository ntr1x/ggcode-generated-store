import { productsRemote } from '../remotes/productsRemote'

export type RequestSelectShopPageProps = {
  token: string
  query?: Record<string, any>
  payload?: Record<string, any>
}
export type RequestSelectShopPageResponse = any

export const selectShopPageRequest = async (props: RequestSelectShopPageProps): Promise<RequestSelectShopPageResponse> => {

  const { data } = await productsRemote.request<RequestSelectShopPageResponse>({
    method: `POST`,
    url: `/system/public_shop/select`,
    data: Object.assign({}, props.payload),
    params: Object.assign({"size":50,"sort":"name,asc"}, props.query),
    paramsSerializer: {
      indexes: null
    },
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
