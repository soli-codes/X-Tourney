<template>
  <div id="app">
    <div class="container-fluid">
      <NavBar />
      <div class="d-flex vh row">
        <sidebar class="sidebar position-fixed vh-100 sidebar" id="sidebar" />
        <div
          class="flex-fill text-info navbar-margin-left navbar-margin-top bg-dark"
          id="main"
        >
          <router-view />
        </div>
      </div>
    </div>
    <svg
      xmlns="http://www.w3.org/2000/svg"
      width="40"
      height="40"
      fill="currentColor"
      class="bi bi-arrow-right-circle display sideArrow"
      viewBox="0 0 16 16"
      id="sideArrow"
      @click="noDisplay"
    >
      <path
        fill-rule="evenodd"
        d="M1 8a7 7 0 1 0 14 0A7 7 0 0 0 1 8zm15 0A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM4.5 7.5a.5.5 0 0 0 0 1h5.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3a.5.5 0 0 0 0-.708l-3-3a.5.5 0 1 0-.708.708L10.293 7.5H4.5z"
      />
    </svg>
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
  methods: {
    noDisplay() {
      document.getElementById('sidebar').classList.toggle('display');
      document.getElementById('sideArrow').classList.toggle('display');
      document.getElementById('main').classList.toggle('navbar-margin-left');
    },
  },
};
</script>

<style lang="scss">
// Include any default variable overrides here (though functions won't be available)
$dark: #182628;
$primary: #65ccb8;
$info: #00fff2;
$warning: lime;
$muted: #3b945e;
$secondary: #57ba98;
$gray-200: #f2f2f2;
$danger: rgb(163, 73, 164);
@import '../node_modules/bootstrap/scss/bootstrap';
@import url('https://fonts.googleapis.com/css2?family=Inconsolata:wght@400;500&display=swap');

*{
  font-family: Inconsolata,Arial, Helvetica, sans-serif;
}
html {
  background-color: #070222 !important;
}

.sideArrow {
  position: fixed;
  color: #b71dde;
  top: 57%;
  left: 1%;
}
#sideArrow:hover {
  cursor: pointer;
}
.display {
  display: none;
  width: 0px !important;
  height: 0px;
}
.top-nav-color {
  background: #57ba98;
  // background: linear-gradient(
  //   180deg,
  //   #57ba98 0%,
  //   #51ad8d 25%,
  //   #3b945e 100%
  // );
}

.side-nav-color {
  background: #57ba98;
  // background: linear-gradient(
  //   30deg,
  //   rgba(0, 11, 24, 1) 0%,
  //   rgba(0, 23, 45, 1) 25%,
  //   rgba(0, 38, 77, 1) 100%
  // );
}
.vh {
  height: 100vh;
}

.text-primary:hover {
  color: #ff455d !important;
  transition-duration: 300ms;
}

.navbar-margin-top {
  margin-top: 112px;
}
.navbar-margin-left {
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
  .navbar-margin-left {
    margin-left: 0px;
  }
  .sidebar {
    display: none;
  }
}
</style>
