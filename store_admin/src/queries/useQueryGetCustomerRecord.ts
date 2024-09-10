
import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { getCustomerRequest } from '../requests/getCustomerRequest'

export type QueryGetCustomerRecordProps = {
  customerId?: string
}

export type QueryGetCustomerRecordFilter = {
  // yet nothing
}

export type QueryGetCustomerRecordSorter = {
  // yet nothing
}

export function useQueryGetCustomerRecord(
  // @ts-ignore
  props: QueryGetCustomerRecordProps,
  // @ts-ignore
  sort?: QueryGetCustomerRecordSorter,
  // @ts-ignore
  filter?: QueryGetCustomerRecordFilter
) {
  const authStore = useAuthStore()

  return useAxiosAutoRequest<any>(async () => {
    const token = await authStore.requireToken()

    return getCustomerRequest({
      token,
      customerId: props.customerId!,
    })
  })
}
