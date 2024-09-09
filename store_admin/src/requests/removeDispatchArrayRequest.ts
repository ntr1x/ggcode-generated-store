import { eventsRemote } from '../remotes/eventsRemote'
import { StructureWidthIdRecord } from '../structures/StructureWidthIdRecord';

export type RequestRemoveDispatchArrayProps = {
  token: string
  payload: StructureWidthIdRecord[]
}

export const removeDispatchArrayRequest = async (props: RequestRemoveDispatchArrayProps) => {

  const { data } = await eventsRemote.request({
    method: `POST`,
    url: `/system/public_dispatch/removeAll`,
    data: Object.assign([], props.payload),
    params: {},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
