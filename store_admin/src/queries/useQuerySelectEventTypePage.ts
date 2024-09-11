
import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { selectEventTypePageRequest } from '../requests/selectEventTypePageRequest'

export type QuerySelectEventTypePageProps = {
  // yet nothing
}

export type QuerySelectEventTypePageFilter = {
  // yet nothing
}

export type QuerySelectEventTypePageSorter = {
  name?: 'asc' | 'desc',
  description?: 'asc' | 'desc',
}

export function useQuerySelectEventTypePage(
  // @ts-ignore
  props: QuerySelectEventTypePageProps,
  // @ts-ignore
  sort?: QuerySelectEventTypePageSorter,
  // @ts-ignore
  filter?: QuerySelectEventTypePageFilter
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

    return selectEventTypePageRequest({
      token,
      query,
      payload,
    })
  })
}
