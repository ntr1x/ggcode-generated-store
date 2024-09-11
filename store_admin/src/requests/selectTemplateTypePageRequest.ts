import { eventsRemote } from '../remotes/eventsRemote'

export type RequestSelectTemplateTypePageProps = {
  token: string
  query?: Record<string, any>
  payload?: Record<string, any>
}
export type RequestSelectTemplateTypePageResponse = any

export const selectTemplateTypePageRequest = async (props: RequestSelectTemplateTypePageProps): Promise<RequestSelectTemplateTypePageResponse> => {

  const { data } = await eventsRemote.request<RequestSelectTemplateTypePageResponse>({
    method: `POST`,
    url: `/system/public_template_type/select`,
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
