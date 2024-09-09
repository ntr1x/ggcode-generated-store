import { eventsRemote } from '../remotes/eventsRemote'

export type RequestSelectTemplatePageProps = {
  token: string
  payload: Record<string, any>
}

export const selectTemplatePageRequest = async (props: RequestSelectTemplatePageProps) => {

  const { data } = await eventsRemote.request({
    method: `POST`,
    url: `/system/public_template/select`,
    data: Object.assign({}, props.payload),
    params: {"size":50,"sort":"id,desc"},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
