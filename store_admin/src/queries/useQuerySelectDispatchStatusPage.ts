
import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { selectDispatchStatusPageRequest } from '../requests/selectDispatchStatusPageRequest'

export type QuerySelectDispatchStatusPageProps = {
  // yet nothing
}

export type QuerySelectDispatchStatusPageFilter = {
  // yet nothing
}

export type QuerySelectDispatchStatusPageSorter = {
  id?: 'asc' | 'desc',
  name?: 'asc' | 'desc',
  description?: 'asc' | 'desc',
}

export function useQuerySelectDispatchStatusPage(
  // @ts-ignore
  props: QuerySelectDispatchStatusPageProps,
  // @ts-ignore
  sort?: QuerySelectDispatchStatusPageSorter,
  // @ts-ignore
  filter?: QuerySelectDispatchStatusPageFilter
) {
  const authStore = useAuthStore()

  return useAxiosAutoRequest<any>(async () => {
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

    const query: Record<string, any> = {}
    if (sortArray.length > 0) {
      query.sort = sortArray
    }

    return selectDispatchStatusPageRequest({
      token,
      query,
      payload,
    })
  })
}
