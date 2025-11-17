<script setup>
import { reactive, ref } from 'vue';
import { supportApi } from '../services/api';

const repairForm = reactive({
  phoneNumber: '',
  deviceType: '手机',
  description: '',
});

const complaintForm = reactive({
  phoneNumber: '',
  category: '网络质量',
  detail: '',
});

const toast = ref('');
const loading = ref(false);

const submitRepair = async () => {
  loading.value = true;
  toast.value = '';
  try {
    await supportApi.submitRepair({ ...repairForm });
    toast.value = '维修申请已提交，预计 4 小时内联系客户。';
    Object.assign(repairForm, { phoneNumber: '', deviceType: '手机', description: '' });
  } catch (err) {
    toast.value = err.message ?? '维修申请提交失败';
  } finally {
    loading.value = false;
  }
};

const submitComplaint = async () => {
  loading.value = true;
  toast.value = '';
  try {
    await supportApi.submitComplaint({ ...complaintForm });
    toast.value = '投诉单已建立，客服将在 24 小时内反馈。';
    Object.assign(complaintForm, { phoneNumber: '', category: '网络质量', detail: '' });
  } catch (err) {
    toast.value = err.message ?? '投诉提交失败';
  } finally {
    loading.value = false;
  }
};
</script>

<template>
  <section class="support-grid">
    <article class="card">
      <h3>设备维修</h3>
      <form class="form-grid" @submit.prevent="submitRepair">
        <label>
          手机号
          <input v-model="repairForm.phoneNumber" required />
        </label>
        <label>
          设备类型
          <select v-model="repairForm.deviceType">
            <option value="手机">手机</option>
            <option value="CPE">CPE 终端</option>
            <option value="路由器">家庭路由器</option>
          </select>
        </label>
        <label class="full">
          故障描述
          <textarea v-model="repairForm.description" rows="3" placeholder="例如：无法拨号、信号弱等" required></textarea>
        </label>
        <button type="submit" :disabled="loading">提交维修单</button>
      </form>
    </article>

    <article class="card">
      <h3>客户投诉</h3>
      <form class="form-grid" @submit.prevent="submitComplaint">
        <label>
          手机号
          <input v-model="complaintForm.phoneNumber" required />
        </label>
        <label>
          投诉类别
          <select v-model="complaintForm.category">
            <option value="网络质量">网络质量</option>
            <option value="计费争议">计费争议</option>
            <option value="服务态度">服务态度</option>
          </select>
        </label>
        <label class="full">
          详情描述
          <textarea v-model="complaintForm.detail" rows="3" placeholder="描述客户诉求" required></textarea>
        </label>
        <button type="submit" :disabled="loading">提交投诉</button>
      </form>
    </article>
  </section>
  <p v-if="toast" class="feedback">{{ toast }}</p>
</template>
