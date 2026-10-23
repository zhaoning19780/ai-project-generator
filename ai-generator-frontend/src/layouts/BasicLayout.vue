<script setup lang="ts">
import GlobalHeader from '../components/GlobalHeader.vue'
import GlobalFooter from '../components/GlobalFooter.vue'
import type { MenuItem } from '../components/GlobalHeader.vue'
import { computed } from 'vue'
import { useLoginUserStore } from '@/stores/loginUser'
import type { MenuProps } from 'ant-design-vue'

const logo = new URL('@/assets/logo.png', import.meta.url).href
const title = 'AI零代码应用平台'

// 菜单配置项
const menuItems = [
  {
    key: '/',
    path: '/',
    label: '主页',
    title: '首页',
  },
  {
    key: '/admin/userManage',
    path: '/admin/userManage',
    label: '用户管理',
    title: '用户管理',
  },
  {
    key: 'others',
    path: '/',
    label: '编程导航',
    title: '编程导航',
  },
]

// // 过滤菜单项
// const filterMenus = (menus = [] as MenuProps['items']) => {
//   return menus?.filter((menu) => {
//     const menuKey = menu?.key as string
//     if (menuKey?.startsWith('/admin')) {
//       const loginUser = loginUserStore.loginUser
//       if (!loginUser || loginUser.userRole !== 'admin') {
//         return false
//       }
//     }
//     return true
//   })
// }

// // 展示在菜单的路由数组
// const menuItems = computed(() => {
//   return filterMenus(originItems)
// })
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
  max-width: 1500px;
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
