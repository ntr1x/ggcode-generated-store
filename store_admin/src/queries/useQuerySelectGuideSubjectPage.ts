
import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { selectGuideSubjectPageRequest } from '../requests/selectGuideSubjectPageRequest'
import { StructurePublicGuideSubjectPage } from '../structures/StructurePublicGuideSubjectPage'

export type QuerySelectGuideSubjectPageProps = {
  // yet nothing
}

export type QuerySelectGuideSubjectPageFilter = {
  // yet nothing
}

export type QuerySelectGuideSubjectPageSorter = {
  id?: 'asc' | 'desc',
  name?: 'asc' | 'desc',
  position?: 'asc' | 'desc',
  createdAt?: 'asc' | 'desc',
  updatedAt?: 'asc' | 'desc',
}

export function useQuerySelectGuideSubjectPage(
  // @ts-ignore
  props: QuerySelectGuideSubjectPageProps,
  // @ts-ignore
  sort?: QuerySelectGuideSubjectPageSorter,
  // @ts-ignore
  filter?: QuerySelectGuideSubjectPageFilter
) {
  const authStore = useAuthStore()

  return useAxiosAutoRequest<StructurePublicGuideSubjectPage>(async () => {
    const token = await authStore.requireToken()
    const payload: Record<string, any> = {}
    
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

    return selectGuideSubjectPageRequest({
      token,
      query,
      payload,
    })
  })
}
