
import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { selectShopPageRequest } from '../requests/selectShopPageRequest'

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
  // @ts-ignore
  sort?: QuerySelectShopPageSorter,
  // @ts-ignore
  filter?: QuerySelectShopPageFilter
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

    const query: Record<string, any> = {"size":50,"sort":"name,asc"}
    if (sortArray.length > 0) {
      query.sort = sortArray
    }

    return selectShopPageRequest({
      token,
      query,
      payload,
    })
  })
}
