import { paymentsRemote } from '../remotes/paymentsRemote'

export type RequestSelectPromotionTargetPageProps = {
  token: string
  query?: Record<string, any>
  payload?: Record<string, any>
}
export type RequestSelectPromotionTargetPageResponse = any

export const selectPromotionTargetPageRequest = async (props: RequestSelectPromotionTargetPageProps): Promise<RequestSelectPromotionTargetPageResponse> => {

  const { data } = await paymentsRemote.request<RequestSelectPromotionTargetPageResponse>({
    method: `POST`,
    url: `/system/public_promotion_target/select`,
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
