import { productsRemote } from '../remotes/productsRemote'

export type RequestSelectPromotionPageProps = {
  token: string
  payload: Record<string, any>
}

export const selectPromotionPageRequest = async (props: RequestSelectPromotionPageProps) => {

  const { data } = await productsRemote.request({
    method: `POST`,
    url: `/system/public_promotion/select`,
    data: Object.assign({}, props.payload),
    params: {},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}