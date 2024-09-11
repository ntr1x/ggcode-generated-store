local Page = require('vue/Page')

local projects = require('vue/definitions/projects')
local queries = require('vue/definitions/queries')
local fieldsets = require('vue/definitions/fieldsets')
local toolbars = require('vue/definitions/toolbars')
local grids = require('vue/definitions/grids')
local widgets = require('vue/definitions/widgets')

local pages = {}

pages.page_dashboard = Page
  :from(projects.project, 'dashboard')
  :with_rules {
    Page.Heading:from('dashboard'),
    Page.Section:from_widget(widgets.widget_personal_profile),
    Page.Section
      :from_widget(widgets.widget_dispatch_message)
      :with_roles({ 'realm:developer', 'realm:admin', 'realm:support' }),
  }

pages.page_customer_list = Page
  :from(projects.project, 'customers')
  :with_rules {
    Page.Heading:from('customers'),
    Page.Section:from_grid(grids.grid_customer),
    Page.Query:from(queries.query_customer_select),
  }

pages.page_customer_info = Page
  :from(projects.project, 'customer_info')
  :without_flex()
  :with_rules {
    Page.Prop:from_route('customerId'),
    Page.Heading:from('customer'),
    Page.Section:from_fieldset(fieldsets.fieldset_customer_info),
    Page.Heading:from('orders', 'h3'),
    Page.Section:from_toolbar(toolbars.toolbar_order),
    Page.Section
      :from_grid(grids.grid_order)
      :with_variable('scrollable', 'false')
      :with_variable('hide-customer', 'true'),
    Page.Heading:from('agents', 'h3'),
    Page.Section
      :from_grid(grids.grid_agent)
      :with_variable('scrollable', 'false')
      :with_variable('hide-customer', 'true'),
    Page.Heading:from('subscriptions', 'h3'),
    Page.Section:from_toolbar(toolbars.toolbar_subscription),
    Page.Section
      :from_grid(grids.grid_subscription)
      :with_variable('scrollable', 'false')
      :with_variable('hide-customer', 'true'),
    Page.Heading:from('dispatches', 'h3'),
    Page.Section:from_toolbar(toolbars.toolbar_dispatch),
    Page.Section
      :from_grid(grids.grid_dispatch)
      :with_variable('scrollable', 'false')
      :with_variable('hide-customer', 'true'),
    Page.Query:from(queries.query_customer_get, { customerId = 'props.customerId' }),
    Page.Query:from(queries.query_order_select, { customerId = 'props.customerId' }),
    Page.Query:from(queries.query_agent_select, { customerId = 'props.customerId' }),
    Page.Query:from(queries.query_subscription_select, { customerId = 'props.customerId' }),
    Page.Query:from(queries.query_dispatch_select, { customerId= 'props.customerId' }),
  }

pages.page_agent_list = Page
  :from(projects.project, 'agents')
  :without_flex()
  :with_rules {
    Page.Heading:from('agents'),
    Page.Section:from_grid(grids.grid_agent),
    Page.Query:from(queries.query_agent_select),
  }

pages.page_agent_info = Page
  :from(projects.project, 'agent_info')
  :with_rules {
    Page.Prop:from_route('agentId'),
    Page.Heading:from('agent'),
    Page.Section:from_fieldset(fieldsets.fieldset_agent_info),
    Page.Query:from(queries.query_agent_get, { agentId = 'props.agentId' }),
  }

pages.page_item_list = Page
  :from(projects.project, 'items')
  :with_rules {
    Page.Heading:from('items'),
    Page.Section:from_toolbar(toolbars.toolbar_item),
    Page.Section:from_grid(grids.grid_item),
    Page.Query:from(queries.query_item_select),
  }

pages.page_product_list = Page
  :from(projects.project, 'products')
  :with_rules {
    Page.Heading:from('products'),
    Page.Section:from_toolbar(toolbars.toolbar_product),
    Page.Section:from_grid(grids.grid_product),
    Page.Query:from(queries.query_product_select),
  }

pages.page_product_info = Page
  :from(projects.project, 'product_info')
  :without_flex()
  :with_rules {
    Page.Prop:from_route('productId'),
    Page.Heading:from('product'),
    Page.Section:from_fieldset(fieldsets.fieldset_product_info),
    Page.Heading:from('promotion_targets', 'h3'),
    Page.Section:from_toolbar(toolbars.toolbar_promotion_target),
    Page.Section
      :from_grid(grids.grid_promotion_target)
      :with_variable('scrollable', 'false')
      :with_variable('hide-customer', 'true'),
    -- Page.Heading:from('agents', 'h3'),
    -- Page.Section
    --   :from_grid(grids.grid_agent)
    --   :with_variable('scrollable', 'false')
    --   :with_variable('hide-customer', 'true'),
    Page.Query:from(queries.query_product_get, { productId = 'props.productId' }),
    Page.Query:from(queries.query_promotion_target_select, { productId = 'props.productId' }),
    -- Page.Query:from(queries.query_agent_select, { productId = 'props.productId' }),
  }

