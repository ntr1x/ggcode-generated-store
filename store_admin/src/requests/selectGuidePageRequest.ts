import { structureRemote } from '../remotes/structureRemote'

export type RequestSelectGuidePageProps = {
  token: string
  payload: Record<string, any>
}

export const selectGuidePageRequest = async (props: RequestSelectGuidePageProps) => {

  const { data } = await structureRemote.request({
    method: `POST`,
    url: `/system/public_guide/select`,
    data: Object.assign({}, props.payload),
    params: {"size":50,"sort":"position,asc"},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
