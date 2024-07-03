<script setup lang="ts">
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useRoute } from 'vue-router';
import { useAuthStore } from '../../store/authStore';
import { useAxiosRequest } from '../../hooks/useAxiosRequest';
import { paymentsRemote } from '../../remotes/paymentsRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import GridPaymentTypes from '../grids/GridPaymentTypes.vue';

// @ts-ignore
const route = useRoute()
const authStore = useAuthStore()

const paymentTypeSelectFilter = reactive({
})

const paymentTypeSelectSort = reactive({
})

const paymentTypeSelectSelection = ref([])

const paymentTypeSelectQuery = useAxiosRequest<any>(paymentsRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  const params = {}
  const sort: string[] = []
  setProperty(params, 'sort', sort.length > 0 ? sort : undefined)

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

watch(
  [paymentTypeSelectFilter, paymentTypeSelectSort],
  () => {
    paymentTypeSelectQuery.refresh()
    paymentTypeSelectSelection.value = []
  }
)
</script>

<template>
  <div class="flex flex-col overflow-hidden">
    <SectionHeading tag="h1" title="Payment Types" />
    <GridPaymentTypes
      :state="paymentTypeSelectQuery.state"
      v-model:selection="paymentTypeSelectSelection"
    />
  </div>
</template>
