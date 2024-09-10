import { productsRemote } from '../remotes/productsRemote'

export type RequestUpdateProductProps = {
  token: string
  productId: string
  payload: Record<string, any>
}

export const updateProductRequest = async (props: RequestUpdateProductProps) => {

  const { data } = await productsRemote.request({
    method: `PUT`,
    url: `/system/public_product/i/${props.productId}`,
    data: Object.assign({}, props.payload),
    params: {},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
