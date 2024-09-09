import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { eventsRemote } from '../remotes/eventsRemote'

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
  sort?: QuerySelectSubscriptionPageSorter,
  filter?: QuerySelectSubscriptionPageFilter
) {
  const authStore = useAuthStore()

  return useAxiosAutoRequest<any>(eventsRemote, async () => {
    const token = await authStore.requireToken()

    const body: Record<string, any> = {}
    if (filter != null) {
      for (const [k, v] of Object.entries(filter)) {
        if (v != undefined) {
          body[mapping[k]] = v
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

    return {
      method: 'POST',
      url: `/system/public_subscription/select`,
      params: query,
      paramsSerializer: {
        indexes: null
      },
      headers: {
        Authorization: `Bearer ${token}`
      },
      data: body
    }
  })
}
