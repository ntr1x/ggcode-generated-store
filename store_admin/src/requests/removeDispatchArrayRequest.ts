import { eventsRemote } from '../remotes/eventsRemote'
import { StructureWidthIdRecord } from '../structures/StructureWidthIdRecord';

export type RequestRemoveDispatchArrayProps = {
  token: string
  payload: StructureWidthIdRecord[]
}
export type RequestRemoveDispatchArrayResponse = any

export const removeDispatchArrayRequest = async (props: RequestRemoveDispatchArrayProps): Promise<RequestRemoveDispatchArrayResponse> => {

  const { data } = await eventsRemote.request<RequestRemoveDispatchArrayResponse>({
    method: `POST`,
    url: `/system/public_dispatch/removeAll`,
    data: Object.assign([], props.payload),
    params: {},
    paramsSerializer: {
      indexes: null
    },
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
