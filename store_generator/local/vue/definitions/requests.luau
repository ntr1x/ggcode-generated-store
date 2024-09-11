local Meta = require('core/Meta')
local Request = require('vue/Request')

local remotes = require('vue/definitions/remotes')
local structures = require('vue/definitions/structures')

local requests = {}

requests.request_customer_select = Request
  :from_post(remotes.remote_customers, 'select_customer_page', '/system/public_customer/select')
  :with_body(Meta:table {})
  :with_query(Meta:table {
    sort = 'fullName',
    size = 50
  })
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('query', 'Record<string, any>'):with_optional(),
    Request.Prop:from_type('payload', 'Record<string, any>'):with_optional(),
  }

requests.request_agent_select = Request
  :from_post(remotes.remote_customers, 'select_agent_page', '/system/public_agent/select')
  :with_body(Meta:table {})
  :with_query(Meta:table {
    size = 50
  })
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('query', 'Record<string, any>'):with_optional(),
    Request.Prop:from_type('payload', 'Record<string, any>'):with_optional(),
  }

requests.request_region_select = Request
  :from_post(remotes.remote_products, 'select_region_page', '/system/public_region/select')
  :with_body(Meta:table {})
  :with_query(Meta:table {
    sort = 'name,asc',
    size = 50
  })
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('query', 'Record<string, any>'):with_optional(),
    Request.Prop:from_type('payload', 'Record<string, any>'):with_optional(),
  }

requests.request_shop_select = Request
  :from_post(remotes.remote_products, 'select_shop_page', '/system/public_shop/select')
  :with_body(Meta:table {})
  :with_query(Meta:table {
    sort = 'name,asc',
    size = 50
  })
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('query', 'Record<string, any>'):with_optional(),
    Request.Prop:from_type('payload', 'Record<string, any>'):with_optional(),
  }

requests.request_product_select = Request
  :from_post(remotes.remote_products, 'select_product_page', '/system/public_product/select')
  :with_body(Meta:table {})
  :with_query(Meta:table {
    sort = 'name,asc',
    size = 50
  })
  :with_structure(structures.structure_public_product_page)
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('query', 'Record<string, any>'):with_optional(),
    Request.Prop:from_type('payload', 'Record<string, any>'):with_optional(),
  }

requests.request_item_select = Request
  :from_post(remotes.remote_products, 'select_item_page', '/system/public_item/select')
  :with_body(Meta:table {})
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('query', 'Record<string, any>'):with_optional(),
    Request.Prop:from_type('payload', 'Record<string, any>'):with_optional(),
  }

requests.request_category_select = Request
  :from_post(remotes.remote_products, 'select_category_page', '/system/public_category/select')
  :with_body(Meta:table {})
  :with_query(Meta:table {
    sort = 'name,asc',
    size = 50
  })
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('query', 'Record<string, any>'):with_optional(),
    Request.Prop:from_type('payload', 'Record<string, any>'):with_optional(),
  }

requests.request_promotion_select = Request
  :from_post(remotes.remote_products, 'select_promotion_page', '/system/public_promotion/select')
  :with_body(Meta:table {})
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('query', 'Record<string, any>'):with_optional(),
    Request.Prop:from_type('payload', 'Record<string, any>'):with_optional(),
  }

requests.request_order_select = Request
  :from_post(remotes.remote_payments, 'select_order_page', '/system/public_order/select')
  :with_body(Meta:table {})
  :with_query(Meta:table {
    sort = 'id,asc',
    size = 50
  })
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('query', 'Record<string, any>'):with_optional(),
    Request.Prop:from_type('payload', 'Record<string, any>'):with_optional(),
  }

requests.request_payment_select = Request
  :from_post(remotes.remote_payments, 'select_payment_page', '/system/public_payment/select')
  :with_body(Meta:table {})
  :with_query(Meta:table {
    sort = 'id,asc',
    size = 50
  })
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('query', 'Record<string, any>'):with_optional(),
    Request.Prop:from_type('payload', 'Record<string, any>'):with_optional(),
  }

