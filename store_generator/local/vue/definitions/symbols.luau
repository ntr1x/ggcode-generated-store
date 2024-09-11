local Symbol = require('vue/control/Symbol')

local projects = require('vue/definitions/projects')

local symbols = {}

symbols.symbol_datetime = Symbol
  :from(projects.project, 'datetime')
  :with_import('date-fns', { format = 'formatDate' })
  :with_label('props.value == null ? null : formatDate(props.value, "PPpp")')

symbols.symbol_customer = Symbol
  :from(projects.project, 'customer')
  :with_label('props.value == null ? null : [props.value.name, props.value.patronymic, props.value.surname].filter(item => item != null).join(" ")')
  :with_route('props.value == null ? null : `/admin/customers/${props.value.id}`')
  :with_icon('"pi pi-user"')

symbols.symbol_agent = Symbol
  :from(projects.project, 'agent')
  :with_label('(props.value == null || props.value.customer == null) ? null : [props.value.customer.name, props.value.customer.patronymic, props.value.customer.surname].filter(item => item != null).join(" ")')
  :with_icon('"pi pi-user"')

symbols.symbol_id = Symbol
  :from(projects.project, 'id')
  :with_label('props.value')
  :with_decoration('"text-xs text-ellipsis"')

symbols.symbol_agent_ref = Symbol
  :from(projects.project, 'agent_ref')
  :with_label('"AGENT"')
  :with_route('`/admin/agents/${props.value}`')
  :with_decoration('"text-[0.6rem]"')

symbols.symbol_price = Symbol
  :from(projects.project, 'price')
  :with_label('props.value')
  :with_icon('"pi pi-money-bill"')

symbols.symbol_region = Symbol
  :from(projects.project, 'region')
  :with_label('props.value.name')
  :with_icon('"pi pi-map"')

symbols.symbol_shop = Symbol
  :from(projects.project, 'shop')
  :with_label('props.value.name')
  :with_icon('"pi pi-warehouse"')

symbols.symbol_source_type = Symbol
  :from(projects.project, 'source_type')
  :with_label('props.value.description')
  :with_decoration('decorations[props.value.name]')
  :with_record('decorations', {
    web = 'rounded text-blue-700 dark:text-blue-300 bg-blue-100 dark:bg-blue-500/20',
    mobile = 'rounded text-purple-700 dark:text-purple-300 bg-purple-100 dark:bg-purple-500/20',
  })

symbols.symbol_order_type = Symbol
  :from(projects.project, 'order_type')
  :with_label('props.value.description')
  :with_decoration('decorations[props.value.name]')
  :with_record('decorations', {
    CASH = 'rounded text-green-700 dark:text-green-300 bg-green-100 dark:bg-green-500/20',
    CARD_INPLACE = 'rounded text-teal-700 dark:text-teal-300 bg-teal-100 dark:bg-teal-500/20',
    CARD_ONLINE = 'rounded text-sky-700 dark:text-sky-300 bg-sky-100 dark:bg-sky-500/20',
  })

symbols.symbol_order_status = Symbol
  :from(projects.project, 'order_status')
  :with_label('props.value.description')
  :with_decoration('decorations[props.value.name]')
  :with_record('decorations', {
    NEW = 'rounded text-blue-700 dark:text-blue-300 bg-blue-100 dark:bg-blue-500/20',
    ACCEPTED = 'rounded text-green-700 dark:text-green-300 bg-green-100 dark:bg-green-500/20',
    REJECTED = 'rounded text-red-700 dark:text-red-300 bg-red-100 dark:bg-red-500/20',
    CANCELED = 'rounded text-orange-700 dark:text-orange-300 bg-orange-100 dark:bg-orange-500/20',
  })

symbols.symbol_payment_type = Symbol
  :from(projects.project, 'payment_type')
  :with_label('props.value.description')
  :with_decoration('decorations[props.value.name]')
  :with_record('decorations', {
    CASH = 'rounded text-green-700 dark:text-green-300 bg-green-100 dark:bg-green-500/20',
    CARD_INPLACE = 'rounded text-teal-700 dark:text-teal-300 bg-teal-100 dark:bg-teal-500/20',
    CARD_ONLINE = 'rounded text-sky-700 dark:text-sky-300 bg-sky-100 dark:bg-sky-500/20',
  })

