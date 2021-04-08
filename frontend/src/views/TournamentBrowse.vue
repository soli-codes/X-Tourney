<template>
    <div>
        <div class="d-flex flex-start bg-dark">
            <input type="text" v-model="filter.name" placeholder="Filter by Name" />
            <input type="text" v-model="filter.startDate" placeholder="Start Date" />
            <input type="text" v-model="filter.description" placeholder="Filter by Description" />
            <div class="dropdown">
                <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                    Sort By
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                    <li><a class="dropdown-item" href="#">Wins</a></li>
                    <li><a class="dropdown-item" href="#">Losses</a></li>
                    <li><a class="dropdown-item" href="#">W/L Ratio</a></li>
                </ul>
            </div>
        </div>
        <div class="bg-primary d-flex justify-content-around">
            <div v-for="tournament in filteredList" :key="tournament.tournamentId">
                <tournament-card :tournament="tournament" />
            </div>
        </div>
    </div>
</template>

<script>
import TournamentCard from '../components/TournamentCard.vue'
import TournamentService from '../services/TournamentsService.js'

export default {
  components: { TournamentCard },

  data() {
      return {
          tournaments: [],
          filter: {
              name: "",
              startDate: "",
              description: "",
          }
      };
  },

  created() {
      TournamentService.getTournaments().then(response => {
        this.tournaments = response.data});
  },

  computed: {
      filteredList() {
          let filteredTournaments = this.tournaments;
          if (this.filter.name != "") {
              filteredTournaments = filteredTournaments.filter( (tournament) => {
                  return tournament.name.toLowerCase().includes(this.filter.name.toLowerCase());
                 
              });
          }

          if (this.filter.startDate != "") {
              filteredTournaments = filteredTournaments.filter( (tournament) => {
                  return tournament.startDate.toLowerCase().includes(this.filter.startDate.toLowerCase());
              });
          }

          if (this.filter.description != "") {
              filteredTournaments = filteredTournaments.filter( (tournament) => {
                  return tournament.description.toLowerCase().includes(this.filter.description.toLowerCase());
              });
          }
           console.log(filteredTournaments.length);
            return filteredTournaments;
      }
  }
    
}
</script>

<style scoped>

.justify-content-around {
    height: 100vh;
    flex-wrap: wrap;
}



</style>