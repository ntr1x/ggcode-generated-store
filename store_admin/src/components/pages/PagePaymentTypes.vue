<script setup lang="ts">
import { useRoute } from 'vue-router';
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useAxiosAutoRequest } from '../../hooks/useAxiosAutoRequest';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import { paymentsRemote } from '../../remotes/paymentsRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import GridPaymentTypes from '../grids/GridPaymentTypes.vue';

// @ts-ignore
const route = useRoute()
const authStore = useAuthStore()
// @ts-ignore
const security = useSecurityContext()

const paymentTypeSelectFilter = reactive({
})

const paymentTypeSelectSort = reactive({
})

const paymentTypeSelectSelection = ref([])

const paymentTypeSelectQuery = useAxiosAutoRequest<any>(paymentsRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  const params: Record<string, any> = {}
  const sort: string[] = []
  setProperty(params, 'sort', sort.length > 0 ? sort : params.sort)

  return {
    method: 'POST',
    url: `/system/public_payment_type/select`,
    params,
    data,
    paramsSerializer: {
      indexes: null
    },
    headers: {
      Authorization: `Bearer ${token}`
    }
  }
})

const handleRefreshPaymentTypeSelect = () => {
  paymentTypeSelectQuery.refresh()
  paymentTypeSelectSelection.value = []
}

watch(
  [paymentTypeSelectFilter, paymentTypeSelectSort],
  handleRefreshPaymentTypeSelect
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Payment Types"
    />
    <GridPaymentTypes
      :state="paymentTypeSelectQuery.state"
      v-model:selection="paymentTypeSelectSelection"
    />
  </div>
</template>
