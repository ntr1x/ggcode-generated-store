local Action = require('vue/Action')

local dialogs = require('vue/definitions/dialogs')
local requests = require('vue/definitions/requests')

local actions = {}

actions.action_dispatch_message_open_dialog = Action
  :from_dialog(dialogs.dialog_dispatch_message, 'dispatch_message')

actions.action_dispatch_message_remove_selected = Action
  :from_request(requests.request_dispatch_remove_all, 'dispatch_remove_selected')

actions.action_network_create_open_dialog = Action
  :from_dialog(dialogs.dialog_network_create, 'create_network')

actions.action_network_remove_selected = Action
  :from_request(requests.request_network_remove_all, 'network_remove_selected')

actions.action_guide_create_open_dialog = Action
  :from_dialog(dialogs.dialog_guide_create, 'create_guide')

actions.action_guide_remove_selected = Action
  :from_request(requests.request_guide_remove_all, 'guide_remove_selected')

actions.action_guide_subject_create_open_dialog = Action
  :from_dialog(dialogs.dialog_guide_subject_create, 'create_guide_subject')

actions.action_guide_subject_remove_selected = Action
  :from_request(requests.request_guide_subject_remove_all, 'guide_subject_remove_selected')

return actions
