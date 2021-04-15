<template>
  <nav
    class="navbar navbar-expand-lg navbar-primary navbar-dark top-nav-color fixed-top"
  >
    <router-link :to="{ name: 'home' }" class="m-0 p-0">
      <div class="m-0 p-0">
        <img
          src="../assets/vectorBlueLogoXTourney.png"
          alt=""
          class="image-size spinny navbar-brand"
          id="navbarImage"
        />
      </div>
    </router-link>
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
        <li class="nav-item sizing collapse-size homeButtonAlignment">
          <router-link
            :to="{ name: 'home' }"
            class="nav-link text-primary"
            href="#"
            >Home</router-link
          >
        </li>
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
      </ul>
      <li>
        <router-link
          class="nav-link active text-primary d-flex collapse-size"
          aria-current="page"
          :to="{ name: 'meetTheTeam' }"
          >Meet the team</router-link
        >
      </li>
      <li>
        <a
          class="nav-link active text-primary d-flex collapse-size"
          aria-current="page"
          href="https://www.techelevator.com/?gclid=CjwKCAjwvMqDBhB8EiwA2iSmPGzzFasx6lXvj4qZY-ZzfVY_4nL0ogGcSvLNPRWZh6H_WYuw0coe5RoC1QEQAvD_BwE"
          target="_blank"
          >Careers</a
        >
      </li>
      <div class="right">
        <li>
          <router-link
            :to="{ name: 'login' }"
            class="nav-link active text-primary d-flex collapse-size"
            aria-current="page"
            href="#"
            v-if="this.$store.state.token == ''"
            >Login</router-link
          >

          <router-link
            :to="{ name: 'profile' }"
            class="nav-link active text-primary d-flex collapse-size "
            aria-current="page"
            href="#"
            v-else
            >My Profile</router-link
          >
        </li>
      </div>
    </div>
  </nav>
</template>

<script>
export default {
  methods: {},
};
</script>

<style scoped>
ul.dropdown-menu > li {
  display: inline-block;
  margin-left: auto;
  margin-right: auto;
  margin-top: 5px;
  width: 150px;
  justify-content: center;
  background: linear-gradient(
    30deg,
    rgba(0, 11, 24, 1) 0%,
    rgba(0, 23, 45, 1) 25%,
    rgba(0, 38, 77, 1) 100%
  );
}
ul.dropdown-menu {
  background: transparent !important;
}
li {
  -webkit-box-shadow: 0px 5px 19px 4px rgba(163, 73, 164, 0.75);
  box-shadow: 0px 5px 19px 4px rgba(163, 73, 164, 0.75);
  border: 4px double rgba(163, 73, 164, 0.75);
  margin: 1px;
  margin-left: 10px;
  text-align: center;
  list-style-type: none !important;
  background-color: #182628;
}

li:hover {
  border: 4px double lime;
  -webkit-box-shadow: 0px 5px 19px 3px lime;
  box-shadow: 0px 5px 19px 3px lime;
  background: black;
}

.homeButtonAlignment {
  margin-left: 220px;
}

.right {
  margin-right: 10px;
}
.right-align {
  position: relative;
}

.sizing {
  width: 150px;
  text-align: center;
}

.image-size {
  width: 175px;
  height: 175px;
  position: absolute;
  top: -28%;
}
@media (max-width: 1000px) {
  .collapse-size {
    text-align: left;
    padding: 0.2rem 0.2rem;
  }
  li {
    width: 200px;
  }
  .sizing {
    width: 200px;
  }
  ul ul {
    background: transparent !important;
  }
  ul ul li {
    margin: 0px !important;
    padding: 0px !important;
    box-shadow: none;
    border: none;
  }
  ul ul li:hover {
    margin: 0px !important;
    padding: 0px !important;
    box-shadow: none;
    border: none;
  }
}
.spinny:hover {
  transform: rotate(360deg);
  transition-duration: 750ms;
}
</style>
