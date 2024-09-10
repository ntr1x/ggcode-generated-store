import { eventsRemote } from '../remotes/eventsRemote'

export type RequestSelectTemplateShapePageProps = {
  token: string
  query?: Record<string, any>
  payload?: Record<string, any>
}
export type RequestSelectTemplateShapePageResponse = any

export const selectTemplateShapePageRequest = async (props: RequestSelectTemplateShapePageProps): Promise<RequestSelectTemplateShapePageResponse> => {

  const { data } = await eventsRemote.request<RequestSelectTemplateShapePageResponse>({
    method: `POST`,
    url: `/system/public_template_shape/select`,
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
