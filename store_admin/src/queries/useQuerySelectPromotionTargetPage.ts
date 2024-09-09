import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { paymentsRemote } from '../remotes/paymentsRemote'

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
  sort?: QuerySelectPromotionTargetPageSorter,
  filter?: QuerySelectPromotionTargetPageFilter
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
      url: `/system/public_promotion_target/select`,
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
