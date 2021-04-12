<template>
  <nav
    class="navbar navbar-expand-lg navbar-primary navbar-dark top-nav-color fixed-top"
  >
    <a href="../views/Home.vue">
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
              <router-link
                :to="{ name: 'teams' }"
                class="dropdown-item text-primary"
                >Teams</router-link
              >
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
          <router-link :to="{ name: 'profileTeams' }" class="nav-link active text-primary" aria-current="page" href="#"
            >My Teams</router-link
          >
        </li>
        <li class="nav-item sizing collapse-size">
          <router-link :to="{ name: 'profileTournaments'}" class="nav-link text-primary" href="#">My Tournaments</router-link>
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
        href="https://www.techelevator.com/?gclid=CjwKCAjwvMqDBhB8EiwA2iSmPGzzFasx6lXvj4qZY-ZzfVY_4nL0ogGcSvLNPRWZh6H_WYuw0coe5RoC1QEQAvD_BwE"
        target="_blank"
        >Careers</a
      >
      <router-link :to="{ name: 'login'} "
        class="nav-link active text-primary d-flex collapse-size"
        aria-current="page"
        href="#"
        >Login</router-link
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
