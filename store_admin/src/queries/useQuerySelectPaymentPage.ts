
import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { selectPaymentPageRequest } from '../requests/selectPaymentPageRequest'

export type QuerySelectPaymentPageProps = {
  // yet nothing
}

export type QuerySelectPaymentPageFilter = {
  orderId?: string,
  customerId?: string,
  paymentType?: string,
  paymentStatus?: string,
  orderType?: string,
  orderStatus?: string,
}

export type QuerySelectPaymentPageSorter = {
  // yet nothing
}
const mapping: Record<string, any> = {
  orderId: 'orderId',
  customerId: 'customerId',
  paymentType: 'paymentTypeId',
  paymentStatus: 'paymentStatusId',
  orderType: 'order.orderTypeId',
  orderStatus: 'order.orderStatusId',
}

export function useQuerySelectPaymentPage(
  // @ts-ignore
  props: QuerySelectPaymentPageProps,
  // @ts-ignore
  sort?: QuerySelectPaymentPageSorter,
  // @ts-ignore
  filter?: QuerySelectPaymentPageFilter
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

    const query: Record<string, any> = {"size":50,"sort":"id,asc"}
    if (sortArray.length > 0) {
      query.sort = sortArray
    }

    return selectPaymentPageRequest({
      token,
      query,
      payload,
    })
  })
}
