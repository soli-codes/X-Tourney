<template>
  <div class="p-1 text-white side-nav-color">
    <ul class="nav nav-pills flex-column padding">
      <li class="nav-item">
        <div class="shadow">
          <router-link :to="{ name: 'help' }" class="nav-link text-primary">
            FAQ/Help
          </router-link>
        </div>
      </li>
      <li>
        <router-link
          :to="{ name: 'tournaments' }"
          class="nav-link text-primary"
        >
          Browse Tournaments
        </router-link>
      </li>
      <li>
        <router-link :to="{ name: 'teams' }" class="nav-link text-primary">
          Browse Teams
        </router-link>
      </li>
      <li>
        <router-link
          :to="{ name: 'createTournament' }"
          class="nav-link text-primary"
        >
          Create Tournament
        </router-link>
      </li>
      <li>
        <router-link :to="{ name: 'createTeam' }" class="nav-link text-primary">
          Create Team
        </router-link>
      </li>
    </ul>
  </div>
</template>

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
  border: 4px double rgb(255, 251, 0);
  -webkit-box-shadow: 0px 5px 19px 3px rgb(255, 251, 0);
  box-shadow: 0px 5px 19px 3px rgb(255, 251, 0);
  margin-top: 30px;
  background: black;
}
.fixed {
  display: inline;
}

.padding {
  padding-top: 132px;
}
</style>