symbols.symbol_payment_status = Symbol
  :from(projects.project, 'payment_status')
  :with_label('props.value.description')
  :with_decoration('decorations[props.value.name]')
  :with_record('decorations', {
    NEW = 'rounded text-blue-700 dark:text-blue-300 bg-blue-100 dark:bg-blue-500/20',
    ACCEPTED = 'rounded text-green-700 dark:text-green-300 bg-green-100 dark:bg-green-500/20',
    REJECTED = 'rounded text-red-700 dark:text-red-300 bg-red-100 dark:bg-red-500/20',
    CANCELED = 'rounded text-orange-700 dark:text-orange-300 bg-orange-100 dark:bg-orange-500/20',
  })

symbols.symbol_promotion_type = Symbol
  :from(projects.project, 'promotion_type')
  :with_label('props.value.description')
  :with_decoration('decorations[props.value.name]')
  :with_record('decorations', {
    DISCOUNT = 'rounded text-green-700 dark:text-green-300 bg-green-100 dark:bg-green-500/20',
    BONUSES = 'rounded text-teal-700 dark:text-teal-300 bg-teal-100 dark:bg-teal-500/20',
    FREE_SHIPPING = 'rounded text-sky-700 dark:text-sky-300 bg-sky-100 dark:bg-sky-500/20',
  })

symbols.symbol_subscription_type = Symbol
  :from(projects.project, 'subscription_type')
  :with_label('props.value.description')
  :with_decoration('decorations[props.value.name]')
  :with_record('decorations', {
    FCM = 'rounded text-green-700 dark:text-green-300 bg-green-100 dark:bg-green-500/20',
    HMS = 'rounded text-indigo-700 dark:text-indigo-300 bg-indigo-100 dark:bg-indigo-500/20',
  })

symbols.symbol_event_topic_info = Symbol
  :from(projects.project, 'event_topic_info')
  :with_label('props.value == null ? null : `${props.value.name} (${props.value.description})`')
  :with_decoration('"rounded text-gray-700 dark:text-gray-300 bg-gray-100 dark:bg-gray-500/20"')

symbols.symbol_event_source_info = Symbol
  :from(projects.project, 'event_source_info')
  :with_label('`${props.value.name} (${props.value.description})`')
  :with_decoration('"rounded text-gray-700 dark:text-gray-300 bg-gray-100 dark:bg-gray-500/20"')

symbols.symbol_event_type_info = Symbol
  :from(projects.project, 'event_type_info')
  :with_label('`${props.value.name} (${props.value.description})`')
  :with_decoration('decorations[props.value.name]')
  :with_record('decorations', {
    created = 'rounded text-blue-700 dark:text-blue-300 bg-blue-100 dark:bg-blue-500/20',
    updated = 'rounded text-green-700 dark:text-green-300 bg-green-100 dark:bg-green-500/20',
    removed = 'rounded text-red-700 dark:text-red-300 bg-red-100 dark:bg-red-500/20',
    replaced = 'rounded text-orange-700 dark:text-orange-300 bg-orange-100 dark:bg-orange-500/20',
    upserted = 'rounded text-amber-700 dark:text-amber-300 bg-amber-100 dark:bg-amber-500/20',
  })

symbols.symbol_session_id = Symbol
  :from(projects.project, 'session_id')
  :with_label('props.value')
  :with_decoration('"text-xs text-ellipsis"')

symbols.symbol_dispatch_status = Symbol
  :from(projects.project, 'dispatch_status')
  :with_label('props.value.description')
  :with_decoration('decorations[props.value.name]')
  :with_record('decorations', {
    NEW = 'rounded text-blue-700 dark:text-blue-300 bg-blue-100 dark:bg-blue-500/20',
    DISPATCHED = 'rounded text-orange-700 dark:text-orange-300 bg-orange-100 dark:bg-orange-500/20',
    CONFIRMED = 'rounded text-green-700 dark:text-green-300 bg-green-100 dark:bg-green-500/20',
    FAILED = 'rounded text-red-700 dark:text-red-300 bg-red-100 dark:bg-red-500/20',
  })

