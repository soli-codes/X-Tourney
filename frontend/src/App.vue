<template>
  <div id="app">
    <div class="container-fluid">
      <NavBar id="navbar" />
      <div class="d-flex vh row">
        <sidebar class="position-fixed vh-100 sidebar" id="sidebar" />
        <div
          class="flex-fill text-info navbar-margin-left margin-toggle navbar-margin-top bg-dark"
          id="main"
        >
          <div id="marginToggle">
            <router-view />
          </div>
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
$dark: #181818;
$primary: #65ccb8;
$info: #00fff2;
$warning: lime;
$muted: #3b945e;
$secondary: #57ba98;
$gray-200: #f2f2f2;
$danger: rgb(163, 73, 164);
@import '../node_modules/bootstrap/scss/bootstrap';
@import url('https://fonts.googleapis.com/css2?family=Inconsolata:wght@400;500&display=swap');

* {
  font-family: Inconsolata, Arial, Helvetica, sans-serif;
}
html {
  background-color: #202020 !important;
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
}
.top-nav-color {
  background: rgb(16, 16, 16);
  background: linear-gradient(
    152deg,
    rgba(16, 16, 16, 1) 0%,
    rgba(16, 16, 16, 1) 50%,
    #65bbc8 100%
  );
}

.side-nav-color {
  background: rgb(16, 16, 16);
  background: linear-gradient(
    152deg,
    rgba(16, 16, 16, 1) 0%,
    rgba(16, 16, 16, 1) 50%,
    #65bbc8 100%
  );
}
.vh {
  height: 100vh;
}

.text-primary:hover {
  color: #ff455d !important;
  transition-duration: 300ms;
}
.margin-toggle {
  padding-left: 70px;
}

.navbar-margin-top {
  margin-top: 100px;
}
.navbar-margin-left {
  margin-left: 200px;
  padding-left: 20px;
}

#navbar {
  height: 100px;
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
    background: #070222;
  }
  .sidebar {
    display: none;
  }
}
</style>
