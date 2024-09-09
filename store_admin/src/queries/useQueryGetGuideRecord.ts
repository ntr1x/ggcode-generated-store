import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { structureRemote } from '../remotes/structureRemote'

export type QueryGetGuideRecordProps = {
  guideId?: string
}

export type QueryGetGuideRecordFilter = {
  // yet nothing
}

export type QueryGetGuideRecordSorter = {
  // yet nothing
}

const mapping: Record<string, any> = {
  // yet nothing
}

export function useQueryGetGuideRecord(
  // @ts-ignore
  props: QueryGetGuideRecordProps,
  sort?: QueryGetGuideRecordSorter,
  filter?: QueryGetGuideRecordFilter
) {
  const authStore = useAuthStore()

  return useAxiosAutoRequest<any>(structureRemote, async () => {
    const token = await authStore.requireToken()

    const body: Record<string, any> = {}
    if (filter != null) {
      for (const [k, v] of Object.entries(filter)) {
        if (v != undefined) {
          body[mapping[k]] = v
        }
      }
    }

    const sortArray: string[] = []
    if (sort != null) {
      for (const [k, v] of Object.entries(sort)) {
        if (v != null) {
          sortArray.push(`${k},${v}`)
        }
      }
    }

    const query: Record<string, any> = {}
    if (sortArray.length > 0) {
      query.sort = sortArray
    }

    return {
      method: 'GET',
      url: `/system/public_guide/i/${props.guideId}`,
      params: query,
      paramsSerializer: {
        indexes: null
      },
      headers: {
        Authorization: `Bearer ${token}`
      },
      data: body
    }
  })
}
