
import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { selectCustomerPageRequest } from '../requests/selectCustomerPageRequest'

export type QuerySelectCustomerPageProps = {
  // yet nothing
}

export type QuerySelectCustomerPageFilter = {
  // yet nothing
}

export type QuerySelectCustomerPageSorter = {
  // yet nothing
}

export function useQuerySelectCustomerPage(
  // @ts-ignore
  props: QuerySelectCustomerPageProps,
  // @ts-ignore
  sort?: QuerySelectCustomerPageSorter,
  // @ts-ignore
  filter?: QuerySelectCustomerPageFilter
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

    const query: Record<string, any> = {"size":50,"sort":"fullName"}
    if (sortArray.length > 0) {
      query.sort = sortArray
    }

    return selectCustomerPageRequest({
      token,
      query,
      payload,
    })
  })
}
