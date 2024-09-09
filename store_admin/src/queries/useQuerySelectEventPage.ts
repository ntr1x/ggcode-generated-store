import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { eventsRemote } from '../remotes/eventsRemote'

export type QuerySelectEventPageProps = {
  // yet nothing
}

export type QuerySelectEventPageFilter = {
  eventSource?: string,
  eventType?: string,
  eventTopic?: string,
}

export type QuerySelectEventPageSorter = {
  id?: 'asc' | 'desc',
  ceSource?: 'asc' | 'desc',
  ceType?: 'asc' | 'desc',
  topic?: 'asc' | 'desc',
  createdAt?: 'asc' | 'desc',
}

const mapping: Record<string, any> = {
  eventSource: 'ceSource',
  eventType: 'ceType',
  eventTopic: 'topic',
}

export function useQuerySelectEventPage(
  // @ts-ignore
  props: QuerySelectEventPageProps,
  sort?: QuerySelectEventPageSorter,
  filter?: QuerySelectEventPageFilter
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

    const query: Record<string, any> = {"size":50,"sort":"createdAt,desc"}
    if (sortArray.length > 0) {
      query.sort = sortArray
    }

    return {
      method: 'POST',
      url: `/system/public_event/select`,
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
