import ViewAdmin from '../components/views/ViewAdmin.vue'
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
import PageTemplates from '../components/pages/PageTemplates.vue'
import PageTemplateShapes from '../components/pages/PageTemplateShapes.vue'
import PageTemplateTypes from '../components/pages/PageTemplateTypes.vue'
import PageNetworks from '../components/pages/PageNetworks.vue'
import PageNetworkTypes from '../components/pages/PageNetworkTypes.vue'
import PageGuides from '../components/pages/PageGuides.vue'
import PageGuideInfo from '../components/pages/PageGuideInfo.vue'
import PageGuideSubjects from '../components/pages/PageGuideSubjects.vue'
import PageGuideSubjectInfo from '../components/pages/PageGuideSubjectInfo.vue'

export const adminRouter = {
  path: '/admin',
  component: ViewAdmin,
  children: [
    {
      path: 'customers',
      component: PageCustomers,
      props: true,
      meta: {
        requiresAuth: true,
        roles: ["realm:developer","realm:admin","realm:support"]
      }
    },
    {
      path: 'customers/:customerId',
      component: PageCustomerInfo,
      props: true,
      meta: {
        requiresAuth: true,
        roles: ["realm:developer","realm:admin","realm:support"]
      }
    },
    {
      path: 'agents',
      component: PageAgents,
      props: true,
      meta: {
        requiresAuth: true,
        roles: ["realm:developer","realm:admin"]
      }
    },
    {
      path: 'agents/:agentId',
      component: PageAgentInfo,
      props: true,
      meta: {
        requiresAuth: true,
        roles: ["realm:developer","realm:admin"]
      }
    },
    {
      path: 'orders',
      component: PageOrders,
      props: true,
      meta: {
        requiresAuth: true,
        roles: ["realm:developer","realm:admin"]
      }
    },
    {
      path: 'orders/:orderId',
      component: PageOrderInfo,
      props: true,
      meta: {
        requiresAuth: true,
        roles: ["realm:developer","realm:admin"]
      }
    },
    {
      path: 'payments',
      component: PagePayments,
      props: true,
      meta: {
        requiresAuth: true,
        roles: ["realm:developer","realm:admin"]
      }
    },
    {
      path: 'categories',
      component: PageCategories,
      props: true,
      meta: {
        requiresAuth: true,
        roles: ["realm:developer","realm:admin"]
      }
    },
    {
      path: 'products',
      component: PageProducts,
      props: true,
      meta: {
        requiresAuth: true,
        roles: ["realm:developer","realm:admin"]
      }
    },
    {
      path: 'products/:productId',
      component: PageProductInfo,
      props: true,
      meta: {
        requiresAuth: true,
        roles: ["realm:developer","realm:admin"]
      }
    },
    {
      path: 'items',
      component: PageItems,
      props: true,
      meta: {
        requiresAuth: true,
        roles: ["realm:developer","realm:admin"]
      }
    },
    {
      path: 'regions',
      component: PageRegions,
      props: true,
      meta: {
        requiresAuth: true,
        roles: ["realm:developer","realm:admin"]
      }
    },
    {
      path: 'shops',
      component: PageShops,
      props: true,
      meta: {
        requiresAuth: true,
        roles: ["realm:developer","realm:admin"]
      }
    },
    {
      path: 'order_types',
      component: PageOrderTypes,
      props: true,
      meta: {
        requiresAuth: true,
        roles: ["realm:developer","realm:admin"]
      }
    },
    {
      path: 'order_statuses',
      component: PageOrderStatuses,
      props: true,
      meta: {
        requiresAuth: true,
        roles: ["realm:developer","realm:admin"]
      }
    },
    {
      path: 'payment_types',
      component: PagePaymentTypes,
      props: true,
      meta: {
        requiresAuth: true,
        roles: ["realm:developer","realm:admin"]
      }
    },
    {
      path: 'payment_statuses',
      component: PagePaymentStatuses,
      props: true,
      meta: {
        requiresAuth: true,
        roles: ["realm:developer","realm:admin"]
      }
    },
    {
      path: 'source_types',
      component: PageSourceTypes,
      props: true,
      meta: {
        requiresAuth: true,
        roles: ["realm:developer","realm:admin"]
      }
    },
    {
      path: 'promotion_types',
      component: PagePromotionTypes,
      props: true,
      meta: {
        requiresAuth: true,
        roles: ["realm:developer","realm:admin"]
      }
    },
    {
      path: 'promotions',
      component: PagePromotions,
      props: true,
      meta: {
        requiresAuth: true,
        roles: ["realm:developer","realm:admin"]
      }
    },
    {
      path: 'events',
      component: PageEvents,
      props: true,
      meta: {
        requiresAuth: true,
        roles: ["realm:developer","realm:admin","realm:support"]
      }
    },
    {
      path: 'event_sources',
      component: PageEventSources,
      props: true,
      meta: {
        requiresAuth: true,
        roles: ["realm:developer","realm:admin"]
      }
    },
    {
      path: 'event_types',
      component: PageEventTypes,
      props: true,
      meta: {
        requiresAuth: true,
        roles: ["realm:developer","realm:admin"]
      }
    },
    {
      path: 'event_topics',
      component: PageEventTopics,
      props: true,
      meta: {
        requiresAuth: true,
        roles: ["realm:developer","realm:admin"]
      }
    },
    {
      path: 'subscriptions',
      component: PageSubscriptions,
      props: true,
      meta: {
        requiresAuth: true,
        roles: ["realm:developer","realm:admin","realm:support"]
      }
    },
    {
      path: 'subscription_types',
      component: PageSubscriptionTypes,
      props: true,
      meta: {
        requiresAuth: true,
        roles: ["realm:developer","realm:admin"]
      }
    },
    {
      path: 'dispatches',
      component: PageDispatches,
      props: true,
      meta: {
        requiresAuth: true,
        roles: ["realm:developer","realm:admin","realm:support"]
      }
    },
    {
      path: 'dispatch_statuses',
      component: PageDispatchStatuses,
      props: true,
      meta: {
        requiresAuth: true,
        roles: ["realm:developer","realm:admin"]
      }
    },
    {
      path: 'dispatch_types',
      component: PageDispatchTypes,
      props: true,
      meta: {
        requiresAuth: true,
        roles: ["realm:developer","realm:admin"]
      }
    },
    {
      path: 'templates',
      component: PageTemplates,
      props: true,
      meta: {
        requiresAuth: true,
        roles: ["realm:developer","realm:admin","realm:support"]
      }
    },
    {
      path: 'template_shapes',
      component: PageTemplateShapes,
      props: true,
      meta: {
        requiresAuth: true,
        roles: ["realm:developer","realm:admin"]
      }
    },
    {
      path: 'template_types',
      component: PageTemplateTypes,
      props: true,
      meta: {
        requiresAuth: true,
        roles: ["realm:developer","realm:admin"]
      }
    },
    {
      path: 'networks',
      component: PageNetworks,
      props: true,
      meta: {
        requiresAuth: true,
        roles: ["realm:developer","realm:admin"]
      }
    },
    {
      path: 'network_types',
      component: PageNetworkTypes,
      props: true,
      meta: {
        requiresAuth: true,
        roles: ["realm:developer","realm:admin"]
      }
    },
    {
      path: 'guides',
      component: PageGuides,
      props: true,
      meta: {
        requiresAuth: true,
        roles: ["realm:developer","realm:admin"]
      }
    },
    {
      path: 'guides/:guideId',
      component: PageGuideInfo,
      props: true,
      meta: {
        requiresAuth: true,
        roles: ["realm:developer","realm:admin","realm:support"]
      }
    },
    {
      path: 'guide_subjects',
      component: PageGuideSubjects,
      props: true,
      meta: {
        requiresAuth: true,
        roles: ["realm:developer","realm:admin"]
      }
    },
    {
      path: 'guide_subjects/:subjectId',
      component: PageGuideSubjectInfo,
      props: true,
      meta: {
        requiresAuth: true,
        roles: ["realm:developer","realm:admin","realm:support"]
      }
    },
    {
      path: '',
      redirect: '/admin/customers'
    }
  ]
}
