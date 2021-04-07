<template>
  <nav class="navbar navbar-expand-lg navbar-primary bg-dark fixed-top">
    <a href="#">
      <img
        src="../assets/vectorBlueLogoXTourney.png"
        alt=""
        class="image-size p-0 m-0 spinny"
      />
    </a>
    <button
      class="navbar-toggler"
      type="button"
      data-bs-toggle="collapse"
      data-bs-target="#navbarSupportedContent"
      aria-controls="navbarSupportedContent"
      aria-expanded="false"
      aria-label="Toggle navigation"
    >
      <span class="navbar-toggler-icon "></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item dropdown sizing collapse-size">
          <a
            class="nav-link dropdown-toggle text-primary"
            href="#"
            id="navbarDropdown"
            role="button"
            data-bs-toggle="dropdown"
            aria-expanded="false"
          >
            Browse
          </a>
          <ul class="dropdown-menu bg-dark" aria-labelledby="navbarDropdown">
            <li>
              <a class="dropdown-item text-primary" href="#">Teams</a>
            </li>
            <li>
              <router-link
                :to="{ name: 'tournaments' }"
                class="dropdown-item text-primary"
                >Tournaments</router-link
              >
            </li>
          </ul>
        </li>
        <li class="nav-item sizing collapse-size">
          <a class="nav-link active text-primary" aria-current="page" href="#"
            >My Teams</a
          >
        </li>
        <li class="nav-item sizing collapse-size">
          <a class="nav-link text-primary" href="#">My Tournaments</a>
        </li>
      </ul>
      <a
        class="nav-link active text-primary d-flex collapse-size"
        aria-current="page"
        href="#"
        >Meet the team</a
      >
      <a
        class="nav-link active text-primary d-flex collapse-size"
        aria-current="page"
        href="#"
        >Careers</a
      >
      <a
        class="nav-link active text-primary d-flex collapse-size"
        aria-current="page"
        href="#"
        >Login</a
      >
    </div>
  </nav>
</template>

<style scoped>
.sizing {
  width: 150px;
  text-align: center;
}

.image-size {
  width: 100px;
  height: 100px;
}
@media (max-width: 1000px) {
  .collapse-size {
    text-align: left;
    padding: 0.2rem 0.2rem;
  }
}
.spinny:hover {
  transform: rotate(360deg);
  transition-duration: 750ms;
}
</style>
