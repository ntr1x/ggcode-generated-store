import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { productsRemote } from '../remotes/productsRemote'
import { StructurePublicProductRecord } from '../structures/StructurePublicProductRecord'

export type QueryGetProductRecordProps = {
  productId?: string
}

export type QueryGetProductRecordFilter = {
  // yet nothing
}

export type QueryGetProductRecordSorter = {
  // yet nothing
}

const mapping: Record<string, any> = {
  // yet nothing
}

export function useQueryGetProductRecord(
  // @ts-ignore
  props: QueryGetProductRecordProps,
  sort?: QueryGetProductRecordSorter,
  filter?: QueryGetProductRecordFilter
) {
  const authStore = useAuthStore()

  return useAxiosAutoRequest<StructurePublicProductRecord>(productsRemote, async () => {
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
      method: 'GET',
      url: `/system/public_product/i/${props.productId}`,
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
