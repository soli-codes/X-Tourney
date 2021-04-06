<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <router-view />
    <the-main-help />
  </div>
</template>

<script>
import TheMainHelp from './components/TheMainHelp.vue'

export default {
  components: { TheMainHelp },
  
}
</script>

<style lang="scss">
  // Custom.scss
// Option B: Include parts of Bootstrap

// 1. Include functions first (so you can manipulate colors, SVGs, calc, etc)
@import "~bootstrap/scss/functions";

// 2. Include any default variable overrides here

// 3. Include remainder of required Bootstrap stylesheets
@import "~bootstrap/scss/variables";
@import "~bootstrap/scss/mixins";

// 4. Include any optional Bootstrap components as you like
// @import "~bootstrap/scss/root";
// @import "~bootstrap/scss/reboot";
// @import "~bootstrap/scss/type";
// @import "~bootstrap/scss/images";
@import "~bootstrap/scss/containers";
@import "~bootstrap/scss/grid";
// @import "~bootstrap/scss/tables";
@import "~bootstrap/scss/forms";
// @import "~bootstrap/scss/buttons";
// @import "~bootstrap/scss/transitions";
@import "~bootstrap/scss/dropdown";
// @import "~bootstrap/scss/button-group";
@import "~bootstrap/scss/nav";
@import "~bootstrap/scss/navbar";
// @import "~bootstrap/scss/card";
// @import "~bootstrap/scss/accordion";
// @import "~bootstrap/scss/breadcrumb";
// @import "~bootstrap/scss/pagination";
// @import "~bootstrap/scss/badge";
// @import "~bootstrap/scss/alert";
// @import "~bootstrap/scss/progress";
// @import "~bootstrap/scss/list-group";
// @import "~bootstrap/scss/close";
// @import "~bootstrap/scss/toasts";
// @import "~bootstrap/scss/modal";
// @import "~bootstrap/scss/tooltip";
// @import "~bootstrap/scss/popover";
// @import "~bootstrap/scss/carousel";
// @import "~bootstrap/scss/spinners";
// @import "~bootstrap/scss/offcanvas";
@import "~bootstrap/scss/utilities";

// 5. Add additional custom code here
</style>
