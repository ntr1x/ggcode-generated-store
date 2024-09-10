
import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { selectPaymentStatusPageRequest } from '../requests/selectPaymentStatusPageRequest'

export type QuerySelectPaymentStatusPageProps = {
  // yet nothing
}

export type QuerySelectPaymentStatusPageFilter = {
  // yet nothing
}

export type QuerySelectPaymentStatusPageSorter = {
  // yet nothing
}

export function useQuerySelectPaymentStatusPage(
  // @ts-ignore
  props: QuerySelectPaymentStatusPageProps,
  // @ts-ignore
  sort?: QuerySelectPaymentStatusPageSorter,
  // @ts-ignore
  filter?: QuerySelectPaymentStatusPageFilter
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

    return selectPaymentStatusPageRequest({
      token,
      query,
      payload,
    })
  })
}
