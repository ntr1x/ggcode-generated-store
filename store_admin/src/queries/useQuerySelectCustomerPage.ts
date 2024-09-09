import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { customersRemote } from '../remotes/customersRemote'

export type QuerySelectCustomerPageProps = {
  // yet nothing
}

export type QuerySelectCustomerPageFilter = {
  // yet nothing
}

export type QuerySelectCustomerPageSorter = {
  // yet nothing
}

const mapping: Record<string, any> = {
  // yet nothing
}

export function useQuerySelectCustomerPage(
  // @ts-ignore
  props: QuerySelectCustomerPageProps,
  sort?: QuerySelectCustomerPageSorter,
  filter?: QuerySelectCustomerPageFilter
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

    const query: Record<string, any> = {"size":50,"sort":"fullName"}
    if (sortArray.length > 0) {
      query.sort = sortArray
    }

    return {
      method: 'POST',
      url: `/system/public_customer/select`,
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
