import { productsRemote } from '../remotes/productsRemote'

export type RequestSelectItemPageProps = {
  token: string
  payload: Record<string, any>
}

export const selectItemPageRequest = async (props: RequestSelectItemPageProps) => {

  const { data } = await productsRemote.request({
    method: `POST`,
    url: `/system/public_item/select`,
    data: Object.assign({}, props.payload),
    params: {},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
