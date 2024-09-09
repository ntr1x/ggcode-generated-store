import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { paymentsRemote } from '../remotes/paymentsRemote'

export type QuerySelectOrderStatusPageProps = {
  // yet nothing
}

export type QuerySelectOrderStatusPageFilter = {
  // yet nothing
}

export type QuerySelectOrderStatusPageSorter = {
  // yet nothing
}

const mapping: Record<string, any> = {
  // yet nothing
}

export function useQuerySelectOrderStatusPage(
  // @ts-ignore
  props: QuerySelectOrderStatusPageProps,
  sort?: QuerySelectOrderStatusPageSorter,
  filter?: QuerySelectOrderStatusPageFilter
) {
  const authStore = useAuthStore()

  return useAxiosAutoRequest<any>(paymentsRemote, async () => {
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

    const query: Record<string, any> = {"size":50,"sort":"id,asc"}
    if (sortArray.length > 0) {
      query.sort = sortArray
    }

    return {
      method: 'POST',
      url: `/system/public_order_status/select`,
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
