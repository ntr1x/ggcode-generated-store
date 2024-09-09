import { eventsRemote } from '../remotes/eventsRemote'

export type RequestSelectTemplateShapePageProps = {
  token: string
  payload: Record<string, any>
}

export const selectTemplateShapePageRequest = async (props: RequestSelectTemplateShapePageProps) => {

  const { data } = await eventsRemote.request({
    method: `POST`,
    url: `/system/public_template_shape/select`,
    data: Object.assign({}, props.payload),
    params: {},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
