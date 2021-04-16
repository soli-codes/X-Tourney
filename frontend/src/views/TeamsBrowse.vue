<template>
  <div class="bg-dark">
    <div class="d-flex flex-start bg-dark">
      <input
        type="text"
        v-model="filter.teamName"
        placeholder="Filter by Name"
      />
      <input type="text" v-model="filter.wins" placeholder="Minimum Wins" />
      <input type="text" v-model="filter.losses" placeholder="Max" />
      <p class="ms-1 text-center">Sort By:</p>
      <select class="form-select ms-1" v-model="filter.sortBy">
        <option>None</option>
        <option>Wins</option>
        <option>Losses</option>
        <option>W / L Ratio</option>
      </select>
    </div>
    <div class="d-flex justify-content-around">
      <div v-for="team in filteredList" :key="team.teamId">
        <router-link
          :to="{
            name: 'teamDetails',
            params: { teamId: team.teamId },
          }"
          :team="team"
        >
          <team-card :team="team" />
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
import TeamCard from '../components/TeamCard.vue';
import TeamsService from '../services/TeamsService.js';

export default {
  components: { TeamCard },

  data() {
    return {
      teams: [],

      filter: {
        teamName: '',
        wins: '',
        losses: '',
        sortBy: '',
      },
    };
  },

  created() {
    TeamsService.getTeams().then((response) => {
      this.teams = response.data;
      this.teams.splice(0, 1);
    });
  },

  computed: {
    filteredList() {
      let filteredTeams = this.teams;

      if (this.filter.teamName != '') {
        filteredTeams = filteredTeams.filter((team) => {
          return team.teamName
            .toLowerCase()
            .includes(this.filter.teamName.toLowerCase());
        });
      }

      if (this.filter.wins != '') {
        filteredTeams = filteredTeams.filter((team) => {
          return team.wins >= this.filter.wins;
        });
      }

      if (this.filter.losses != '') {
        filteredTeams = filteredTeams.filter((team) => {
          return team.losses <= this.filter.losses;
        });
      }

      if (this.filter.sortBy == 'Wins') {
        filteredTeams.sort((a, b) => {
          if (a.wins > b.wins) {
            return 1;
          } else return -1;
        });
      }

      if (this.filter.sortBy == 'Losses') {
        filteredTeams.sort((a, b) => {
          if (a.losses > b.losses) {
            return 1;
          } else return -1;
        });
      }

      if (this.filter.sortBy == 'W / L Ratio') {
        filteredTeams.sort((a, b) => {
          if (a.wins / a.losses > b.wins / b.losses) {
            return 1;
          } else return -1;
        });
      }

      return filteredTeams;
    },
  },
};
</script>

<style scoped>
a {
  text-decoration: none;
}
.justify-content-around {
  height: 100vh;
  flex-wrap: wrap;
}

.form-select {
  width: 15%;
}
</style>