symbols.symbol_dispatch_type = Symbol
  :from(projects.project, 'dispatch_type')
  :with_label('props.value.description')
  :with_decoration('decorations[props.value.name]')
  :with_record('decorations', {
    CUSTOMER = 'rounded text-blue-700 dark:text-blue-300 bg-blue-100 dark:bg-blue-500/20',
    AGENT = 'rounded text-orange-700 dark:text-orange-300 bg-orange-100 dark:bg-orange-500/20',
    SESSION = 'rounded text-green-700 dark:text-green-300 bg-green-100 dark:bg-green-500/20',
    CRITERIA = 'rounded text-rose-700 dark:text-rose-300 bg-rose-100 dark:bg-rose-500/20',
  })

symbols.symbol_template_shape = Symbol
  :from(projects.project, 'template_shape')
  :with_label('props.value.description')
  :with_decoration('decorations[props.value.name]')
  :with_record('decorations', {
    TEXT = 'rounded text-blue-700 dark:text-blue-300 bg-blue-100 dark:bg-blue-500/20',
    EMAIL = 'rounded text-orange-700 dark:text-orange-300 bg-orange-100 dark:bg-orange-500/20',
  })

symbols.symbol_template_type = Symbol
  :from(projects.project, 'template_type')
  :with_label('props.value.description')
  :with_decoration('decorations[props.value.name]')
  :with_record('decorations', {
    ['message:info'] = 'rounded text-red-700 dark:text-red-300 bg-red-100 dark:bg-red-500/20',
    ['security:signin'] = 'rounded text-orange-700 dark:text-orange-300 bg-orange-100 dark:bg-orange-500/20',
    ['security:signup'] = 'rounded text-amber-700 dark:text-amber-300 bg-amber-100 dark:bg-amber-500/20',
    ['profile:updated'] = 'rounded text-green-700 dark:text-green-300 bg-green-100 dark:bg-green-500/20',
    ['order:created'] = 'rounded text-blue-700 dark:text-blue-300 bg-blue-100 dark:bg-blue-500/20',
    ['order:packaged'] = 'rounded text-emerald-700 dark:text-emerald-300 bg-emerald-100 dark:bg-emerald-500/20',
    ['order:paid'] = 'rounded text-teal-700 dark:text-teal-300 bg-teal-100 dark:bg-teal-500/20',
    ['order:failed'] = 'rounded text-rose-700 dark:text-rose-300 bg-rose-100 dark:bg-rose-500/20',
    ['order:canceled'] = 'rounded text-fuchsia-700 dark:text-fuchsia-300 bg-fuchsia-100 dark:bg-fuchsia-500/20',
    ['order:ready'] = 'rounded text-sky-700 dark:text-sky-300 bg-sky-100 dark:bg-sky-500/20',
    ['order:complete'] = 'rounded text-purple-700 dark:text-purple-300 bg-purple-100 dark:bg-purple-500/20',
  })

symbols.symbol_network_type = Symbol
  :from(projects.project, 'network_type')
  :with_label('props.value.description')
  :with_decoration('decorations[props.value.name]')
  :with_record('decorations', {
    NOPASS,
    WEP,
    WPA,
    ['NOPASS'] = 'rounded text-orange-700 dark:text-orange-300 bg-orange-100 dark:bg-orange-500/20',
    ['WEP'] = 'rounded text-green-700 dark:text-green-300 bg-green-100 dark:bg-green-500/20',
    ['WPA'] = 'rounded text-blue-700 dark:text-blue-300 bg-blue-100 dark:bg-blue-500/20',
  })

symbols.symbol_guide_subject = Symbol
  :from(projects.project, 'guide_subject')
  :with_label('props.value.name')
  :with_icon('"pi pi-bookmark"')

symbols.symbol_s3_http = Symbol
  :from(projects.project, 's3_http')
  :with_label('(props.value == null || props.value == null) ? null : `s3://app-public-document/${props.value}`')
  :with_icon('"pi pi-download"')

return symbols