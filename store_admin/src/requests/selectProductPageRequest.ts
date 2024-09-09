import { productsRemote } from '../remotes/productsRemote'

export type RequestSelectProductPageProps = {
  token: string
  payload: Record<string, any>
}

export const selectProductPageRequest = async (props: RequestSelectProductPageProps) => {

  const { data } = await productsRemote.request({
    method: `POST`,
    url: `/system/public_product/select`,
    data: Object.assign({}, props.payload),
    params: {},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
