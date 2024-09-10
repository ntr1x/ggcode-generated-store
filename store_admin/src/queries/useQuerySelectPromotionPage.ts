
import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { selectPromotionPageRequest } from '../requests/selectPromotionPageRequest'

export type QuerySelectPromotionPageProps = {
  // yet nothing
}

export type QuerySelectPromotionPageFilter = {
  promotionType?: string,
}

export type QuerySelectPromotionPageSorter = {
  // yet nothing
}
const mapping: Record<string, any> = {
  promotionType: 'typeId',
}

export function useQuerySelectPromotionPage(
  // @ts-ignore
  props: QuerySelectPromotionPageProps,
  // @ts-ignore
  sort?: QuerySelectPromotionPageSorter,
  // @ts-ignore
  filter?: QuerySelectPromotionPageFilter
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

    const query: Record<string, any> = {}
    if (sortArray.length > 0) {
      query.sort = sortArray
    }

    return selectPromotionPageRequest({
      token,
      query,
      payload,
    })
  })
}
