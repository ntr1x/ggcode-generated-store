import { productsRemote } from '../remotes/productsRemote'

export type RequestSelectCategoryPageProps = {
  token: string
  query?: Record<string, any>
  payload?: Record<string, any>
}
export type RequestSelectCategoryPageResponse = any

export const selectCategoryPageRequest = async (props: RequestSelectCategoryPageProps): Promise<RequestSelectCategoryPageResponse> => {

  const { data } = await productsRemote.request<RequestSelectCategoryPageResponse>({
    method: `POST`,
    url: `/system/public_category/select`,
    data: Object.assign({}, props.payload),
    params: Object.assign({"size":50,"sort":"name,asc"}, props.query),
    paramsSerializer: {
      indexes: null
    },
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
