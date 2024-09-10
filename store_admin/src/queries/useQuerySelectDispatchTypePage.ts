
import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { selectDispatchTypePageRequest } from '../requests/selectDispatchTypePageRequest'

export type QuerySelectDispatchTypePageProps = {
  // yet nothing
}

export type QuerySelectDispatchTypePageFilter = {
  // yet nothing
}

export type QuerySelectDispatchTypePageSorter = {
  id?: 'asc' | 'desc',
  name?: 'asc' | 'desc',
  description?: 'asc' | 'desc',
}

export function useQuerySelectDispatchTypePage(
  // @ts-ignore
  props: QuerySelectDispatchTypePageProps,
  // @ts-ignore
  sort?: QuerySelectDispatchTypePageSorter,
  // @ts-ignore
  filter?: QuerySelectDispatchTypePageFilter
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

    return selectDispatchTypePageRequest({
      token,
      query,
      payload,
    })
  })
}
