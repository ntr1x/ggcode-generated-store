import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { productsRemote } from '../remotes/productsRemote'

export type QuerySelectShopPageProps = {
  // yet nothing
}

export type QuerySelectShopPageFilter = {
  regionId?: string,
}

export type QuerySelectShopPageSorter = {
  // yet nothing
}

const mapping: Record<string, any> = {
  regionId: 'regionId',
}

export function useQuerySelectShopPage(
  // @ts-ignore
  props: QuerySelectShopPageProps,
  sort?: QuerySelectShopPageSorter,
  filter?: QuerySelectShopPageFilter
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

    const query: Record<string, any> = {"size":50,"sort":"name,asc"}
    if (sortArray.length > 0) {
      query.sort = sortArray
    }

    return {
      method: 'POST',
      url: `/system/public_shop/select`,
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
