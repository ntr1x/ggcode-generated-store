import { productsRemote } from '../remotes/productsRemote'
import { StructurePublicProductPage } from '../structures/StructurePublicProductPage'

export type RequestSelectProductPageProps = {
  token: string
  query?: Record<string, any>
  payload?: Record<string, any>
}
export type RequestSelectProductPageResponse = StructurePublicProductPage

export const selectProductPageRequest = async (props: RequestSelectProductPageProps): Promise<RequestSelectProductPageResponse> => {

  const { data } = await productsRemote.request<RequestSelectProductPageResponse>({
    method: `POST`,
    url: `/system/public_product/select`,
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
