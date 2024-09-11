local EditorJS = require('vue/control/EditorJS')

local projects = require('vue/definitions/projects')

local editors = {}

editors.editor_product_description = EditorJS
  :from(projects.project, 'product_description')

return editors