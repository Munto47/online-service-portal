<script setup>
import { ref } from 'vue';
import { billingApi } from '../services/api';

const phoneNumber = ref('');
const invoices = ref([]);
const loading = ref(false);
const toast = ref('');

const search = async () => {
  loading.value = true;
  toast.value = '';
  invoices.value = [];
  try {
    const { data } = await billingApi.fetchInvoices(phoneNumber.value);
    invoices.value = data.content ?? data ?? [];
    if (!invoices.value.length) {
      toast.value = '暂无需要缴纳的账单。';
    }
  } catch (err) {
    toast.value = err.message ?? '查询账单失败';
  } finally {
    loading.value = false;
  }
};

const pay = async (invoiceId) => {
  loading.value = true;
  toast.value = '';
  try {
    await billingApi.payInvoice(invoiceId);
    toast.value = `账单 ${invoiceId} 支付成功`;
    invoices.value = invoices.value.filter((invoice) => invoice.id !== invoiceId);
  } catch (err) {
    toast.value = err.message ?? '支付失败';
  } finally {
    loading.value = false;
  }
};
</script>

<template>
  <section class="card">
    <h2>费用中心</h2>
    <form class="inline-form" @submit.prevent="search">
      <input v-model="phoneNumber" placeholder="输入客户手机号" required />
      <button type="submit" :disabled="loading">{{ loading ? '查询中…' : '查询账单' }}</button>
    </form>

    <p v-if="toast" class="feedback">{{ toast }}</p>

    <table v-if="invoices.length" class="data-table">
      <thead>
        <tr>
          <th>账单号</th>
          <th>周期</th>
          <th>金额 (¥)</th>
          <th>状态</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="invoice in invoices" :key="invoice.id">
          <td>{{ invoice.id }}</td>
          <td>{{ invoice.period ?? '2024-10' }}</td>
          <td>{{ invoice.amount ?? 0 }}</td>
          <td>{{ invoice.status ?? '待缴' }}</td>
          <td>
            <button @click="pay(invoice.id)" :disabled="loading">支付</button>
          </td>
        </tr>
      </tbody>
    </table>
  </section>
</template>
