import { productsRemote } from '../remotes/productsRemote'

export type RequestCreateProductProps = {
  token: string
  payload: Record<string, any>
}
export type RequestCreateProductResponse = any

export const createProductRequest = async (props: RequestCreateProductProps): Promise<RequestCreateProductResponse> => {

  const { data } = await productsRemote.request<RequestCreateProductResponse>({
    method: `POST`,
    url: `/system/public_product`,
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
