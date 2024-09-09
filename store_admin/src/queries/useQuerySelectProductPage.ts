import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { productsRemote } from '../remotes/productsRemote'
import { StructurePublicProductPage } from '../structures/StructurePublicProductPage'

export type QuerySelectProductPageProps = {
  // yet nothing
}

export type QuerySelectProductPageFilter = {
  categoryId?: string,
}

export type QuerySelectProductPageSorter = {
  id?: 'asc' | 'desc',
  name?: 'asc' | 'desc',
}

const mapping: Record<string, any> = {
  categoryId: 'categoryId',
}

export function useQuerySelectProductPage(
  // @ts-ignore
  props: QuerySelectProductPageProps,
  sort?: QuerySelectProductPageSorter,
  filter?: QuerySelectProductPageFilter
) {
  const authStore = useAuthStore()

  return useAxiosAutoRequest<StructurePublicProductPage>(productsRemote, async () => {
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
      url: `/system/public_product/select`,
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
