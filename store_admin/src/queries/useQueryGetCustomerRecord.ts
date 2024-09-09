import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { customersRemote } from '../remotes/customersRemote'

export type QueryGetCustomerRecordProps = {
  customerId?: string
}

export type QueryGetCustomerRecordFilter = {
  // yet nothing
}

export type QueryGetCustomerRecordSorter = {
  // yet nothing
}

const mapping: Record<string, any> = {
  // yet nothing
}

export function useQueryGetCustomerRecord(
  // @ts-ignore
  props: QueryGetCustomerRecordProps,
  sort?: QueryGetCustomerRecordSorter,
  filter?: QueryGetCustomerRecordFilter
) {
  const authStore = useAuthStore()

  return useAxiosAutoRequest<any>(customersRemote, async () => {
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
      url: `/system/public_customer/i/${props.customerId}`,
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
