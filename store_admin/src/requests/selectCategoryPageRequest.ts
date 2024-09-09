import { productsRemote } from '../remotes/productsRemote'

export type RequestSelectCategoryPageProps = {
  token: string
  payload: Record<string, any>
}

export const selectCategoryPageRequest = async (props: RequestSelectCategoryPageProps) => {

  const { data } = await productsRemote.request({
    method: `POST`,
    url: `/system/public_category/select`,
    data: Object.assign({}, props.payload),
    params: {},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
