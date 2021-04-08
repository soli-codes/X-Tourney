<template>
    <div>
        <div class="d-flex flex-start">
            <input type="text" v-model="filter.name" placeholder="Filter by Name" />
            <input type="text" v-model="filter.startDate" placeholder="Start Date" />
            <input type="text" v-model="filter.description" placeholder="Filter by Description" />
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
                  tournament.name.toLowerCase().includes(this.filter.name.toLowerCase());
                 
              });
          }

        //   if (this.filter.startDate != "") {
        //       filteredTournaments = filteredTournaments.filter( (tournament) => {
        //           tournament.startDate.toLowerCase().includes(this.filter.startDate.toLowerCase());
        //       });
        //   }

        //   if (this.filter.description != "") {
        //       filteredTournaments = filteredTournaments.filter( (tournament) => {
        //           tournament.description.toLowerCase().includes(this.filter.description.toLowerCase());
        //       });
        //   }
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