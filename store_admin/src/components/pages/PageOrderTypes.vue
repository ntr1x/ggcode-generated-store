<script setup lang="ts">
import { useRoute } from 'vue-router';
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useAxiosAutoRequest } from '../../hooks/useAxiosAutoRequest';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import { paymentsRemote } from '../../remotes/paymentsRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import GridOrderTypes from '../grids/GridOrderTypes.vue';

// @ts-ignore
const route = useRoute()
const authStore = useAuthStore()
// @ts-ignore
const security = useSecurityContext()

const orderTypeSelectFilter = reactive({
})

const orderTypeSelectSort = reactive({
})

const orderTypeSelectSelection = ref([])

const orderTypeSelectQuery = useAxiosAutoRequest<any>(paymentsRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  const params: Record<string, any> = {}
  const sort: string[] = []
  setProperty(params, 'sort', sort.length > 0 ? sort : params.sort)

  return {
    method: 'POST',
    url: `/system/public_order_type/select`,
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

const handleRefreshOrderTypeSelect = () => {
  orderTypeSelectQuery.refresh()
  orderTypeSelectSelection.value = []
}

watch(
  [orderTypeSelectFilter, orderTypeSelectSort],
  handleRefreshOrderTypeSelect
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Order Types"
    />
    <GridOrderTypes
      :state="orderTypeSelectQuery.state"
      v-model:selection="orderTypeSelectSelection"
    />
  </div>
</template>
