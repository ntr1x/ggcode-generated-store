import { productsRemote } from '../remotes/productsRemote'
import { StructurePublicProductRecord } from '../structures/StructurePublicProductRecord'

export type RequestGetProductProps = {
  token: string
  productId: string
}
export type RequestGetProductResponse = StructurePublicProductRecord

export const getProductRequest = async (props: RequestGetProductProps): Promise<RequestGetProductResponse> => {

  const { data } = await productsRemote.request<RequestGetProductResponse>({
    method: `GET`,
    url: `/system/public_product/i/${props.productId}`,
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
