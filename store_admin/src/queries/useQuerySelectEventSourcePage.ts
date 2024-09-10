
import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { selectEventSourcePageRequest } from '../requests/selectEventSourcePageRequest'

export type QuerySelectEventSourcePageProps = {
  // yet nothing
}

export type QuerySelectEventSourcePageFilter = {
  // yet nothing
}

export type QuerySelectEventSourcePageSorter = {
  name?: 'asc' | 'desc',
  description?: 'asc' | 'desc',
}

export function useQuerySelectEventSourcePage(
  // @ts-ignore
  props: QuerySelectEventSourcePageProps,
  // @ts-ignore
  sort?: QuerySelectEventSourcePageSorter,
  // @ts-ignore
  filter?: QuerySelectEventSourcePageFilter
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

    return selectEventSourcePageRequest({
      token,
      query,
      payload,
    })
  })
}
