<template>
  <div class="p-1 text-white side-nav-color">
    <ul class="nav nav-pills flex-column padding">
      <li class="nav-item">
        <router-link :to="{ name: 'help' }" class="nav-link text-primary">
          FAQ/Help
        </router-link>
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
        <router-link :to="{ name: 'createTournament' }" class="nav-link text-primary">
          Create Tournament
        </router-link>
      </li>
      <li>
        <router-link :to="{ name: 'createTeam' }" class="nav-link text-primary">
          Create Team
        </router-link>
      </li>
      <li>
        <a href="#" class="text-primary nav-link">
          Toggle Display
        </a>
      </li>
    </ul>
  </div>
</template>

<style scoped>
.fixed {
  display: inline;
}

.padding {
  padding-top: 112px;
}
</style>
