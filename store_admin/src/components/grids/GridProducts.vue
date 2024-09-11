<script setup lang="ts">
import Column from 'primevue/column';
import DataTable from 'primevue/datatable';
import { type State } from '../../hooks/useAxiosRequest';
import SpeedDial from 'primevue/speeddial';
import { useActionUpdateProduct } from '../../actions/useActionUpdateProduct'
import SymbolId from '../symbols/SymbolId.vue'
import SymbolPrice from '../symbols/SymbolPrice.vue'
import { StructurePublicProductPage } from '../../structures/StructurePublicProductPage'

export type GridProductsRecord = StructurePublicProductPage['content'][0]

export type GridProductsPage = {
  content: GridProductsRecord[]
}

export type GridProductsProps = {
  state: State<GridProductsPage>,
  scrollable?: boolean,
  scrollHeight?: string,
  hideId?: boolean,
  hideName?: boolean,
  hideCategory?: boolean,
  hidePrice?: boolean,
}

const selection = defineModel<GridProductsRecord[]>('selection')

withDefaults(defineProps<GridProductsProps>(), {
  scrollable: true,
  scrollHeight: 'flex',
})

// @ts-ignore
const emit = defineEmits<{
  (e: 'refresh'): void
}>()

const actionUpdateProduct = useActionUpdateProduct()

actionUpdateProduct.emitter
  .on('success', (data: any) => {
    console.log(data)
    emit('refresh')
  })
  .on('failure', (error: any) => {
    console.error(error)
  })

async function handleProductEdit(item: GridProductsRecord) {
  actionUpdateProduct.execute({ productId: item.id })
}

const buildHandles = (item: GridProductsRecord) => ([
  {
    label: 'Product Edit',
    icon: 'pi pi-pencil',
    command: () => {
      handleProductEdit(item)
    }
  },
])

</script>

<template>
  <DataTable
    :loading="state.isLoading"
    v-model:selection="selection"
    :value="state.data?.content || []"
    :scrollable="scrollable"
    :scrollHeight="scrollHeight"
    :class="{
      'overflow-hidden': scrollable
    }"
    :pt="{
      column: {
        bodyCell: {
          class: 'text-left border-0 border-b border-solid border-r border-b p-2 border-surface-200 dark:border-surface-700'
        },
        headerCell: {
          class: 'font-normal text-sm text-left border-t-0 border-y border-r border-0 border-b border-solid p-2 bg-surface-50 text-surface-700 dark:text-white/80 dark:bg-surface-800 border-surface-200 dark:border-surface-700 focus-visible:outline-none focus-visible:outline-offset-0 focus-visible:ring focus-visible:ring-inset focus-visible:ring-primary-400/50 dark:focus-visible:ring-primary-300/50'
        }
      }
    }"
  >
    <Column selectionMode="multiple" headerStyle="width: 3rem" />
    <Column header="#" headerStyle="width:3rem">
      <template #body="slotProps">
        <div v-text="slotProps.index + 1"></div>
      </template>
    </Column>
    <Column v-if="!hideId" header="Id">
      <template #body="slotProps">
        <SymbolId :value="slotProps.data.id" :label="'REF'" :route="`/admin/products/${slotProps.data.id}`"/>
      </template>
    </Column>
    <Column v-if="!hideName" field="name" header="Name" />
    <Column v-if="!hideCategory" field="category.name" header="Category" />
    <Column v-if="!hidePrice" header="Price">
      <template #body="slotProps">
        <SymbolPrice :value="slotProps.data.price"/>
      </template>
    </Column>
    <Column header="" class="w-1">
      <template #body="slotProps">
        <div class="relative flex w-8 justify-end">
          <SpeedDial :model="buildHandles(slotProps.data)" direction="left" class="relative" />
        </div>
      </template>
    </Column>
  </DataTable>
</template>
