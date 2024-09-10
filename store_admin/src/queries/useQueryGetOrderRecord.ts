
import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { getOrderRequest } from '../requests/getOrderRequest'

export type QueryGetOrderRecordProps = {
  orderId?: string
}

export type QueryGetOrderRecordFilter = {
  // yet nothing
}

export type QueryGetOrderRecordSorter = {
  // yet nothing
}

export function useQueryGetOrderRecord(
  // @ts-ignore
  props: QueryGetOrderRecordProps,
  // @ts-ignore
  sort?: QueryGetOrderRecordSorter,
  // @ts-ignore
  filter?: QueryGetOrderRecordFilter
) {
  const authStore = useAuthStore()

  return useAxiosAutoRequest<any>(async () => {
    const token = await authStore.requireToken()

    return getOrderRequest({
      token,
      orderId: props.orderId!,
    })
  })
}
