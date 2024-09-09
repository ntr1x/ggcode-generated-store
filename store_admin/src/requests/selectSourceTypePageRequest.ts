import { paymentsRemote } from '../remotes/paymentsRemote'

export type RequestSelectSourceTypePageProps = {
  token: string
  payload: Record<string, any>
}

export const selectSourceTypePageRequest = async (props: RequestSelectSourceTypePageProps) => {

  const { data } = await paymentsRemote.request({
    method: `POST`,
    url: `/system/public_source_type/select`,
    data: Object.assign({}, props.payload),
    params: {},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
