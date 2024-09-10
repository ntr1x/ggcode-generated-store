
import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { selectNetworkTypePageRequest } from '../requests/selectNetworkTypePageRequest'

export type QuerySelectNetworkTypePageProps = {
  // yet nothing
}

export type QuerySelectNetworkTypePageFilter = {
  // yet nothing
}

export type QuerySelectNetworkTypePageSorter = {
  id?: 'asc' | 'desc',
  name?: 'asc' | 'desc',
  description?: 'asc' | 'desc',
}

export function useQuerySelectNetworkTypePage(
  // @ts-ignore
  props: QuerySelectNetworkTypePageProps,
  // @ts-ignore
  sort?: QuerySelectNetworkTypePageSorter,
  // @ts-ignore
  filter?: QuerySelectNetworkTypePageFilter
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

    return selectNetworkTypePageRequest({
      token,
      query,
      payload,
    })
  })
}
