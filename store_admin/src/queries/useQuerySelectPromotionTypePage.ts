
import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { selectPromotionTypePageRequest } from '../requests/selectPromotionTypePageRequest'

export type QuerySelectPromotionTypePageProps = {
  // yet nothing
}

export type QuerySelectPromotionTypePageFilter = {
  // yet nothing
}

export type QuerySelectPromotionTypePageSorter = {
  // yet nothing
}

export function useQuerySelectPromotionTypePage(
  // @ts-ignore
  props: QuerySelectPromotionTypePageProps,
  // @ts-ignore
  sort?: QuerySelectPromotionTypePageSorter,
  // @ts-ignore
  filter?: QuerySelectPromotionTypePageFilter
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

    const query: Record<string, any> = {}
    if (sortArray.length > 0) {
      query.sort = sortArray
    }

    return selectPromotionTypePageRequest({
      token,
      query,
      payload,
    })
  })
}
