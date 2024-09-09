import { productsRemote } from '../remotes/productsRemote'

export type RequestCreateProductProps = {
  token: string
  payload: Record<string, any>
}

export const createProductRequest = async (props: RequestCreateProductProps) => {

  const { data } = await productsRemote.request({
    method: `POST`,
    url: `/system/public_product`,
    data: Object.assign({}, props.payload),
    params: {},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
