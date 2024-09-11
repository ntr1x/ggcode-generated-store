import { eventsRemote } from '../remotes/eventsRemote'
import { StructurePublicDispatchPage } from '../structures/StructurePublicDispatchPage'

export type RequestSelectDispatchPageProps = {
  token: string
  query?: Record<string, any>
  payload?: Record<string, any>
}
export type RequestSelectDispatchPageResponse = StructurePublicDispatchPage

export const selectDispatchPageRequest = async (props: RequestSelectDispatchPageProps): Promise<RequestSelectDispatchPageResponse> => {

  const { data } = await eventsRemote.request<RequestSelectDispatchPageResponse>({
    method: `POST`,
    url: `/system/public_dispatch/select`,
    data: Object.assign({}, props.payload),
    params: Object.assign({"size":50,"sort":"createdAt,desc"}, props.query),
    paramsSerializer: {
      indexes: null
    },
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
