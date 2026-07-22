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

defineProps<{
  logo?: string
  title?: string
  menuItems?: MenuItem[]
}>()

const route = useRoute()
const selectedKeys = computed(() => [route.path])
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
      <a-button type="primary">登录</a-button>
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

.header-menu {
  background: transparent;
  border-bottom: none;
  line-height: 64px;
  margin-left: 16px;
}

.header-menu .ant-menu-item {
  color: rgba(0, 0, 0, 0.65);
}

.header-menu .ant-menu-item:hover {
  color: #1677ff;
}

.header-menu .ant-menu-item-selected {
  color: #1677ff !important;
  font-weight: 500;
}

.header-menu .ant-menu-item-selected a {
  color: #1677ff;
}

.header-menu .ant-menu-item a:hover {
  color: #1677ff;
}

.header-right {
  display: flex;
  align-items: center;
}
</style>
