import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { structureRemote } from '../remotes/structureRemote'

export type QueryGetGuideSubjectRecordProps = {
  subjectId?: string
}

export type QueryGetGuideSubjectRecordFilter = {
  // yet nothing
}

export type QueryGetGuideSubjectRecordSorter = {
  // yet nothing
}

const mapping: Record<string, any> = {
  // yet nothing
}

export function useQueryGetGuideSubjectRecord(
  // @ts-ignore
  props: QueryGetGuideSubjectRecordProps,
  sort?: QueryGetGuideSubjectRecordSorter,
  filter?: QueryGetGuideSubjectRecordFilter
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
      url: `/system/public_guide_subject/i/${props.subjectId}`,
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
