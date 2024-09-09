import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { paymentsRemote } from '../remotes/paymentsRemote'

export type QuerySelectSourceTypePageProps = {
  // yet nothing
}

export type QuerySelectSourceTypePageFilter = {
  // yet nothing
}

export type QuerySelectSourceTypePageSorter = {
  // yet nothing
}

const mapping: Record<string, any> = {
  // yet nothing
}

export function useQuerySelectSourceTypePage(
  // @ts-ignore
  props: QuerySelectSourceTypePageProps,
  sort?: QuerySelectSourceTypePageSorter,
  filter?: QuerySelectSourceTypePageFilter
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

    const query: Record<string, any> = {}
    if (sortArray.length > 0) {
      query.sort = sortArray
    }

    return {
      method: 'POST',
      url: `/system/public_source_type/select`,
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
