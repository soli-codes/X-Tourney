<template>
  <div class="p-1 text-white side-nav-color">
    <ul class="nav nav-pills flex-column padding">
      <li class="nav-item bg-dark">
        <div class="shadow">
          <router-link :to="{ name: 'help' }" class="nav-link text-primary">
            FAQ/Help
          </router-link>
        </div>
      </li>
      <li class="bg-dark">
        <router-link
          :to="{ name: 'tournaments' }"
          class="nav-link text-primary"
        >
          Browse Tournaments
        </router-link>
      </li>
      <li class="bg-dark">
        <router-link :to="{ name: 'teams' }" class="nav-link text-primary">
          Browse Teams
        </router-link>
      </li>
      <li class="bg-dark">
        <router-link
          :to="{ name: 'createTournament' }"
          class="nav-link text-primary"
        >
          Create Tournament
        </router-link>
      </li>
      <li class="bg-dark">
        <router-link :to="{ name: 'createTeam' }" class="nav-link text-primary">
          Create Team
        </router-link>
      </li>
      <li class="bg-dark">
        <div class="nav-link text-primary" @click="noDisplay">
          Toggle Display
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  methods: {
    noDisplay() {
      document.getElementById('sidebar').classList.toggle('display');
      document.getElementById('sideArrow').classList.toggle('display');
      document.getElementById('main').classList.toggle('navbar-margin-left');
    },
  },
};
</script>

<style scoped>
li {
  -webkit-box-shadow: 0px 5px 19px 4px rgba(183, 29, 222, 0.75);
  box-shadow: 0px 5px 19px 4px rgba(183, 29, 222, 0.75);
  border: 4px double #b71dde;
  margin: 1px;
  margin-top: 30px;
  text-align: center;
  width: 180px;
  white-space: nowrap;
}

li:hover {
  border: 4px double lime;
  -webkit-box-shadow: 0px 5px 19px 3px lime;
  box-shadow: 0px 5px 19px 3px lime;
  margin-top: 30px;
  background: black;
  cursor: pointer;
}
.fixed {
  display: inline;
}

.padding {
  padding-top: 132px;
}
</style>