requests.request_source_type_select = Request
  :from_post(remotes.remote_payments, 'select_source_type_page', '/system/public_source_type/select')
  :with_body(Meta:table {})
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('query', 'Record<string, any>'):with_optional(),
    Request.Prop:from_type('payload', 'Record<string, any>'):with_optional(),
  }

requests.request_order_type_select = Request
  :from_post(remotes.remote_payments, 'select_order_type_page', '/system/public_order_type/select')
  :with_body(Meta:table {})
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('query', 'Record<string, any>'):with_optional(),
    Request.Prop:from_type('payload', 'Record<string, any>'):with_optional(),
  }

requests.request_order_status_select = Request
  :from_post(remotes.remote_payments, 'select_order_status_page', '/system/public_order_status/select')
  :with_body(Meta:table {})
  :with_query(Meta:table {
    sort = 'id,asc',
    size = 50
  })
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('query', 'Record<string, any>'):with_optional(),
    Request.Prop:from_type('payload', 'Record<string, any>'):with_optional(),
  }

requests.request_payment_type_select = Request
  :from_post(remotes.remote_payments, 'select_payment_type_page', '/system/public_payment_type/select')
  :with_body(Meta:table {})
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('query', 'Record<string, any>'):with_optional(),
    Request.Prop:from_type('payload', 'Record<string, any>'):with_optional(),
  }

requests.request_payment_status_select = Request
  :from_post(remotes.remote_payments, 'select_payment_status_page', '/system/public_payment_status/select')
  :with_body(Meta:table {})
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('query', 'Record<string, any>'):with_optional(),
    Request.Prop:from_type('payload', 'Record<string, any>'):with_optional(),
  }

requests.request_promotion_type_select = Request
  :from_post(remotes.remote_payments, 'select_promotion_type_page', '/system/public_promotion_type/select')
  :with_body(Meta:table {})
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('query', 'Record<string, any>'):with_optional(),
    Request.Prop:from_type('payload', 'Record<string, any>'):with_optional(),
  }

requests.request_promotion_target_select = Request
  :from_post(remotes.remote_payments, 'select_promotion_target_page', '/system/public_promotion_target/select')
  :with_body(Meta:table {})
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('query', 'Record<string, any>'):with_optional(),
    Request.Prop:from_type('payload', 'Record<string, any>'):with_optional(),
  }

requests.request_event_select = Request
  :from_post(remotes.remote_events, 'select_event_page', '/system/public_event/select')
  :with_body(Meta:table {})
  :with_query(Meta:table {
    sort = 'createdAt,desc',
    size = 50
  })
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('query', 'Record<string, any>'):with_optional(),
    Request.Prop:from_type('payload', 'Record<string, any>'):with_optional(),
  }

requests.request_event_type_select = Request
  :from_post(remotes.remote_events, 'select_event_type_page', '/system/public_event_type_info/select')
  :with_body(Meta:table {})
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('query', 'Record<string, any>'):with_optional(),
    Request.Prop:from_type('payload', 'Record<string, any>'):with_optional(),
  }

requests.request_event_topic_select = Request
  :from_post(remotes.remote_events, 'select_event_topic_page', '/system/public_event_topic_info/select')
  :with_body(Meta:table {})
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('query', 'Record<string, any>'):with_optional(),
    Request.Prop:from_type('payload', 'Record<string, any>'):with_optional(),
  }

requests.request_event_source_select = Request
  :from_post(remotes.remote_events, 'select_event_source_page', '/system/public_event_source_info/select')
  :with_body(Meta:table {})
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('query', 'Record<string, any>'):with_optional(),
    Request.Prop:from_type('payload', 'Record<string, any>'):with_optional(),
  }

requests.request_dispatch_select = Request
  :from_post(remotes.remote_events, 'select_dispatch_page', '/system/public_dispatch/select')
  :with_body(Meta:table {})
  :with_query(Meta:table {
    sort = 'createdAt,desc',
    size = 50
  })
  :with_structure(structures.structure_public_dispatch_page)
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('query', 'Record<string, any>'):with_optional(),
    Request.Prop:from_type('payload', 'Record<string, any>'):with_optional(),
  }

