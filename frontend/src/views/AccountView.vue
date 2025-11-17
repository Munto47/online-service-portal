<script setup>
import { reactive, ref } from 'vue';
import { accountApi } from '../services/api';

const form = reactive({
  name: '',
  idNumber: '',
  phoneNumber: '',
  planType: '5G-premium',
});

const feedback = ref('');
const loading = ref(false);

const submit = async () => {
  loading.value = true;
  feedback.value = '';
  try {
    await accountApi.createAccount({ ...form });
    feedback.value = '开户申请提交成功，预计 2 分钟内可生效。';
    Object.assign(form, { name: '', idNumber: '', phoneNumber: '', planType: '5G-premium' });
  } catch (error) {
    feedback.value = error.message ?? '开户失败，请稍后再试。';
  } finally {
    loading.value = false;
  }
};
</script>

<template>
  <section class="card">
    <h2>在线开户</h2>
    <p class="description">
      客户经理可帮助客户在线提交开户请求，系统会自动调用鉴权、库存和计费服务。
    </p>

    <form @submit.prevent="submit" class="form-grid">
      <label>
        客户姓名
        <input v-model="form.name" required placeholder="张三" />
      </label>
      <label>
        身份证号
        <input v-model="form.idNumber" required minlength="18" maxlength="18" placeholder="4401**********1234" />
      </label>
      <label>
        手机号
        <input v-model="form.phoneNumber" required placeholder="138****8888" />
      </label>
      <label>
        套餐
        <select v-model="form.planType">
          <option value="5G-premium">5G 尊享 199</option>
          <option value="5G-standard">5G 畅享 129</option>
          <option value="broadband-bundle">宽带融合 169</option>
        </select>
      </label>

      <button type="submit" :disabled="loading">
        {{ loading ? '提交中…' : '提交开户请求' }}
      </button>
    </form>

    <p v-if="feedback" class="feedback">{{ feedback }}</p>
  </section>
</template>
