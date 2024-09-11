local Query = require('vue/Query')

local requests = require('vue/definitions/requests')

local queries = {}

queries.query_shop_select = Query
  :from_request(requests.request_shop_select, 'select_shop_page')
  :with_rules {
    Query.Filter:from('region_id', 'regionId'),
  }

queries.query_product_select = Query
  :from_request(requests.request_product_select, 'select_product_page')
  :with_rules {
    Query.Filter:from('category_id', 'categoryId'),
    Query.Sorter:from('id', 'id'),
    Query.Sorter:from('name', 'name'),
  }

queries.query_item_select = Query
  :from_request(requests.request_item_select, 'select_item_page')
  :with_rules {
    Query.Filter:from('shop_id', 'shopId'),
  }

queries.query_payment_select = Query
  :from_request(requests.request_payment_select, 'select_payment_page')
  :with_rules {
    Query.Filter:from('order_id', 'orderId'),
    Query.Filter:from('customer_id', 'customerId'),
    Query.Filter:from('payment_type', 'paymentTypeId'),
    Query.Filter:from('payment_status', 'paymentStatusId'),
    Query.Filter:from('order_type', 'order.orderTypeId'),
    Query.Filter:from('order_status', 'order.orderStatusId'),
  }

queries.query_order_select = Query
  :from_request(requests.request_order_select, 'select_order_page')
  :with_rules {
    Query.Filter:from('customer_id', 'customerId'),
    Query.Filter:from('order_type', 'orderTypeId'),
    Query.Filter:from('order_status', 'orderStatusId'),
    Query.Filter:from('source_type', 'sourceTypeId'),
    Query.Filter:from('region_id', 'regionId'),
    Query.Sorter:from('order_type_id', 'orderType'),
    Query.Sorter:from('order_status_id', 'orderStatus'),
    Query.Sorter:from('id', 'id'),
    Query.Sorter:from('customer_id', 'customerId'),
    Query.Sorter:from('created_at', 'createdAt'),
    Query.Sorter:from('updated_at', 'updatedAt'),
  }

queries.query_promotion_select = Query
  :from_request(requests.request_promotion_select, 'select_promotion_page')
  :with_rules {
    Query.Filter:from('promotion_type', 'typeId'),
  }

queries.query_agent_select = Query
  :from_request(requests.request_agent_select, 'select_agent_page')
  :with_rules {
    Query.Filter:from('customer_id', 'customerId'),
  }

queries.query_customer_select = Query:from_request(requests.request_customer_select, 'select_customer_page')
queries.query_region_select = Query:from_request(requests.request_region_select, 'select_region_page')
queries.query_category_select = Query:from_request(requests.request_category_select, 'select_category_page')
queries.query_source_type_select = Query:from_request(requests.request_source_type_select, 'select_source_type_page')
queries.query_order_type_select = Query:from_request(requests.request_order_type_select, 'select_order_type_page')
queries.query_order_status_select = Query:from_request(requests.request_order_status_select, 'select_order_status_page')
queries.query_payment_type_select = Query:from_request(requests.request_payment_type_select, 'select_payment_type_page')
queries.query_payment_status_select = Query:from_request(requests.request_payment_status_select, 'select_payment_status_page')
queries.query_promotion_type_select = Query:from_request(requests.request_promotion_type_select, 'select_promotion_type_page')

queries.query_event_select = Query
  :from_request(requests.request_event_select, 'select_event_page')
  :with_rules {
    Query.Filter:from('event_source', 'ceSource'),
    Query.Filter:from('event_type', 'ceType'),
    Query.Filter:from('event_topic', 'topic'),
    Query.Sorter:from('id', 'id'),
    Query.Sorter:from('ce_source', 'ceSource'),
    Query.Sorter:from('ce_type', 'ceType'),
    Query.Sorter:from('topic', 'topic'),
    Query.Sorter:from('created_at', 'createdAt'),
  }

queries.query_event_type_select = Query
  :from_request(requests.request_event_type_select, 'select_event_type_page')
  :with_rules {
    Query.Sorter:from('name', 'name'),
    Query.Sorter:from('description', 'description'),
  }

queries.query_event_topic_select = Query
  :from_request(requests.request_event_topic_select, 'select_event_topic_page')
  :with_rules {
    Query.Sorter:from('name', 'name'),
    Query.Sorter:from('description', 'description'),
  }

queries.query_event_source_select = Query
  :from_request(requests.request_event_source_select, 'select_event_source_page')
  :with_rules {
    Query.Sorter:from('name', 'name'),
    Query.Sorter:from('description', 'description'),
  }

queries.query_dispatch_select = Query
  :from_request(requests.request_dispatch_select, 'select_dispatch_page')
  :with_rules {
    Query.Filter:from('customer_id', 'customerId'),
    Query.Filter:from('type_id', 'typeId'),
    Query.Filter:from('status_id', 'statusId'),
    Query.Sorter:from('id', 'id'),
    Query.Sorter:from('type_id', 'typeId'),
    Query.Sorter:from('status_id', 'statusId'),
    Query.Sorter:from('created_at', 'createdAt'),
    Query.Sorter:from('updated_at', 'updatedAt'),
  }

