local Dialog = require('vue/Dialog')

local projects = require('vue/definitions/projects')
local selects = require('vue/definitions/selects')
local requests = require('vue/definitions/requests')

local dialogs = {}

dialogs.dialog_dispatch_message = Dialog
  :from(projects.project, 'dispatch_message')

dialogs.dialog_network_create = Dialog
  :from(projects.project, 'network_create')
  :with_rules {
    Dialog.Field:from_select(selects.select_network_type, 'type_id'),
    Dialog.Field:from_select(selects.select_shop_id, 'shop_id'),
    Dialog.Field:from_text('name'),
    Dialog.Field:from_text('password'),
    Dialog.Field:from_toggle('hidden'),
    Dialog.Call:from_submit(requests.request_network_create)
  }

dialogs.dialog_guide_subject_create = Dialog
  :from(projects.project, 'guide_subject_create')
  :with_rules {
    Dialog.Field:from_text('name'),
    Dialog.Field:from_number('position'),
    Dialog.Call:from_submit(requests.request_guide_subject_create)
  }

dialogs.dialog_guide_create = Dialog
  :from(projects.project, 'guide_create')
  :with_rules {
    Dialog.Field:from_text('name'),
    Dialog.Field:from_text('link'),
    Dialog.Field:from_number('position'),
    Dialog.Field:from_select(selects.select_guide_subject, 'subject_id'),
    Dialog.Call:from_submit(requests.request_guide_create)
  }

return dialogs
