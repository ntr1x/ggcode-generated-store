
import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { selectAgentPageRequest } from '../requests/selectAgentPageRequest'

export type QuerySelectAgentPageProps = {
  // yet nothing
}

export type QuerySelectAgentPageFilter = {
  customerId?: string,
}

export type QuerySelectAgentPageSorter = {
  // yet nothing
}
const mapping: Record<string, any> = {
  customerId: 'customerId',
}

export function useQuerySelectAgentPage(
  // @ts-ignore
  props: QuerySelectAgentPageProps,
  // @ts-ignore
  sort?: QuerySelectAgentPageSorter,
  // @ts-ignore
  filter?: QuerySelectAgentPageFilter
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

    const query: Record<string, any> = {"size":50}
    if (sortArray.length > 0) {
      query.sort = sortArray
    }

    return selectAgentPageRequest({
      token,
      query,
      payload,
    })
  })
}
