import { paymentsRemote } from '../remotes/paymentsRemote'

export type RequestSelectSourceTypePageProps = {
  token: string
  query?: Record<string, any>
  payload?: Record<string, any>
}
export type RequestSelectSourceTypePageResponse = any

export const selectSourceTypePageRequest = async (props: RequestSelectSourceTypePageProps): Promise<RequestSelectSourceTypePageResponse> => {

  const { data } = await paymentsRemote.request<RequestSelectSourceTypePageResponse>({
    method: `POST`,
    url: `/system/public_source_type/select`,
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
