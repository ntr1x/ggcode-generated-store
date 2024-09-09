import { paymentsRemote } from '../remotes/paymentsRemote'

export type RequestSelectPromotionTargetPageProps = {
  token: string
  payload: Record<string, any>
}

export const selectPromotionTargetPageRequest = async (props: RequestSelectPromotionTargetPageProps) => {

  const { data } = await paymentsRemote.request({
    method: `POST`,
    url: `/system/public_promotion_target/select`,
    data: Object.assign({}, props.payload),
    params: {},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
