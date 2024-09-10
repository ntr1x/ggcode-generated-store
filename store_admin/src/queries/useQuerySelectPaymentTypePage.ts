
import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { selectPaymentTypePageRequest } from '../requests/selectPaymentTypePageRequest'

export type QuerySelectPaymentTypePageProps = {
  // yet nothing
}

export type QuerySelectPaymentTypePageFilter = {
  // yet nothing
}

export type QuerySelectPaymentTypePageSorter = {
  // yet nothing
}

export function useQuerySelectPaymentTypePage(
  // @ts-ignore
  props: QuerySelectPaymentTypePageProps,
  // @ts-ignore
  sort?: QuerySelectPaymentTypePageSorter,
  // @ts-ignore
  filter?: QuerySelectPaymentTypePageFilter
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

    return selectPaymentTypePageRequest({
      token,
      query,
      payload,
    })
  })
}
