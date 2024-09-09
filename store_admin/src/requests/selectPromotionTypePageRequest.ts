import { paymentsRemote } from '../remotes/paymentsRemote'

export type RequestSelectPromotionTypePageProps = {
  token: string
  payload: Record<string, any>
}

export const selectPromotionTypePageRequest = async (props: RequestSelectPromotionTypePageProps) => {

  const { data } = await paymentsRemote.request({
    method: `POST`,
    url: `/system/public_promotion_type/select`,
    data: Object.assign({}, props.payload),
    params: {},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
