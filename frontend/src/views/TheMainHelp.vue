<template>
  <div class="bg-primary">
    <!-- make each div into a row flexbox // each will be a StepX button-->
    <router-link
      to="#"
      tag="div"
      class="d-flex flex-row justify-content-between m-3 bg-dark border border-5 border-danger rounded-3"
    >
      <div>
        <img
          class="image border-left"
          src="https://www.wolflair.com/wp-content/uploads/2017/01/placeholder.jpg"
        />
      </div>
      <!-- make div into vertical flexbox // will contain StepX title and description -->
      <div class="d-flex flex-column justify-content-around">
        <h2>Step One</h2>
        <p>lorem ipsum</p>
      </div>
      <div>
        <img
          class="image border-right"
          src="https://www.wolflair.com/wp-content/uploads/2017/01/placeholder.jpg"
        />
      </div>
    </router-link>
    <router-link
      to="#"
      tag="div"
      class="d-flex flex-row justify-content-between m-3 bg-dark border border-5 border-danger rounded-3"
    >
      <div>
        <img
          class="image border-left"
          src="https://www.wolflair.com/wp-content/uploads/2017/01/placeholder.jpg"
        />
      </div>
      <!-- make div into vertical flexbox // will contain StepX title and description -->
      <div class="d-flex flex-column">
        <h2>Step One</h2>
        <p>lorem ipsum</p>
      </div>
      <div>
        <img
          class="image border-right"
          src="https://www.wolflair.com/wp-content/uploads/2017/01/placeholder.jpg"
        />
      </div>
    </router-link>
    <router-link
      to="#"
      tag="div"
      class="d-flex flex-row justify-content-between m-3 bg-dark border border-5 border-danger rounded-3"
    >
      <div>
        <img
          class="image border-left"
          src="https://www.wolflair.com/wp-content/uploads/2017/01/placeholder.jpg"
        />
      </div>
      <!-- make div into vertical flexbox // will contain StepX title and description -->
      <div class="d-flex flex-column">
        <h2>Step One</h2>
        <p>lorem ipsum</p>
      </div>
      <div>
        <img
          class="image border-right"
          src="https://www.wolflair.com/wp-content/uploads/2017/01/placeholder.jpg"
        />
      </div>
    </router-link>
    <router-link
      to="#"
      tag="div"
      class="d-flex flex-row justify-content-between m-3 bg-dark border border-5 border-danger rounded-3"
    >
      <div>
        <img
          class="image border-left"
          src="https://www.wolflair.com/wp-content/uploads/2017/01/placeholder.jpg"
        />
      </div>
      <!-- make div into vertical flexbox // will contain StepX title and description -->
      <div class="d-flex flex-column">
        <h2>Step One</h2>
        <p>lorem ipsum</p>
      </div>
      <div>
        <img
          class="image border-right"
          src="https://www.wolflair.com/wp-content/uploads/2017/01/placeholder.jpg"
        />
      </div>
    </router-link>
  </div>
</template>

<script>
export default {
  name: 'TheMainHelp',
};
</script>

<style scoped>
.image {
  height: 200px;
  width: 200px;
}

.border {
  border-radius: 25px !important;
}

.border-left {
  border-top-left-radius: 20px;
  border-bottom-left-radius: 20px;
}

.border-right {
  border-top-right-radius: 20px;
  border-bottom-right-radius: 20px;
}
</style>
