import { eventsRemote } from '../remotes/eventsRemote'

export type RequestSelectTemplatePageProps = {
  token: string
  query?: Record<string, any>
  payload?: Record<string, any>
}
export type RequestSelectTemplatePageResponse = any

export const selectTemplatePageRequest = async (props: RequestSelectTemplatePageProps): Promise<RequestSelectTemplatePageResponse> => {

  const { data } = await eventsRemote.request<RequestSelectTemplatePageResponse>({
    method: `POST`,
    url: `/system/public_template/select`,
    data: Object.assign({}, props.payload),
    params: Object.assign({"size":50,"sort":"id,desc"}, props.query),
    paramsSerializer: {
      indexes: null
    },
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
