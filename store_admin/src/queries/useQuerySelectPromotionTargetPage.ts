
import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { selectPromotionTargetPageRequest } from '../requests/selectPromotionTargetPageRequest'

export type QuerySelectPromotionTargetPageProps = {
  // yet nothing
}

export type QuerySelectPromotionTargetPageFilter = {
  productId?: string,
  promotionId?: string,
}

export type QuerySelectPromotionTargetPageSorter = {
  // yet nothing
}
const mapping: Record<string, any> = {
  productId: 'productId',
  promotionId: 'promotionId',
}

export function useQuerySelectPromotionTargetPage(
  // @ts-ignore
  props: QuerySelectPromotionTargetPageProps,
  // @ts-ignore
  sort?: QuerySelectPromotionTargetPageSorter,
  // @ts-ignore
  filter?: QuerySelectPromotionTargetPageFilter
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

    return selectPromotionTargetPageRequest({
      token,
      query,
      payload,
    })
  })
}
