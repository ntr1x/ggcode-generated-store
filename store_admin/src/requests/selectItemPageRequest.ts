import { productsRemote } from '../remotes/productsRemote'

export type RequestSelectItemPageProps = {
  token: string
  query?: Record<string, any>
  payload?: Record<string, any>
}
export type RequestSelectItemPageResponse = any

export const selectItemPageRequest = async (props: RequestSelectItemPageProps): Promise<RequestSelectItemPageResponse> => {

  const { data } = await productsRemote.request<RequestSelectItemPageResponse>({
    method: `POST`,
    url: `/system/public_item/select`,
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
