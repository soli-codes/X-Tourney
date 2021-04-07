<template>
  <div class="p-4 text-white bg-dark">
    <ul class="nav nav-pills flex-column position-sticky padding">
      <li class="nav-item">
        <router-link :to="{ name: 'help' }" class="nav-link text-primary">
          FAQ/Help
        </router-link>
      </li>
      <li>
        <a href="#" class="nav-link text-primary">
          Browse Tournaments
        </a>
      </li>
      <li>
        <a href="#" class="nav-link text-primary">
          My Tournaments
        </a>
      </li>
      <li>
        <a href="#" class="nav-link text-primary">
          Products
        </a>
      </li>
      <li>
        <a href="#" class="nav-link text-primary">
          Customers
        </a>
      </li>
      <div class="d-flex align-items-left flex-column">
        <div class="fixed">
          <a href="#" class="text-primary nav-link">
            Toggle Display
          </a>
        </div>
      </div>
    </ul>
  </div>
</template>

<style scoped>
.fixed {
  position: sticky;
  top: 86vh;
  align-items: center;
}

.padding {
  padding-top: 100px;
}
</style>
