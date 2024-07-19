<script setup lang="ts" generic="T extends { id: string }">
import { ref, reactive } from 'vue';
import Menubar from 'primevue/menubar'
import Button from 'primevue/button'
import Chip from 'primevue/chip'
import Menu from 'primevue/menu';
import { useActionGuideSubjectRemoveSelected } from '../../actions/useActionGuideSubjectRemoveSelected'
import { useActionCreateGuideSubject } from '../../actions/useActionCreateGuideSubject'

const emit = defineEmits<{
  (e: 'refresh'): void
}>()

const sortById = defineModel<'asc' | 'desc' | undefined>('sortById')
const sortByName = defineModel<'asc' | 'desc' | undefined>('sortByName')
const sortByPosition = defineModel<'asc' | 'desc' | undefined>('sortByPosition')
const selection = defineModel<T[]>('selection', { required: true })

const sortings = reactive<Record<string, 'asc' | 'desc' | undefined>>({
  id: undefined,
  name: undefined,
  position: undefined,
})

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
    ]
  }
])

const actionGuideSubjectRemoveSelected = useActionGuideSubjectRemoveSelected()
const actionCreateGuideSubject = useActionCreateGuideSubject()

async function handleRemoveSelected() {
  try {
    await actionGuideSubjectRemoveSelected.execute(selection.value.map(item => ({ id: item.id })))
    emit('refresh')
  } catch (e) {
    console.log(e)
  }
}

async function handleCreateGuideSubject() {
  try {
    actionCreateGuideSubject.execute(undefined, {
      success: (data: any) => {
        console.log(data)
        emit('refresh')
      },
      failure: (error: any) => {
        console.error(error)
        // TODO @framework: Implement
        
      }
    })
    emit('refresh')
  } catch (e) {
    console.log(e)
  }
}
</script>

<template>
  <Menubar>
    <template #start>
      <div class="flex flex-wrap">
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
            label="Create Guide Subject"
            class="ms-2"
            @click="handleCreateGuideSubject"
          />
        </div>
      </div>
    </template>
  </Menubar>
</template>
