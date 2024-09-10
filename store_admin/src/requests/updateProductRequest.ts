import { productsRemote } from '../remotes/productsRemote'

export type RequestUpdateProductProps = {
  token: string
  productId: string
  payload: Record<string, any>
}
export type RequestUpdateProductResponse = any

export const updateProductRequest = async (props: RequestUpdateProductProps): Promise<RequestUpdateProductResponse> => {

  const { data } = await productsRemote.request<RequestUpdateProductResponse>({
    method: `PUT`,
    url: `/system/public_product/i/${props.productId}`,
    data: Object.assign({}, props.payload),
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
