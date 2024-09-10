import { eventsRemote } from '../remotes/eventsRemote'

export type RequestSelectTemplateTypePageProps = {
  token: string
  payload: Record<string, any>
}

export const selectTemplateTypePageRequest = async (props: RequestSelectTemplateTypePageProps) => {

  const { data } = await eventsRemote.request({
    method: `POST`,
    url: `/system/public_template_type/select`,
    data: Object.assign({}, props.payload),
    params: {},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}