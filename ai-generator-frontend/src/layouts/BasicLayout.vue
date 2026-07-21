<script setup lang="ts">
import GlobalHeader from './GlobalHeader.vue'
import GlobalFooter from './GlobalFooter.vue'
import type { MenuItem } from './GlobalHeader.vue'

const logo = new URL('@/assets/logo.png', import.meta.url).href
const title = 'AI零代码应用平台'

const menuItems: MenuItem[] = [
  { key: 'home', label: '首页', path: '/' },
  { key: 'about', label: '关于', path: '/about' },
]
</script>

<template>
  <a-layout class="basic-layout">
    <a-layout-header class="layout-header">
      <GlobalHeader :logo="logo" :title="title" :menu-items="menuItems" />
    </a-layout-header>

    <a-layout-content class="layout-content">
      <div class="content-wrapper">
        <router-view />
      </div>
    </a-layout-content>

    <a-layout-footer class="layout-footer">
      <GlobalFooter />
    </a-layout-footer>
  </a-layout>
</template>

<style scoped>
.basic-layout {
  min-height: 100vh;
}

.layout-header {
  position: sticky;
  top: 0;
  z-index: 10;
  padding: 0;
  background: #fff;
  border-bottom: 1px solid #f0f0f0;
}

.layout-content {
  padding: 24px;
}

.content-wrapper {
  max-width: 1200px;
  margin: 0 auto;
  min-height: calc(100vh - 64px - 70px - 48px);
}

.layout-footer {
  padding: 24px 50px;
  background: #f0f0f0;
}

@media (max-width: 768px) {
  .layout-content {
    padding: 16px;
  }

  .layout-footer {
    padding: 16px;
  }

  .content-wrapper {
    min-height: calc(100vh - 64px - 54px - 32px);
  }
}
</style>
