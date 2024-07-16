<script setup lang="ts">
import { computed, ref, watch } from 'vue';
import { debounce } from 'lodash';
import { customersRemote } from '../../remotes/customersRemote'
import { useAuthStore } from '../../store/authStore';
import { useAxiosManualRequest } from '../../hooks/useAxiosManualRequest';
import AutoComplete, { type AutoCompleteCompleteEvent } from 'primevue/autocomplete';

type IHaveId = {
  id: string
}

type IHaveName = {
  fullName: string
}

type IHaveDescription = {
  email: string
}

export type ResponseDataRow = IHaveId & IHaveName & IHaveDescription

export type ResponseData = {
  content: ResponseDataRow[]
}

const query = ref<string | undefined>('')

const model = defineModel('customerId')

const selected = ref<{
  value: string,
}>()

const authStore = useAuthStore()

const response = useAxiosManualRequest<ResponseData>(customersRemote, async () => {
  const token = await authStore.requireToken()

  const requestData = {
    $where: query.value == null ? undefined : [
      {
        $gt: {
          a: {
            func: {
              similarity: {
                a: { prop: "fullName" },
                b: { value: query.value }
              }
            }
          },
          b: { value: '0.2' }
        }
      }
    ],
    $order: query.value == null ? undefined : [
      {
        direction: "DESC",
        func: {
          similarity: {
            a: { prop: "fullName" },
            b: { value: query.value }
          }
        }
      }
    ]
  }

  return {
    method: 'POST',
    url: '/system/public_customer/select',
    data: Object.assign({}, requestData),
    params: {"size":50,"sort":"fullName"},
    headers: {
      Authorization: `Bearer ${token}`
    }
  }
})

const options = computed(() => {
  const items = response.state.data?.content || []
  return items.map(item => ({
    key: `item-${item.id}`,
    title: item.fullName,
    description: item.email,
    value: item.id
  }))
})

watch([selected], ([s]) => {
  model.value = s?.value
})

const refreshDebounced = debounce(() => {
  response.refresh()
}, 500);

const handleQuery = (event: AutoCompleteCompleteEvent) => {
  const value = event.query.trim()
  query.value = value.length == 0 ? undefined : value
  refreshDebounced()
}

</script>

<template>
  <AutoComplete
    v-model="selected"
    optionLabel="title"
    placeholder="Customer Id"
    dropdown
    :loading="response.state.isLoading"
    :suggestions="options"
    @complete="handleQuery"
    class="w-full"
    pt:input:class="font-sans text-base leading-none appearance-none rounded-md rounded-tr-none rounded-br-none m-0 p-3 text-surface-700 dark:text-white/80 border bg-surface-0 dark:bg-surface-900  border-surface-300 dark:border-surface-700 focus:outline-none focus:outline-offset-0 focus:ring focus:ring-primary-400/50 dark:focus:ring-primary-300/50 transition-colors duration-200 w-[100%]"
  >
    <template #option="slotProps">
      <div class="py-1">
        <div v-html="slotProps.option.title"></div>
        <div class="mt-1 text-sm" v-html="slotProps.option.description"></div>
      </div>
    </template>
  </AutoComplete>
</template>
