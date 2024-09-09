import { productsRemote } from '../remotes/productsRemote'
import { StructureWidthIdRecord } from '../structures/StructureWidthIdRecord';

export type RequestRemoveProductArrayProps = {
  token: string
  payload: StructureWidthIdRecord[]
}

export const removeProductArrayRequest = async (props: RequestRemoveProductArrayProps) => {

  const { data } = await productsRemote.request({
    method: `POST`,
    url: `/system/public_product/removeAll`,
    data: Object.assign([], props.payload),
    params: {},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
