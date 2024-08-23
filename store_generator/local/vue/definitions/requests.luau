local Meta = require('core/Meta')
local Request = require('vue/Request')

local remotes = require('vue/definitions/remotes')

local requests = {}

requests.request_presign_upload = Request
  :from_post(remotes.remote_uploads, '/profile/uploads/preSign')
  :with_body(Meta:table {})

requests.request_customer_select = Request
  :from_post(remotes.remote_customers, '/system/public_customer/select')
  :with_body(Meta:table {})
  :with_query(Meta:table {
    sort = 'fullName',
    size = 50
  })

requests.request_customer_get = Request
  :from_get(remotes.remote_customers, '/system/public_customer/i/${route.params.customerId}')

requests.request_agent_select = Request
  :from_post(remotes.remote_customers, '/system/public_agent/select')
  :with_body(Meta:table {})
  :with_query(Meta:table {
    size = 50
  })

requests.request_agent_get = Request
  :from_get(remotes.remote_customers, '/system/public_agent/i/${route.params.agentId}')

requests.request_region_select = Request
  :from_post(remotes.remote_products, '/system/public_region/select')
  :with_body(Meta:table {})

requests.request_shop_select = Request
  :from_post(remotes.remote_products, '/system/public_shop/select')
  :with_body(Meta:table {})

requests.request_product_select = Request
  :from_post(remotes.remote_products, '/system/public_product/select')
  :with_body(Meta:table {})

requests.request_product_get = Request
  :from_get(remotes.remote_products, '/system/public_product/i/${route.params.productId}')

requests.request_item_select = Request
  :from_post(remotes.remote_products, '/system/public_item/select')
  :with_body(Meta:table {})

requests.request_category_select = Request
  :from_post(remotes.remote_products, '/system/public_category/select')
  :with_body(Meta:table {})

requests.request_promotion_select = Request
  :from_post(remotes.remote_products, '/system/public_promotion/select')
  :with_body(Meta:table {})

requests.request_order_select = Request
  :from_post(remotes.remote_payments, '/system/public_order/select')
  :with_body(Meta:table {})
  :with_query(Meta:table {
    sort = 'id,asc',
    size = 50
  })

requests.request_order_get = Request
  :from_get(remotes.remote_payments, '/system/public_order/i/${route.params.orderId}')

requests.request_payment_select = Request
  :from_post(remotes.remote_payments, '/system/public_payment/select')
  :with_body(Meta:table {})
  :with_query(Meta:table {
    sort = 'id,asc',
    size = 50
  })

requests.request_source_type_select = Request
  :from_post(remotes.remote_payments, '/system/public_source_type/select')
  :with_body(Meta:table {})

requests.request_order_type_select = Request
  :from_post(remotes.remote_payments, '/system/public_order_type/select')
  :with_body(Meta:table {})

requests.request_order_status_select = Request
  :from_post(remotes.remote_payments, '/system/public_order_status/select')
  :with_body(Meta:table {})
  :with_query(Meta:table {
    sort = 'id,asc',
    size = 50
  })

requests.request_payment_type_select = Request
  :from_post(remotes.remote_payments, '/system/public_payment_type/select')
  :with_body(Meta:table {})

requests.request_payment_status_select = Request
  :from_post(remotes.remote_payments, '/system/public_payment_status/select')
  :with_body(Meta:table {})

requests.request_promotion_type_select = Request
  :from_post(remotes.remote_payments, '/system/public_promotion_type/select')
  :with_body(Meta:table {})

requests.request_promotion_target_select = Request
  :from_post(remotes.remote_payments, '/system/public_promotion_target/select')
  :with_body(Meta:table {})

requests.request_event_select = Request
  :from_post(remotes.remote_events, '/system/public_event/select')
  :with_body(Meta:table {})
  :with_query(Meta:table {
    sort = 'createdAt,desc',
    size = 50
  })

requests.request_event_type_select = Request
  :from_post(remotes.remote_events, '/system/public_event_type_info/select')
  :with_body(Meta:table {})

