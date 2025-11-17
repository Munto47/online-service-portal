import { createRouter, createWebHistory } from 'vue-router';
import DashboardView from '../views/DashboardView.vue';
import AccountView from '../views/AccountView.vue';
import BillingView from '../views/BillingView.vue';
import SupportView from '../views/SupportView.vue';

const routes = [
  { path: '/', name: 'dashboard', component: DashboardView },
  { path: '/accounts', name: 'accounts', component: AccountView },
  { path: '/billing', name: 'billing', component: BillingView },
  { path: '/support', name: 'support', component: SupportView },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
