import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { customersRemote } from '../remotes/customersRemote'

export type QueryGetAgentRecordProps = {
  agentId?: string
}

export type QueryGetAgentRecordFilter = {
  // yet nothing
}

export type QueryGetAgentRecordSorter = {
  // yet nothing
}

const mapping: Record<string, any> = {
  // yet nothing
}

export function useQueryGetAgentRecord(
  // @ts-ignore
  props: QueryGetAgentRecordProps,
  sort?: QueryGetAgentRecordSorter,
  filter?: QueryGetAgentRecordFilter
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

    const query: Record<string, any> = {}
    if (sortArray.length > 0) {
      query.sort = sortArray
    }

    return {
      method: 'GET',
      url: `/system/public_agent/i/${props.agentId}`,
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
