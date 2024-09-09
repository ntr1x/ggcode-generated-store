import { productsRemote } from '../remotes/productsRemote'

export type RequestGetProductProps = {
  token: string
  productId: string
}

export const getProductRequest = async (props: RequestGetProductProps) => {

  const { data } = await productsRemote.request({
    method: `GET`,
    url: `/system/public_product/i/${props.productId}`,
    params: {},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
