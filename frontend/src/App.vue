<template>
  <div id="app">
    <div class="container-fluid">
      <NavBar />
      <div class="d-flex vh row">
        <sidebar class="sidebar position-fixed vh-100 sidebar" />
        <div class="flex-fill text-info navbar-margin bg-secondary">
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
$danger: #ff455d;
$muted: #005678;
$secondary: #020222;
$gray-200: #01012b;
@import '../node_modules/bootstrap/scss/bootstrap';

html {
  background-color: #070222 !important;
}

.navigation-color {
  background-color: #020222;
}

.vh {
  height: 100vh;
}

.text-primary:hover {
  color: #ff455d !important;
  transition-duration: 400ms;
}

.navbar-margin {
  margin-top: 112px;
  margin-left: 200px;
}

.sizing {
  width: 150px;
  text-align: center;
}

.image-size {
  width: 100px;
  height: 100px;
}

.sidebar {
  width: 200px;
  padding: 0px;
}
@media (max-width: 991px) {
  .collapse-size {
    text-align: left;
    padding: 0.2rem 0.2rem;
  }
  .navbar-margin {
    margin-left: 0px;
  }
  .sidebar {
    display: none;
  }
}
</style>
