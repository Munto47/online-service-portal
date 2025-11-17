<script setup>
import { ref, onMounted } from 'vue';
import { billingApi, supportApi } from '../services/api';

const stats = ref({
  accounts: 0,
  pendingPayments: 0,
  openTickets: 0,
});

const timeline = ref([]);
const loading = ref(false);
const error = ref('');

const loadData = async () => {
  loading.value = true;
  error.value = '';

  try {
    const [invoiceRes, ticketRes] = await Promise.allSettled([
      billingApi.fetchInvoices('demo'),
      supportApi.fetchTickets('demo'),
    ]);

    if (invoiceRes.status === 'fulfilled') {
      stats.value.pendingPayments = invoiceRes.value.data.content?.length ?? invoiceRes.value.data.length ?? 2;
    }

    if (ticketRes.status === 'fulfilled') {
      stats.value.openTickets = ticketRes.value.data.content?.length ?? ticketRes.value.data.length ?? 1;
      timeline.value = ticketRes.value.data.content ?? ticketRes.value.data ?? [];
    }

    stats.value.accounts = 128; // placeholder demo data
  } catch (err) {
    error.value = err.message ?? '无法加载仪表盘数据';
  } finally {
    loading.value = false;
  }
};

onMounted(loadData);
</script>

<template>
  <section class="dashboard">
    <header class="hero">
      <div>
        <p class="eyebrow">智慧营业厅</p>
        <h1>客户运营驾驶舱</h1>
        <p>实时掌握开户、缴费、投诉工单的处理情况。</p>
      </div>
      <button class="ghost" @click="loadData" :disabled="loading">
        {{ loading ? '加载中...' : '刷新数据' }}
      </button>
    </header>

    <div v-if="error" class="error">{{ error }}</div>

    <div class="stats">
      <article>
        <p>累计开户</p>
        <h2>{{ stats.accounts }}</h2>
        <small>今日新增 8 个</small>
      </article>
      <article>
        <p>待缴账单</p>
        <h2>{{ stats.pendingPayments }}</h2>
        <small>合计金额 ¥ 2,560</small>
      </article>
      <article>
        <p>未结服务单</p>
        <h2>{{ stats.openTickets }}</h2>
        <small>超时预警 1 单</small>
      </article>
    </div>

    <section class="timeline">
      <h3>最新服务处理</h3>
      <ul>
        <li v-for="item in timeline" :key="item.id">
          <div>
            <strong>{{ item.type ?? '服务单' }}</strong>
            <p>{{ item.description ?? item.detail ?? '暂无描述' }}</p>
          </div>
          <span>{{ item.status ?? '跟进中' }}</span>
        </li>
        <li v-if="!timeline.length" class="placeholder">
          暂无最新动态，可在服务管理中创建工单。
        </li>
      </ul>
    </section>
  </section>
</template>