queries.query_dispatch_type_select = Query
  :from_request(requests.request_dispatch_type_select, 'select_dispatch_type_page')
  :with_rules {
    Query.Sorter:from('id', 'id'),
    Query.Sorter:from('name', 'name'),
    Query.Sorter:from('description', 'description'),
  }

queries.query_dispatch_status_select = Query
  :from_request(requests.request_dispatch_status_select, 'select_dispatch_status_page')
  :with_rules {
    Query.Sorter:from('id', 'id'),
    Query.Sorter:from('name', 'name'),
    Query.Sorter:from('description', 'description'),
  }

queries.query_subscription_select = Query
  :from_request(requests.request_subscription_select, 'select_subscription_page')
  :with_rules {
    Query.Filter:from('customer_id', 'customerId'),
    Query.Filter:from('type_id', 'typeId'),
    Query.Sorter:from('id', 'id'),
    Query.Sorter:from('created_at', 'createdAt'),
    Query.Sorter:from('type_id', 'typeId'),
  }

queries.query_subscription_type_select = Query
  :from_request(requests.request_subscription_type_select, 'select_subscription_type_page')
  :with_rules {
    Query.Filter:from('customer_id', 'customerId'),
    Query.Filter:from('type_id', 'typeId'),
    Query.Sorter:from('id', 'id'),
    Query.Sorter:from('name', 'name'),
    Query.Sorter:from('description', 'description'),
  }

queries.query_template_select = Query
  :from_request(requests.request_template_select, 'select_template_page')
  :with_rules {
    Query.Filter:from('type_id', 'typeId'),
    Query.Filter:from('shape_id', 'shapeId'),
    Query.Sorter:from('id', 'id'),
    Query.Sorter:from('name', 'name'),
    Query.Sorter:from('type_id', 'typeId'),
    Query.Sorter:from('shape_id', 'shapeId'),
  }

queries.query_template_type_select = Query
  :from_request(requests.request_template_type_select, 'select_template_type_page')
  :with_rules {
    Query.Sorter:from('id', 'id'),
    Query.Sorter:from('name', 'name'),
    Query.Sorter:from('description', 'description'),
  }

queries.query_template_shape_select = Query
  :from_request(requests.request_template_shape_select, 'select_template_shape_page')
  :with_rules {
    Query.Sorter:from('id', 'id'),
    Query.Sorter:from('name', 'name'),
    Query.Sorter:from('description', 'description'),
  }

queries.query_promotion_target_select = Query
  :from_request(requests.request_promotion_target_select, 'select_promotion_target_page')
  :with_rules {
    Query.Filter:from('product_id', 'productId'),
    Query.Filter:from('promotion_id', 'promotionId'),
  }

queries.query_customer_get = Query
  :from_request(requests.request_customer_get, 'get_customer_record')
  :with_rules {
    Query.Prop:from('customer_id'),
  }

queries.query_agent_get = Query
  :from_request(requests.request_agent_get, 'get_agent_record')
  :with_rules {
    Query.Prop:from('agent_id'),
  }

queries.query_order_get = Query
  :from_request(requests.request_order_get, 'get_order_record')
  :with_rules {
    Query.Prop:from('order_id'),
  }

queries.query_product_get = Query
  :from_request(requests.request_product_get, 'get_product_record')
  :with_rules {
    Query.Prop:from('product_id'),
  }

queries.query_network_select = Query
  :from_request(requests.request_network_select, 'select_network_page')
  :with_rules {
    Query.Filter:from('type_id', 'typeId'),
    Query.Filter:from('shop_id', 'shopId'),
    Query.Filter:from('hidden', 'hidden'),
    Query.Sorter:from('id', 'id'),
    Query.Sorter:from('name', 'name'),
    Query.Sorter:from('type_id', 'typeId'),
    Query.Sorter:from('shop_id', 'shopId'),
    Query.Sorter:from('hidden', 'hidden'),
  }

queries.query_network_type_select = Query
  :from_request(requests.request_network_type_select, 'select_network_type_page')
  :with_rules {
    Query.Sorter:from('id', 'id'),
    Query.Sorter:from('name', 'name'),
    Query.Sorter:from('description', 'description'),
  }

queries.query_guide_select = Query
  :from_request(requests.request_guide_select, 'select_guide_page')
  :with_rules {
    Query.Filter:from('subject_id', 'subjectId'),
    Query.Sorter:from('id', 'id'),
    Query.Sorter:from('name', 'name'),
    Query.Sorter:from('position', 'position'),
    Query.Sorter:from('subject_id', 'subjectId'),
    Query.Sorter:from('created_at', 'createdAt'),
    Query.Sorter:from('updated_at', 'updatedAt'),
  }

queries.query_guide_subject_select = Query
  :from_request(requests.request_guide_subject_select, 'select_guide_subject_page')
  :with_rules {
    Query.Sorter:from('id', 'id'),
    Query.Sorter:from('name', 'name'),
    Query.Sorter:from('position', 'position'),
    Query.Sorter:from('created_at', 'createdAt'),
    Query.Sorter:from('updated_at', 'updatedAt'),
  }

queries.query_guide_subject_get = Query
  :from_request(requests.request_guide_subject_get, 'get_guide_subject_record')
  :with_rules {
    Query.Prop:from('subject_id'),
  }

queries.query_guide_get = Query
  :from_request(requests.request_guide_get, 'get_guide_record')
  :with_rules {
    Query.Prop:from('guide_id'),
  }

return queries