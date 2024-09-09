import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { productsRemote } from '../remotes/productsRemote'

export type QuerySelectRegionPageProps = {
  // yet nothing
}

export type QuerySelectRegionPageFilter = {
  // yet nothing
}

export type QuerySelectRegionPageSorter = {
  // yet nothing
}

const mapping: Record<string, any> = {
  // yet nothing
}

export function useQuerySelectRegionPage(
  // @ts-ignore
  props: QuerySelectRegionPageProps,
  sort?: QuerySelectRegionPageSorter,
  filter?: QuerySelectRegionPageFilter
) {
  const authStore = useAuthStore()

  return useAxiosAutoRequest<any>(productsRemote, async () => {
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
      url: `/system/public_region/select`,
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
