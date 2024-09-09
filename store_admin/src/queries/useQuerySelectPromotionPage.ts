import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { productsRemote } from '../remotes/productsRemote'

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
  sort?: QuerySelectPromotionPageSorter,
  filter?: QuerySelectPromotionPageFilter
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
      url: `/system/public_promotion/select`,
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