requests.request_dispatch_type_select = Request
  :from_post(remotes.remote_events, 'select_dispatch_type_page', '/system/public_dispatch_type/select')
  :with_body(Meta:table {})
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('query', 'Record<string, any>'):with_optional(),
    Request.Prop:from_type('payload', 'Record<string, any>'):with_optional(),
  }

requests.request_dispatch_status_select = Request
  :from_post(remotes.remote_events, 'select_dispatch_status_page', '/system/public_dispatch_status/select')
  :with_body(Meta:table {})
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('query', 'Record<string, any>'):with_optional(),
    Request.Prop:from_type('payload', 'Record<string, any>'):with_optional(),
  }

requests.request_subscription_select = Request
  :from_post(remotes.remote_events, 'select_subscription_page', '/system/public_subscription/select')
  :with_body(Meta:table {})
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('query', 'Record<string, any>'):with_optional(),
    Request.Prop:from_type('payload', 'Record<string, any>'):with_optional(),
  }

requests.request_subscription_type_select = Request
  :from_post(remotes.remote_events, 'select_subscription_type_page', '/system/public_subscription_type/select')
  :with_body(Meta:table {})
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('query', 'Record<string, any>'):with_optional(),
    Request.Prop:from_type('payload', 'Record<string, any>'):with_optional(),
  }

requests.request_template_select = Request
  :from_post(remotes.remote_events, 'select_template_page', '/system/public_template/select')
  :with_body(Meta:table {})
  :with_query(Meta:table {
    sort = 'id,desc',
    size = 50
  })
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('query', 'Record<string, any>'):with_optional(),
    Request.Prop:from_type('payload', 'Record<string, any>'):with_optional(),
  }

requests.request_template_type_select = Request
  :from_post(remotes.remote_events, 'select_template_type_page', '/system/public_template_type/select')
  :with_body(Meta:table {})
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('query', 'Record<string, any>'):with_optional(),
    Request.Prop:from_type('payload', 'Record<string, any>'):with_optional(),
  }

requests.request_template_shape_select = Request
  :from_post(remotes.remote_events, 'select_template_shape_page', '/system/public_template_shape/select')
  :with_body(Meta:table {})
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('query', 'Record<string, any>'):with_optional(),
    Request.Prop:from_type('payload', 'Record<string, any>'):with_optional(),
  }

requests.request_network_select = Request
  :from_post(remotes.remote_structure, 'select_network_page', '/system/public_network/select')
  :with_body(Meta:table {})
  :with_query(Meta:table {
    sort = 'id,desc',
    size = 50
  })
  :with_structure(structures.structure_public_network_page)
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('query', 'Record<string, any>'):with_optional(),
    Request.Prop:from_type('payload', 'Record<string, any>'):with_optional(),
  }

requests.request_network_type_select = Request
  :from_post(remotes.remote_structure, 'select_network_type_page', '/system/public_network_type/select')
  :with_body(Meta:table {})
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('query', 'Record<string, any>'):with_optional(),
    Request.Prop:from_type('payload', 'Record<string, any>'):with_optional(),
  }

requests.request_guide_select = Request
  :from_post(remotes.remote_structure, 'select_guide_page', '/system/public_guide/select')
  :with_body(Meta:table {})
  :with_query(Meta:table {
    sort = 'name,asc',
    size = 50
  })
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('query', 'Record<string, any>'):with_optional(),
    Request.Prop:from_type('payload', 'Record<string, any>'):with_optional(),
  }

requests.request_guide_select = Request
  :from_post(remotes.remote_structure, 'select_guide_page', '/system/public_guide/select')
  :with_body(Meta:table {})
  :with_query(Meta:table {
    sort = 'position,asc',
    size = 50
  })
  :with_structure(structures.structure_public_guide_page)
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('query', 'Record<string, any>'):with_optional(),
    Request.Prop:from_type('payload', 'Record<string, any>'):with_optional(),
  }

requests.request_guide_subject_select = Request
  :from_post(remotes.remote_structure, 'select_guide_subject_page', '/system/public_guide_subject/select')
  :with_body(Meta:table {})
  :with_query(Meta:table {
    sort = 'position,asc',
    size = 50
  })
  :with_structure(structures.structure_public_guide_subject_page)
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('query', 'Record<string, any>'):with_optional(),
    Request.Prop:from_type('payload', 'Record<string, any>'):with_optional(),
  }

