
import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { selectSubscriptionTypePageRequest } from '../requests/selectSubscriptionTypePageRequest'

export type QuerySelectSubscriptionTypePageProps = {
  // yet nothing
}

export type QuerySelectSubscriptionTypePageFilter = {
  customerId?: string,
  typeId?: string,
}

export type QuerySelectSubscriptionTypePageSorter = {
  id?: 'asc' | 'desc',
  name?: 'asc' | 'desc',
  description?: 'asc' | 'desc',
}
const mapping: Record<string, any> = {
  customerId: 'customerId',
  typeId: 'typeId',
}

export function useQuerySelectSubscriptionTypePage(
  // @ts-ignore
  props: QuerySelectSubscriptionTypePageProps,
  // @ts-ignore
  sort?: QuerySelectSubscriptionTypePageSorter,
  // @ts-ignore
  filter?: QuerySelectSubscriptionTypePageFilter
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

    return selectSubscriptionTypePageRequest({
      token,
      query,
      payload,
    })
  })
}
