
import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { selectSourceTypePageRequest } from '../requests/selectSourceTypePageRequest'

export type QuerySelectSourceTypePageProps = {
  // yet nothing
}

export type QuerySelectSourceTypePageFilter = {
  // yet nothing
}

export type QuerySelectSourceTypePageSorter = {
  // yet nothing
}

export function useQuerySelectSourceTypePage(
  // @ts-ignore
  props: QuerySelectSourceTypePageProps,
  // @ts-ignore
  sort?: QuerySelectSourceTypePageSorter,
  // @ts-ignore
  filter?: QuerySelectSourceTypePageFilter
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

    return selectSourceTypePageRequest({
      token,
      query,
      payload,
    })
  })
}
