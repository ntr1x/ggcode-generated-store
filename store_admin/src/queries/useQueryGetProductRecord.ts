
import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { getProductRequest } from '../requests/getProductRequest'
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

export function useQueryGetProductRecord(
  // @ts-ignore
  props: QueryGetProductRecordProps,
  // @ts-ignore
  sort?: QueryGetProductRecordSorter,
  // @ts-ignore
  filter?: QueryGetProductRecordFilter
) {
  const authStore = useAuthStore()

  return useAxiosAutoRequest<StructurePublicProductRecord>(async () => {
    const token = await authStore.requireToken()

    return getProductRequest({
      token,
      productId: props.productId!,
    })
  })
}
