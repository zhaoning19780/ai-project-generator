<script lang="ts">
export interface MenuItem {
  key: string
  label: string
  path: string
}
</script>

<script setup lang="ts">
import { computed } from 'vue'
import { useRoute } from 'vue-router'
// 引入Store
import { useLoginUserStore } from '@/stores/loginUser.ts'
import { message } from 'ant-design-vue'
import router from '@/router'
import { userLogout } from '@/api/userController'
const loginUserStore = useLoginUserStore()

defineProps<{
  logo?: string
  title?: string
  menuItems?: MenuItem[]
}>()

const route = useRoute()
const selectedKeys = computed(() => [route.path])

import { LogoutOutlined } from '@ant-design/icons-vue'
// 用户注销
const doLogout = async () => {
  const res = await userLogout()
  if (res.data.code === 0) {
    loginUserStore.setLoginUser({
      userName: '未登录',
    })
    message.success('退出登录成功')
    await router.push('/user/login')
  } else {
    message.error('退出登录失败，' + res.data.message)
  }
}
</script>

<template>
  <div class="global-header">
    <div class="header-left">
      <img v-if="logo" :src="logo" alt="logo" class="logo" />
      <span v-if="title" class="title">{{ title }}</span>
      <a-menu
        v-if="menuItems && menuItems.length > 0"
        mode="horizontal"
        :selected-keys="selectedKeys"
        class="header-menu"
      >
        <a-menu-item v-for="item in menuItems" :key="item.key">
          <router-link :to="item.path">{{ item.label }}</router-link>
        </a-menu-item>
      </a-menu>
    </div>
    <div class="header-right">
      <div v-if="loginUserStore.loginUser.id">
        <a-dropdown>
          <a-space>
            <a-avatar :src="loginUserStore.loginUser.userAvatar" class="user-avatar" />
            {{ loginUserStore.loginUser.userName ?? '无名' }}
          </a-space>
          <template #overlay>
            <a-menu>
              <a-menu-item @click="doLogout">
                <LogoutOutlined />
                退出登录
              </a-menu-item>
            </a-menu>
          </template>
        </a-dropdown>
      </div>
      <div v-else>
        <a-button type="primary">登录</a-button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.global-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 100%;
  padding: 0 24px;
}

.header-left {
  display: flex;
  align-items: center;
  gap: 16px;
}

.logo {
  height: 36px;
  width: auto;
}

.title {
  font-size: 18px;
  font-weight: 600;
  color: rgba(0, 0, 0, 0.85);
  white-space: nowrap;
}

.header-nav {
  display: flex;
  align-items: center;
  gap: 8px;
  margin-left: 16px;
}

.nav-link {
  color: rgba(0, 0, 0, 0.65);
  text-decoration: none;
  padding: 8px 16px;
  border-radius: 4px;
  transition:
    color 0.3s,
    background-color 0.3s;
}

.nav-link:hover {
  color: #1677ff;
  background-color: rgba(22, 119, 255, 0.05);
}

.nav-link-active {
  color: #1677ff !important;
  font-weight: 500;
}

.header-right {
  display: flex;
  align-items: center;
  width: 150px;
}
</style>
