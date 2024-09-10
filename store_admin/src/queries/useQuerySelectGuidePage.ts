
import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { selectGuidePageRequest } from '../requests/selectGuidePageRequest'
import { StructurePublicGuidePage } from '../structures/StructurePublicGuidePage'

export type QuerySelectGuidePageProps = {
  // yet nothing
}

export type QuerySelectGuidePageFilter = {
  subjectId?: string,
}

export type QuerySelectGuidePageSorter = {
  id?: 'asc' | 'desc',
  name?: 'asc' | 'desc',
  position?: 'asc' | 'desc',
  subjectId?: 'asc' | 'desc',
  createdAt?: 'asc' | 'desc',
  updatedAt?: 'asc' | 'desc',
}
const mapping: Record<string, any> = {
  subjectId: 'subjectId',
}

export function useQuerySelectGuidePage(
  // @ts-ignore
  props: QuerySelectGuidePageProps,
  // @ts-ignore
  sort?: QuerySelectGuidePageSorter,
  // @ts-ignore
  filter?: QuerySelectGuidePageFilter
) {
  const authStore = useAuthStore()

  return useAxiosAutoRequest<StructurePublicGuidePage>(async () => {
    const token = await authStore.requireToken()
    const payload: Record<string, any> = {}
    if (filter != null) {
      for (const [k, v] of Object.entries(filter)) {
        if (v != undefined) {
          payload[mapping[k]] = v
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

    const query: Record<string, any> = {"size":50,"sort":"position,asc"}
    if (sortArray.length > 0) {
      query.sort = sortArray
    }

    return selectGuidePageRequest({
      token,
      query,
      payload,
    })
  })
}
