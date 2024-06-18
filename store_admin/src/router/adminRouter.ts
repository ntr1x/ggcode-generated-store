import PrivateView from '../components/views/PrivateView.vue'
import PageCustomers from '../components/pages/PageCustomers.vue'
import PageCustomerInfo from '../components/pages/PageCustomerInfo.vue'
import PageAgents from '../components/pages/PageAgents.vue'
import PageAgentInfo from '../components/pages/PageAgentInfo.vue'
import PageOrders from '../components/pages/PageOrders.vue'
import PageOrderInfo from '../components/pages/PageOrderInfo.vue'
import PagePayments from '../components/pages/PagePayments.vue'
import PageCategories from '../components/pages/PageCategories.vue'
import PageProducts from '../components/pages/PageProducts.vue'
import PageProductInfo from '../components/pages/PageProductInfo.vue'
import PageItems from '../components/pages/PageItems.vue'
import PageRegions from '../components/pages/PageRegions.vue'
import PageShops from '../components/pages/PageShops.vue'
import PageOrderTypes from '../components/pages/PageOrderTypes.vue'
import PageOrderStatuses from '../components/pages/PageOrderStatuses.vue'
import PagePaymentTypes from '../components/pages/PagePaymentTypes.vue'
import PagePaymentStatuses from '../components/pages/PagePaymentStatuses.vue'
import PageSourceTypes from '../components/pages/PageSourceTypes.vue'
import PagePromotionTypes from '../components/pages/PagePromotionTypes.vue'
import PagePromotions from '../components/pages/PagePromotions.vue'
import PageEvents from '../components/pages/PageEvents.vue'
import PageEventSources from '../components/pages/PageEventSources.vue'
import PageEventTypes from '../components/pages/PageEventTypes.vue'
import PageEventTopics from '../components/pages/PageEventTopics.vue'
import PageSubscriptions from '../components/pages/PageSubscriptions.vue'
import PageSubscriptionTypes from '../components/pages/PageSubscriptionTypes.vue'
import PageDispatches from '../components/pages/PageDispatches.vue'
import PageDispatchStatuses from '../components/pages/PageDispatchStatuses.vue'
import PageDispatchTypes from '../components/pages/PageDispatchTypes.vue'

export const adminRouter = {
  path: '/admin',
  component: PrivateView,
  children: [
    {
      path: 'customers',
      component: PageCustomers
    },
    {
      path: 'customers/:customerId',
      component: PageCustomerInfo
    },
    {
      path: 'agents',
      component: PageAgents
    },
    {
      path: 'agents/:agentId',
      component: PageAgentInfo
    },
    {
      path: 'orders',
      component: PageOrders
    },
    {
      path: 'orders/:orderId',
      component: PageOrderInfo
    },
    {
      path: 'payments',
      component: PagePayments
    },
    {
      path: 'categories',
      component: PageCategories
    },
    {
      path: 'products',
      component: PageProducts
    },
    {
      path: 'products/:productId',
      component: PageProductInfo
    },
    {
      path: 'items',
      component: PageItems
    },
    {
      path: 'regions',
      component: PageRegions
    },
    {
      path: 'shops',
      component: PageShops
    },
    {
      path: 'order_types',
      component: PageOrderTypes
    },
    {
      path: 'order_statuses',
      component: PageOrderStatuses
    },
    {
      path: 'payment_types',
      component: PagePaymentTypes
    },
    {
      path: 'payment_statuses',
      component: PagePaymentStatuses
    },
    {
      path: 'source_types',
      component: PageSourceTypes
    },
    {
      path: 'promotion_types',
      component: PagePromotionTypes
    },
    {
      path: 'promotions',
      component: PagePromotions
    },
    {
      path: 'events',
      component: PageEvents
    },
    {
      path: 'event_sources',
      component: PageEventSources
    },
    {
      path: 'event_types',
      component: PageEventTypes
    },
    {
      path: 'event_topics',
      component: PageEventTopics
    },
    {
      path: 'subscriptions',
      component: PageSubscriptions
    },
    {
      path: 'subscription_types',
      component: PageSubscriptionTypes
    },
    {
      path: 'dispatches',
      component: PageDispatches
    },
    {
      path: 'dispatch_statuses',
      component: PageDispatchStatuses
    },
    {
      path: 'dispatch_types',
      component: PageDispatchTypes
    },
    {
      path: '',
      redirect: '/admin/customers'
    }
  ]
}
