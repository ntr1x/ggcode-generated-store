import { paymentsRemote } from '../remotes/paymentsRemote'

export type RequestSelectPromotionTypePageProps = {
  token: string
  query?: Record<string, any>
  payload?: Record<string, any>
}
export type RequestSelectPromotionTypePageResponse = any

export const selectPromotionTypePageRequest = async (props: RequestSelectPromotionTypePageProps): Promise<RequestSelectPromotionTypePageResponse> => {

  const { data } = await paymentsRemote.request<RequestSelectPromotionTypePageResponse>({
    method: `POST`,
    url: `/system/public_promotion_type/select`,
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
