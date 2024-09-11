
import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { selectOrderTypePageRequest } from '../requests/selectOrderTypePageRequest'

export type QuerySelectOrderTypePageProps = {
  // yet nothing
}

export type QuerySelectOrderTypePageFilter = {
  // yet nothing
}

export type QuerySelectOrderTypePageSorter = {
  // yet nothing
}

export function useQuerySelectOrderTypePage(
  // @ts-ignore
  props: QuerySelectOrderTypePageProps,
  // @ts-ignore
  sort?: QuerySelectOrderTypePageSorter,
  // @ts-ignore
  filter?: QuerySelectOrderTypePageFilter
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

    return selectOrderTypePageRequest({
      token,
      query,
      payload,
    })
  })
}
