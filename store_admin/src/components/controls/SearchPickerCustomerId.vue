<script setup lang="ts">
import { computed, ref, watch } from 'vue';
import { debounce } from 'lodash';
import { selectCustomerPageRequest } from '../../requests/selectCustomerPageRequest'
import { useAuthStore } from '../../store/authStore';
import { useAxiosAutoRequest } from '../../hooks/useAxiosAutoRequest';
import PlatformDialogSearch, { type Option } from '../dialogs/PlatformDialogSearch.vue';

type IHaveId = {
  id: string
}

type IHaveName = {
  fullName?: string
}

type IHaveDescription = {
  email?: string
}

export type ResponseDataRow = IHaveId & IHaveName & IHaveDescription

export type ResponseData = {
  content: ResponseDataRow[]
}

const model = defineModel<ResponseDataRow | undefined>()

const query = ref<string | undefined>()

const emit = defineEmits<{
  (e: 'change:option', value: Option): void
}>()

const authStore = useAuthStore()

const response = useAxiosAutoRequest<ResponseData>(async () => {
  const token = await authStore.requireToken()

  const payload = {
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

  return selectCustomerPageRequest({
    token,
    payload
  })
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

function handleQuery(value?: string) {
  query.value = value
}

watch(
  [query],
  debounce(() => {
    response.refresh()
  }, 500)
)

</script>

<template>
  <PlatformDialogSearch
    title="Customer Id"
    :is-loading="response.state.isLoading"
    :is-loaded="response.state.isLoaded"
    :is-failed="response.state.isFailed"
    :options="options"
    v-model="model"
    @change:option="value => emit('change:option', value)"
    @change:query="query => handleQuery(query)"
  />
</template>
