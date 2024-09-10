
import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { selectSubscriptionPageRequest } from '../requests/selectSubscriptionPageRequest'

export type QuerySelectSubscriptionPageProps = {
  // yet nothing
}

export type QuerySelectSubscriptionPageFilter = {
  customerId?: string,
  typeId?: string,
}

export type QuerySelectSubscriptionPageSorter = {
  id?: 'asc' | 'desc',
  createdAt?: 'asc' | 'desc',
  typeId?: 'asc' | 'desc',
}
const mapping: Record<string, any> = {
  customerId: 'customerId',
  typeId: 'typeId',
}

export function useQuerySelectSubscriptionPage(
  // @ts-ignore
  props: QuerySelectSubscriptionPageProps,
  // @ts-ignore
  sort?: QuerySelectSubscriptionPageSorter,
  // @ts-ignore
  filter?: QuerySelectSubscriptionPageFilter
) {
  const authStore = useAuthStore()

  return useAxiosAutoRequest<any>(async () => {
    const token = await authStore.requireToken()
    const payload: Record<string, any> = {}
    if (filter != null) {
      for (const [k, v] of Object.entries(filter)) {
        if (v != undefined) {
          payload[mapping[k]] = v
        }
      }
    }
    
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

    return selectSubscriptionPageRequest({
      token,
      query,
      payload,
    })
  })
}
