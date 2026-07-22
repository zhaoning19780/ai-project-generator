<script setup lang="ts">
const linkUrl = 'https://github.com/zhaoning19780/ai-project-generator'
</script>

<template>
  <div class="global-footer">
    <a :href="linkUrl" target="_blank" rel="noopener noreferrer">
      AI零代码应用平台
    </a>
  </div>
</template>

<style scoped>
.global-footer {
  text-align: center;
  padding: 0;
}

.global-footer a {
  color: rgba(0, 0, 0, 0.45);
  font-size: 14px;
  text-decoration: none;
  transition: color 0.3s;
}

.global-footer a:hover {
  color: rgba(0, 0, 0, 0.85);
}
</style>