pages.page_category_list = Page
  :from(projects.project, 'categories')
  :with_rules {
    Page.Heading:from('categories'),
    Page.Section:from_grid(grids.grid_category),
    Page.Query:from(queries.query_category_select),
  }

pages.page_promotion_list = Page
  :from(projects.project, 'promotions')
  :with_rules {
    Page.Heading:from('promotions'),
    Page.Section:from_toolbar(toolbars.toolbar_promotion),
    Page.Section:from_grid(grids.grid_promotion),
    Page.Query:from(queries.query_promotion_select),
  }

pages.page_region_list = Page
  :from(projects.project, 'regions')
  :with_rules {
    Page.Heading:from('regions'),
    Page.Section:from_grid(grids.grid_region),
    Page.Query:from(queries.query_region_select),
  }

pages.page_shop_list = Page
  :from(projects.project, 'shops')
  :with_rules {
    Page.Heading:from('shops'),
    Page.Section:from_toolbar(toolbars.toolbar_shop),
    Page.Section:from_grid(grids.grid_shop),
    Page.Query:from(queries.query_shop_select),
  }

pages.page_order_list = Page
  :from(projects.project, 'orders')
  :with_rules {
    Page.Heading:from('orders'),
    Page.Section:from_toolbar(toolbars.toolbar_order),
    Page.Section:from_grid(grids.grid_order),
    Page.Query:from(queries.query_order_select),
  }

pages.page_order_info = Page
  :from(projects.project, 'order_info')
  :without_flex()
  :with_rules {
    Page.Prop:from_route('orderId'),
    Page.Heading:from('order'),
    Page.Section:from_fieldset(fieldsets.fieldset_order_info),
    Page.Heading:from('payments', 'h3'),
    Page.Section:from_toolbar(toolbars.toolbar_payment),
    Page.Section
      :from_grid(grids.grid_payment)
      :with_variable('scrollable', 'false')
      :with_variable('hide-order', 'true'),
    Page.Query:from(queries.query_order_get, { orderId = 'props.orderId' }),
    Page.Query:from(queries.query_payment_select, { orderId = 'props.orderId' }),
  }

pages.page_payment_list = Page:from(projects.project, 'payments')
  :from(projects.project, 'payments')
  :with_rules {
    Page.Heading:from('payments'),
    Page.Section:from_toolbar(toolbars.toolbar_payment),
    Page.Section:from_grid(grids.grid_payment),
    Page.Query:from(queries.query_payment_select),
  }

pages.page_order_type_list = Page
  :from(projects.project, 'order_types')
  :with_rules {
    Page.Heading:from('order_types'),
    Page.Section:from_grid(grids.grid_order_type),
    Page.Query:from(queries.query_order_type_select),
  }

pages.page_order_status_list = Page
  :from(projects.project, 'order_statuses')
  :with_rules {
    Page.Heading:from('order_statuses'),
    Page.Section:from_grid(grids.grid_order_status),
    Page.Query:from(queries.query_order_status_select),
  }

pages.page_payment_type_list = Page
  :from(projects.project, 'payment_types')
  :with_rules {
    Page.Heading:from('payment_types'),
    Page.Section:from_grid(grids.grid_payment_type),
    Page.Query:from(queries.query_payment_type_select),
  }

pages.page_payment_status_list = Page
  :from(projects.project, 'payment_statuses')
  :with_rules {
    Page.Heading:from('payment_statuses'),
    Page.Section:from_grid(grids.grid_payment_status),
    Page.Query:from(queries.query_payment_status_select),
  }

pages.page_promotion_type_list = Page
  :from(projects.project, 'promotion_types')
  :with_rules {
    Page.Heading:from('promotion_types'),
    Page.Section:from_grid(grids.grid_promotion_type),
    Page.Query:from(queries.query_promotion_type_select),
  }

pages.page_source_type_list = Page
  :from(projects.project, 'source_types')
  :with_rules {
    Page.Heading:from('source_types'),
    Page.Section:from_grid(grids.grid_source_type),
    Page.Query:from(queries.query_source_type_select),
  }

pages.page_dispatch_list = Page
  :from(projects.project, 'dispatches')
  :with_rules {
    Page.Heading:from('dispatches'),
    Page.Section:from_toolbar(toolbars.toolbar_dispatch),
    Page.Section:from_grid(grids.grid_dispatch),
    Page.Query:from(queries.query_dispatch_select),
  }

pages.page_dispatch_type_list = Page
  :from(projects.project, 'dispatch_types')
  :with_rules {
    Page.Heading:from('dispatch_types'),
    Page.Section:from_grid(grids.grid_dispatch_type),
    Page.Query:from(queries.query_dispatch_type_select),
  }

pages.page_dispatch_status_list = Page
  :from(projects.project, 'dispatch_statuses')
  :with_rules {
    Page.Heading:from('dispatch_statuses'),
    Page.Section:from_grid(grids.grid_dispatch_status),
    Page.Query:from(queries.query_dispatch_status_select),
  }

