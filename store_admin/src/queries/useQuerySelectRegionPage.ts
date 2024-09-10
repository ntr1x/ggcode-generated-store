
import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { selectRegionPageRequest } from '../requests/selectRegionPageRequest'

export type QuerySelectRegionPageProps = {
  // yet nothing
}

export type QuerySelectRegionPageFilter = {
  // yet nothing
}

export type QuerySelectRegionPageSorter = {
  // yet nothing
}

export function useQuerySelectRegionPage(
  // @ts-ignore
  props: QuerySelectRegionPageProps,
  // @ts-ignore
  sort?: QuerySelectRegionPageSorter,
  // @ts-ignore
  filter?: QuerySelectRegionPageFilter
) {
  const authStore = useAuthStore()

  return useAxiosAutoRequest<any>(async () => {
    const token = await authStore.requireToken()
    const payload: Record<string, any> = {}
    
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

    return selectRegionPageRequest({
      token,
      query,
      payload,
    })
  })
}
