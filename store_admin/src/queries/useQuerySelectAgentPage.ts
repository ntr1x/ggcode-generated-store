import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { customersRemote } from '../remotes/customersRemote'

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
  sort?: QuerySelectAgentPageSorter,
  filter?: QuerySelectAgentPageFilter
) {
  const authStore = useAuthStore()

  return useAxiosAutoRequest<any>(customersRemote, async () => {
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

    const query: Record<string, any> = {"size":50}
    if (sortArray.length > 0) {
      query.sort = sortArray
    }

    return {
      method: 'POST',
      url: `/system/public_agent/select`,
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
