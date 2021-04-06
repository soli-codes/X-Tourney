<template>
  <div id="app">
    <div class="navigation">
      <NavBar />
      <div class="d-flex vh">
        <sidebar class="sidebar" />
        <div class="flex-fill">
          <router-view />
        </div>
      </div>
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
$info: #00fff2;
$danger: #ff2a8d;
$muted: #005678;
$secondary: #020222;
// $gray-200: #111; - Dropdown hover color
@import '../node_modules/bootstrap/scss/bootstrap';

.vh {
  height: 100vh;
}

.nav-link:hover {
  color: #00fff2 !important;
  transition-duration: 400ms;
}

.sizing {
  width: 150px;
  text-align: center;
}

.image-size {
  width: 100px;
  height: 100px;
}
@media (max-width: 991px) {
  .collapse-size {
    text-align: left;
    padding: 0.2rem 0.2rem;
  }
  .sidebar {
    display: none;
  }
}
</style>
