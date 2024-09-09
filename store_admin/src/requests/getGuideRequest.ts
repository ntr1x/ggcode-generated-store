import { structureRemote } from '../remotes/structureRemote'

export type RequestGetGuideProps = {
  token: string
  guideId: string
}

export const getGuideRequest = async (props: RequestGetGuideProps) => {

  const { data } = await structureRemote.request({
    method: `GET`,
    url: `/system/public_guide/i/${props.guideId}`,
    params: {},
    headers: {
      Authorization: `Bearer ${props.token}`
    },
  })

  return data
}
