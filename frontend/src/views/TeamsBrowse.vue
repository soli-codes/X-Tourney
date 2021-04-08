<template>
    <div class="bg-primary">
        <div class="d-flex flex-start bg-dark">
            <input type="text" v-model="filter.name" placeholder="Filter by Name" />
            <input type="text" v-model="filter.wins" placeholder="Wins >= Than" />
            <input type="text" v-model="filter.losses" placeholder="Losses <= Than" />
            <select class="form-select ms-1" v-model="sortBy">
                <option>Wins</option>
                <option>Losses</option>
                <option>W / L Ratio</option>
            </select>
        </div>
        <div class="d-flex justify-content-around">
            <div v-for="team in filteredTeams" :key="team.teamId">
                <team-card :team="team" />
            </div>
        </div>
    </div>
</template>

<script>
import TeamCard from '../components/TeamCard.vue'
import TeamsService from '../services/TeamsService.js'

export default {
  components: { TeamCard },

  data() {
      return {
          teams: [],

          filter: {
              name: "",
              wins: "",
              losses: "",
          }
      };
  },

  created() {
      TeamsService.getTeams().then(response => {
        this.teams = response.data});
  },

  computed: {

      filteredList() {
          let filteredTeams = this.teams;
          if (this.filter.name != "") {
              filteredTeams = filteredTeams.filter( (team) => {
                  team.name.toLowerCase()
                  .includes(this.filter.name.toLowerCase());
              });
          }

          if (this.filter.wins != "") {
              filteredTeams = filteredTeams.filter( (team) => {
                  team.wins >= this.filter.wins;
              });
          }

          if (this.filter.losses != "") {
              filteredTeams = filteredTeams.filter( (team) => {
                  team.losses <= this.filter.losses;
              });
          }
          return filteredTeams;
      }

  }
    
}
</script>

<style scoped>

.d-flex {
    height: 100vh;
    flex-wrap: wrap;
}

</style>