-- Create

requests.request_presign_upload = Request
  :from_post(remotes.remote_uploads, 'pre_sign_upload', '/profile/uploads/preSign')
  :with_body(Meta:table {})
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('payload', 'Record<string, any>'),
  }

requests.request_product_create = Request
  :from_post(remotes.remote_products, 'create_product', '/system/public_product')
  :with_body(Meta:table {})
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('payload', 'Record<string, any>'),
  }

requests.request_guide_subject_create = Request
  :from_post(remotes.remote_structure, 'create_guide_subject', '/system/public_guide_subject')
  :with_body(Meta:table {})
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('payload', 'Record<string, any>'),
  }

requests.request_guide_create = Request
  :from_post(remotes.remote_structure, 'create_guide', '/system/public_guide')
  :with_body(Meta:table {})
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('payload', 'Record<string, any>'),
  }

requests.request_network_create = Request
  :from_post(remotes.remote_structure, 'create_network', '/system/public_network')
  :with_body(Meta:table {})
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('payload', 'Record<string, any>'),
  }

requests.request_dispatch_create = Request
  :from_post(remotes.remote_events, 'create_dispatch', '/system/public_dispatch')
  :with_body(Meta:table {})
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('payload', 'Record<string, any>'),
  }

-- Update

requests.request_product_update = Request
  :from_put(remotes.remote_products, 'update_product', '/system/public_product/i/${props.productId}')
  :with_body(Meta:table {})
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('productId'),
    Request.Prop:from_type('payload', 'Record<string, any>'),
  }

-- Get by id

requests.request_customer_get = Request
  :from_get(remotes.remote_customers, 'get_customer', '/system/public_customer/i/${props.customerId}')
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('customerId'),
  }

requests.request_agent_get = Request
  :from_get(remotes.remote_customers, 'get_agent', '/system/public_agent/i/${props.agentId}')
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('agentId'),
  }

requests.request_product_get = Request
  :from_get(remotes.remote_products, 'get_product', '/system/public_product/i/${props.productId}')
  :with_structure(structures.structure_public_product_record)
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('productId'),
  }

requests.request_order_get = Request
  :from_get(remotes.remote_payments, 'get_order', '/system/public_order/i/${props.orderId}')
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('orderId'),
  }

requests.request_guide_subject_get = Request
  :from_get(remotes.remote_structure, 'get_guide_subject', '/system/public_guide_subject/i/${props.subjectId}')
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('subjectId'),
  }

requests.request_guide_get = Request
  :from_get(remotes.remote_structure, 'get_guide', '/system/public_guide/i/${props.guideId}')
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_type('guideId'),
  }

-- Remove all

requests.request_product_remove_all = Request
  :from_post(remotes.remote_products, 'remove_product_array', '/system/public_product/removeAll')
  :with_body(Meta:array {})
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_structure_array('payload', structures.structure_with_id_record),
  }

requests.request_dispatch_remove_all = Request
  :from_post(remotes.remote_events, 'remove_dispatch_array', '/system/public_dispatch/removeAll')
  :with_body(Meta:array {})
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_structure_array('payload', structures.structure_with_id_record),
  }

requests.request_network_remove_all = Request
  :from_post(remotes.remote_structure, 'remove_network_array', '/system/public_network/removeAll')
  :with_body(Meta:array {})
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_structure_array('payload', structures.structure_with_id_record),
  }

requests.request_guide_remove_all = Request
  :from_post(remotes.remote_structure, 'remove_guide_array', '/system/public_guide/removeAll')
  :with_body(Meta:array {})
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_structure_array('payload', structures.structure_with_id_record),
  }

requests.request_guide_subject_remove_all = Request
  :from_post(remotes.remote_structure, 'remove_guide_subject_array', '/system/public_guide_subject/removeAll')
  :with_body(Meta:array {})
  :with_rules {
    Request.Prop:from_type('token'),
    Request.Prop:from_structure_array('payload', structures.structure_with_id_record),
  }

return requests
