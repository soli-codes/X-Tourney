<template>
  <div id="app">
    <div class="navigation">
      <NavBar />
      <sidebar />
    </div>
    <div class="container">
      <router-view />
    </div>
  </div>
</template>

<script>
import NavBar from '@/components/NavBar';
import Sidebar from './components/Sidebar.vue';
export default {
  components: {
    NavBar,
    Sidebar,
  },
};
</script>

<style lang="scss">
// Include any default variable overrides here (though functions won't be available)
$dark: #01012b;
$primary: #d1f7ff;
$info: #00eeff;
$danger: #ff2a8d;
$muted: #005678;
// $gray-200: #111; - Dropdown hover color
@import '../node_modules/bootstrap/scss/bootstrap';
</style>
