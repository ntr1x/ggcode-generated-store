<script setup lang="ts" generic="T">
import Column from 'primevue/column';
import DataTable from 'primevue/datatable';
import { type State } from '../../hooks/useAxiosRequest';
import SymbolId from '../symbols/SymbolId.vue'
import SymbolEventSourceInfo from '../symbols/SymbolEventSourceInfo.vue'
import SymbolEventTopicInfo from '../symbols/SymbolEventTopicInfo.vue'
import SymbolEventTypeInfo from '../symbols/SymbolEventTypeInfo.vue'
import SymbolDatetime from '../symbols/SymbolDatetime.vue'

export type ResponseData = {
  content: T[]
}

export type GridEventsProps = {
  state: State<ResponseData>,
  scrollable?: boolean,
  scrollHeight?: string,
  hideId?: boolean,
  hideEventSource?: boolean,
  hideEventTopic?: boolean,
  hideEventType?: boolean,
  hideContentType?: boolean,
  hideVersion?: boolean,
  hideCreatedAt?: boolean,
}

const selection = defineModel<T[]>('selection')

withDefaults(defineProps<GridEventsProps>(), {
  scrollable: true,
  scrollHeight: 'flex',
})

</script>

<template>
  <DataTable
    v-if="state.isLoaded"
    v-model:selection="selection"
    :value="state.data?.content || []"
    :scrollable="scrollable"
    :scrollHeight="scrollHeight"
    :class="{
      'overflow-hidden': scrollable
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
        <SymbolId :value="slotProps.data.id" :label="'REF'" :route="`/admin/events/${slotProps.data.id}`"/>
      </template>
    </Column>
    <Column v-if="!hideEventSource" header="Event Source">
      <template #body="slotProps">
        <SymbolEventSourceInfo :value="slotProps.data.ceSourceInfo"/>
      </template>
    </Column>
    <Column v-if="!hideEventTopic" header="Event Topic">
      <template #body="slotProps">
        <SymbolEventTopicInfo :value="slotProps.data.topicInfo"/>
      </template>
    </Column>
    <Column v-if="!hideEventType" header="Event Type">
      <template #body="slotProps">
        <SymbolEventTypeInfo :value="slotProps.data.ceTypeInfo"/>
      </template>
    </Column>
    <Column v-if="!hideContentType" field="contentType" header="Content Type" />
    <Column v-if="!hideVersion" field="ceSpecification" header="Version" />
    <Column v-if="!hideCreatedAt" header="Created At">
      <template #body="slotProps">
        <SymbolDatetime :value="slotProps.data.createdAt"/>
      </template>
    </Column>
  </DataTable>
</template>
