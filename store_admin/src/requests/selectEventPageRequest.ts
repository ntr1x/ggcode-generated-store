import { eventsRemote } from '../remotes/eventsRemote'

export type RequestSelectEventPageProps = {
  token: string
  query?: Record<string, any>
  payload?: Record<string, any>
}
export type RequestSelectEventPageResponse = any

export const selectEventPageRequest = async (props: RequestSelectEventPageProps): Promise<RequestSelectEventPageResponse> => {

  const { data } = await eventsRemote.request<RequestSelectEventPageResponse>({
    method: `POST`,
    url: `/system/public_event/select`,
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
