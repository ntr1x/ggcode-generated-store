<script setup lang="ts" generic="T">
import { ref, reactive, shallowRef } from 'vue';
import Menubar from 'primevue/menubar'
import Button from 'primevue/button'
import Chip from 'primevue/chip'
import Menu from 'primevue/menu';
import { type Option } from '../dialogs/PlatformDialogFilter.vue';
import { useModalStore } from '../../store/modalStore';
import { StructurePublicGuidePage } from '../../structures/StructurePublicGuidePage'
import SelectPickerGuideSubject from '../controls/SelectPickerGuideSubject.vue'
import { useActionGuideRemoveSelected } from '../../actions/useActionGuideRemoveSelected'
import { useActionCreateGuide } from '../../actions/useActionCreateGuide'

export type SelectionGuidesRecord = StructurePublicGuidePage['content'][0]

const modalStore = useModalStore()

const emit = defineEmits<{
  (e: 'refresh'): void
}>()

const filterBySubjectId = defineModel('filterBySubjectId')
const sortById = defineModel<'asc' | 'desc' | undefined>('sortById')
const sortByName = defineModel<'asc' | 'desc' | undefined>('sortByName')
const sortByPosition = defineModel<'asc' | 'desc' | undefined>('sortByPosition')
const sortBySubjectId = defineModel<'asc' | 'desc' | undefined>('sortBySubjectId')
const selection = defineModel<SelectionGuidesRecord[]>('selection', { required: true })

const filters = reactive<Record<string, Option | undefined>>({
  subjectId: undefined,
})

const sortings = reactive<Record<string, 'asc' | 'desc' | undefined>>({
  id: undefined,
  name: undefined,
  position: undefined,
  subjectId: undefined,
})

const filtersMenuRef = ref()

const filtersMenuItems = ref([
  {
    label: 'By Property',
    items: [
      {
        label: 'Subject Id',
        icon: 'pi pi-plus-circle',
        command: () => [
          modalStore.openModal(() => ({
            component: shallowRef(SelectPickerGuideSubject),
            props: {},
            handlers: {
              ['update:model-value'](value: string) {
                filterBySubjectId.value = value
              },
              ['change:option'](option: Option) {
                filters.subjectId = option
              }
            }
          }))
        ]
      },
    ]
  }
])

const sortersMenuRef = ref()

const sortersMenuItems = ref([
  {
    label: 'By Column',
    items: [
      {
        label: 'Id (Ascending)',
        icon: 'pi pi-sort-alpha-down',
        command: () => {
          sortings.id = 'asc'
          sortById.value = 'asc'
        }
      },
      {
        icon: 'pi pi-sort-alpha-up',
        label: 'Id (Descending)',
        command: () => {
          sortings.id = 'desc'
          sortById.value = 'desc'
        }
      },
      {
        label: 'Name (Ascending)',
        icon: 'pi pi-sort-alpha-down',
        command: () => {
          sortings.name = 'asc'
          sortByName.value = 'asc'
        }
      },
      {
        icon: 'pi pi-sort-alpha-up',
        label: 'Name (Descending)',
        command: () => {
          sortings.name = 'desc'
          sortByName.value = 'desc'
        }
      },
      {
        label: 'Position (Ascending)',
        icon: 'pi pi-sort-alpha-down',
        command: () => {
          sortings.position = 'asc'
          sortByPosition.value = 'asc'
        }
      },
      {
        icon: 'pi pi-sort-alpha-up',
        label: 'Position (Descending)',
        command: () => {
          sortings.position = 'desc'
          sortByPosition.value = 'desc'
        }
      },
      {
        label: 'Subject Id (Ascending)',
        icon: 'pi pi-sort-alpha-down',
        command: () => {
          sortings.subjectId = 'asc'
          sortBySubjectId.value = 'asc'
        }
      },
      {
        icon: 'pi pi-sort-alpha-up',
        label: 'Subject Id (Descending)',
        command: () => {
          sortings.subjectId = 'desc'
          sortBySubjectId.value = 'desc'
        }
      },
    ]
  }
])

const actionGuideRemoveSelected = useActionGuideRemoveSelected()
const actionCreateGuide = useActionCreateGuide()

actionGuideRemoveSelected.emitter
  .on('success', (data: any) => {
    console.log(data)
    emit('refresh')
  })
  .on('failure', (error: any) => {
    console.error(error)
  })

actionCreateGuide.emitter
  .on('success', (data: any) => {
    console.log(data)
    emit('refresh')
  })
  .on('failure', (error: any) => {
    console.error(error)
  })

async function handleRemoveSelected() {
  actionGuideRemoveSelected.execute({
    items: selection.value,
  })
}

async function handleCreateGuide() {
  actionCreateGuide.execute()
}
</script>

<template>
  <Menubar>
    <template #start>
      <div class="flex flex-wrap">
        <div class="flex items-center group-filters">
          <div class="flex flex-none">
            <Button text label="Filters" icon="pi pi-filter" @click="filtersMenuRef.toggle($event)" />
            <Menu ref="filtersMenuRef" id="overlay_menu" :model="filtersMenuItems" :popup="true" />
          </div>
          <div class="flex flex-wrap flex-1">
            <Chip v-if="filterBySubjectId !== undefined" removable @remove="filterBySubjectId = undefined" class="p-1 ms-2 my-1 md:whitespace-nowrap">
              <span><b>Subject Id: </b><span v-text="filters.subjectId?.title || filterBySubjectId"></span></span>
            </Chip>
          </div>
        </div>
        <div class="flex items-center group-sort">
          <div class="flex flex-none">
            <Button text label="Sort" icon="pi pi-sort" @click="sortersMenuRef.toggle($event)" />
            <Menu ref="sortersMenuRef" id="overlay_menu" :model="sortersMenuItems" :popup="true" />
          </div>
          <div class="flex flex-wrap flex-1">
            <Chip v-if="sortById !== undefined" removable @remove="sortById = undefined" class="p-1 ms-2 my-1 md:whitespace-nowrap">
              <span><b>Id: </b><span v-text="sortings.id || sortById"></span></span>
            </Chip>
            <Chip v-if="sortByName !== undefined" removable @remove="sortByName = undefined" class="p-1 ms-2 my-1 md:whitespace-nowrap">
              <span><b>Name: </b><span v-text="sortings.name || sortByName"></span></span>
            </Chip>
            <Chip v-if="sortByPosition !== undefined" removable @remove="sortByPosition = undefined" class="p-1 ms-2 my-1 md:whitespace-nowrap">
              <span><b>Position: </b><span v-text="sortings.position || sortByPosition"></span></span>
            </Chip>
            <Chip v-if="sortBySubjectId !== undefined" removable @remove="sortBySubjectId = undefined" class="p-1 ms-2 my-1 md:whitespace-nowrap">
              <span><b>Subject Id: </b><span v-text="sortings.subjectId || sortBySubjectId"></span></span>
            </Chip>
          </div>
        </div>
        <div class="flex items-center group-sort">
          <div class="flex flex-none">
            <Button text label="Refresh" icon="pi pi-refresh" @click="emit('refresh')" />
          </div>
        </div>
      </div>
    </template>
    <template #end>
      <div class="flex flex-wrap">
        <div class="flex items-center group-actions">
          <Button
            v-if="selection.length > 0"
            text
            label="Remove Selected"
            class="ms-2"
            severity="danger"
            @click="handleRemoveSelected"
          />
          <Button
            text
            label="Create Guide"
            class="ms-2"
            @click="handleCreateGuide"
          />
        </div>
      </div>
    </template>
  </Menubar>
</template>
