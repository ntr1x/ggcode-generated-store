
import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { selectOrderStatusPageRequest } from '../requests/selectOrderStatusPageRequest'

export type QuerySelectOrderStatusPageProps = {
  // yet nothing
}

export type QuerySelectOrderStatusPageFilter = {
  // yet nothing
}

export type QuerySelectOrderStatusPageSorter = {
  // yet nothing
}

export function useQuerySelectOrderStatusPage(
  // @ts-ignore
  props: QuerySelectOrderStatusPageProps,
  // @ts-ignore
  sort?: QuerySelectOrderStatusPageSorter,
  // @ts-ignore
  filter?: QuerySelectOrderStatusPageFilter
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

    const query: Record<string, any> = {"size":50,"sort":"id,asc"}
    if (sortArray.length > 0) {
      query.sort = sortArray
    }

    return selectOrderStatusPageRequest({
      token,
      query,
      payload,
    })
  })
}
