<script setup lang="ts">
import { EditorView, basicSetup } from "codemirror"
import { EditorState, Compartment } from "@codemirror/state"
import { lintGutter, linter } from '@codemirror/lint'
import { type ViewUpdate } from "@codemirror/view"
import { json, jsonParseLinter } from "@codemirror/lang-json"
import { onMounted } from "vue";
import { ref } from "vue";

let editor = ref<EditorView | undefined>()
let editorRef = ref()

let language = new Compartment

const model = defineModel<string | undefined>()

onMounted(() => {
  let state = EditorState.create({
    doc: model.value,
    extensions: [
      basicSetup,
      language.of(json()),
      linter(jsonParseLinter()),
      lintGutter(),
      EditorView.updateListener.of((update: ViewUpdate): void => {
        if (update.docChanged) {
          model.value = update.state.doc.toString()
        }
      })
    ]
  })

  const view =  new EditorView({
    state,
    parent: editorRef.value
  })

  editor.value = view
})
</script>

<template>
  <div class="min-height-[5rem]" ref="editorRef"></div>
</template>