requests.request_event_topic_select = Request
  :from_post(remotes.remote_events, '/system/public_event_topic_info/select')
  :with_body(Meta:table {})

requests.request_event_source_select = Request
  :from_post(remotes.remote_events, '/system/public_event_source_info/select')
  :with_body(Meta:table {})

requests.request_dispatch_select = Request
  :from_post(remotes.remote_events, '/system/public_dispatch/select')
  :with_body(Meta:table {})
  :with_query(Meta:table {
    sort = 'createdAt,desc',
    size = 50
  })

requests.request_dispatch_remove_all = Request
  :from_post(remotes.remote_events, '/system/public_dispatch/removeAll')
  :with_body(Meta:array {})

requests.request_dispatch_type_select = Request
  :from_post(remotes.remote_events, '/system/public_dispatch_type/select')
  :with_body(Meta:table {})

requests.request_dispatch_status_select = Request
  :from_post(remotes.remote_events, '/system/public_dispatch_status/select')
  :with_body(Meta:table {})

requests.request_subscription_select = Request
  :from_post(remotes.remote_events, '/system/public_subscription/select')
  :with_body(Meta:table {})

requests.request_subscription_type_select = Request
  :from_post(remotes.remote_events, '/system/public_subscription_type/select')
  :with_body(Meta:table {})

requests.request_template_select = Request
  :from_post(remotes.remote_events, '/system/public_template/select')
  :with_body(Meta:table {})
  :with_query(Meta:table {
    sort = 'id,desc',
    size = 50
  })

requests.request_template_type_select = Request
  :from_post(remotes.remote_events, '/system/public_template_type/select')
  :with_body(Meta:table {})

requests.request_template_shape_select = Request
  :from_post(remotes.remote_events, '/system/public_template_shape/select')
  :with_body(Meta:table {})

requests.request_network_select = Request
  :from_post(remotes.remote_structure, '/system/public_network/select')
  :with_body(Meta:table {})
  :with_query(Meta:table {
    sort = 'id,desc',
    size = 50
  })

requests.request_network_create = Request
  :from_post(remotes.remote_structure, '/system/public_network')
  :with_body(Meta:table {})

requests.request_network_remove_all = Request
  :from_post(remotes.remote_structure, '/system/public_network/removeAll')
  :with_body(Meta:array {})

requests.request_network_type_select = Request
  :from_post(remotes.remote_structure, '/system/public_network_type/select')
  :with_body(Meta:table {})

requests.request_guide_select = Request
  :from_post(remotes.remote_structure, '/system/public_guide/select')
  :with_body(Meta:table {})
  :with_query(Meta:table {
    sort = 'name,asc',
    size = 50
  })

requests.request_guide_create = Request
  :from_post(remotes.remote_structure, '/system/public_guide')
  :with_body(Meta:table {})

requests.request_guide_remove_all = Request
  :from_post(remotes.remote_structure, '/system/public_guide/removeAll')
  :with_body(Meta:array {})

requests.request_guide_select = Request
  :from_post(remotes.remote_structure, '/system/public_guide/select')
  :with_body(Meta:table {})
  :with_query(Meta:table {
    sort = 'position,asc',
    size = 50
  })

requests.request_guide_subject_create = Request
  :from_post(remotes.remote_structure, '/system/public_guide_subject')
  :with_body(Meta:table {})

requests.request_guide_subject_remove_all = Request
  :from_post(remotes.remote_structure, '/system/public_guide_subject/removeAll')
  :with_body(Meta:array {})

requests.request_guide_subject_select = Request
  :from_post(remotes.remote_structure, '/system/public_guide_subject/select')
  :with_body(Meta:table {})
  :with_query(Meta:table {
    sort = 'position,asc',
    size = 50
  })

requests.request_guide_subject_get = Request
  :from_get(remotes.remote_structure, '/system/public_guide_subject/i/${route.params.subjectId}')
requests.request_guide_get = Request
  :from_get(remotes.remote_structure, '/system/public_guide/i/${route.params.guideId}')

return requests
