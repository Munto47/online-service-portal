<script setup>
import { RouterLink, RouterView } from 'vue-router';
</script>

<template>
  <div class="layout">
    <aside class="sidebar">
      <div class="brand">
        <span>移动网上营业厅</span>
        <small>Online Service Portal</small>
      </div>
      <nav>
        <RouterLink to="/">仪表盘</RouterLink>
        <RouterLink to="/accounts">开户中心</RouterLink>
        <RouterLink to="/billing">缴费中心</RouterLink>
        <RouterLink to="/support">投诉与维修</RouterLink>
      </nav>
    </aside>

    <section class="content">
      <RouterView />
    </section>
  </div>
</template>