pages.page_subscription_list = Page
  :from(projects.project, 'subscriptions')
  :with_rules {
    Page.Heading:from('subscriptions'),
    Page.Section:from_toolbar(toolbars.toolbar_subscription),
    Page.Section:from_grid(grids.grid_subscription),
    Page.Query:from(queries.query_subscription_select),
  }

pages.page_subscription_type_list = Page
  :from(projects.project, 'subscription_types')
  :with_rules {
    Page.Heading:from('subscription_types'),
    Page.Section:from_grid(grids.grid_subscription_type),
    Page.Query:from(queries.query_subscription_type_select),
  }

pages.page_event_list = Page
  :from(projects.project, 'events')
  :with_rules {
    Page.Heading:from('events'),
    Page.Section:from_toolbar(toolbars.toolbar_event),
    Page.Section:from_grid(grids.grid_event),
    Page.Query:from(queries.query_event_select),
  }

pages.page_event_topics = Page
  :from(projects.project, 'event_topics')
  :with_rules {
    Page.Heading:from('event_topics'),
    Page.Section:from_grid(grids.grid_event_topic),
    Page.Query:from(queries.query_event_topic_select),
  }

pages.page_event_types = Page
  :from(projects.project, 'event_types')
  :with_rules {
    Page.Heading:from('event_types'),
    Page.Section:from_grid(grids.grid_event_type),
    Page.Query:from(queries.query_event_type_select),
  }

pages.page_event_sources = Page
  :from(projects.project, 'event_sources')
  :with_rules {
    Page.Heading:from('event_sources'),
    Page.Section:from_grid(grids.grid_event_source),
    Page.Query:from(queries.query_event_source_select),
  }

pages.page_template_list = Page
  :from(projects.project, 'templates')
  :with_rules {
    Page.Heading:from('templates'),
    Page.Section:from_toolbar(toolbars.toolbar_template),
    Page.Section:from_grid(grids.grid_template),
    Page.Query:from(queries.query_template_select),
  }

pages.page_template_type_list = Page
  :from(projects.project, 'template_types')
  :with_rules {
    Page.Heading:from('template_types'),
    Page.Section:from_grid(grids.grid_template_type),
    Page.Query:from(queries.query_template_type_select),
  }

pages.page_template_shape_list = Page
  :from(projects.project, 'template_shapes')
  :with_rules {
    Page.Heading:from('template_shapes'),
    Page.Section:from_grid(grids.grid_template_shape),
    Page.Query:from(queries.query_template_shape_select),
  }

pages.page_network_list = Page
  :from(projects.project, 'networks')
  :with_rules {
    Page.Heading:from('networks'),
    Page.Section:from_toolbar(toolbars.toolbar_network),
    Page.Section:from_grid(grids.grid_network),
    Page.Query:from(queries.query_network_select),
  }

pages.page_network_type_list = Page
  :from(projects.project, 'network_types')
  :with_rules {
    Page.Heading:from('network_types'),
    Page.Section:from_grid(grids.grid_network_type),
    Page.Query:from(queries.query_network_type_select),
  }

pages.page_guide_list = Page
  :from(projects.project, 'guides')
  :with_rules {
    Page.Heading:from('guides'),
    Page.Section:from_toolbar(toolbars.toolbar_guide),
    Page.Section:from_grid(grids.grid_guide),
    Page.Query:from(queries.query_guide_select),
  }

pages.page_guide_info = Page
  :from(projects.project, 'guide_info')
  :without_flex()
  :with_rules {
    Page.Prop:from_route('guideId'),
    Page.Heading:from('guide'),
    Page.Section:from_fieldset(fieldsets.fieldset_guide_info),
    Page.Query:from(queries.query_guide_get, { guideId = 'props.guideId' }),
  }

pages.page_guide_subject_list = Page
  :from(projects.project, 'guide_subjects')
  :with_rules {
    Page.Heading:from('guide_subjects'),
    Page.Section:from_toolbar(toolbars.toolbar_guide_subject),
    Page.Section:from_grid(grids.grid_guide_subject),
    Page.Query:from(queries.query_guide_subject_select),
  }

pages.page_guide_subject_info = Page
  :from(projects.project, 'guide_subject_info')
  :without_flex()
  :with_rules {
    Page.Prop:from_route('subjectId'),
    Page.Heading:from('guide_subject'),
    Page.Section:from_fieldset(fieldsets.fieldset_guide_subject_info),
    Page.Heading:from('guides', 'h3'),
    Page.Section:from_toolbar(toolbars.toolbar_guide),
    Page.Section
      :from_grid(grids.grid_guide)
      :with_variable('scrollable', 'false')
      :with_variable('hide-customer', 'true'),
    Page.Query:from(queries.query_guide_subject_get, { subjectId = 'props.subjectId' }),
    Page.Query:from(queries.query_guide_select, { subjectId = 'props.subjectId' }),
  }

return pages