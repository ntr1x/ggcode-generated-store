import { productsRemote } from '../remotes/productsRemote'

export type RequestSelectPromotionPageProps = {
  token: string
  query?: Record<string, any>
  payload?: Record<string, any>
}
export type RequestSelectPromotionPageResponse = any

export const selectPromotionPageRequest = async (props: RequestSelectPromotionPageProps): Promise<RequestSelectPromotionPageResponse> => {

  const { data } = await productsRemote.request<RequestSelectPromotionPageResponse>({
    method: `POST`,
    url: `/system/public_promotion/select`,
    data: Object.assign({}, props.payload),
    params: Object.assign({}, props.query),
    paramsSerializer: {
      indexes: null
    },
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
