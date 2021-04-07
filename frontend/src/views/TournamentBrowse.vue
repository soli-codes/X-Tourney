<template>
    <div>
        <div v-for="tournament in tournaments" :key="tournament.tournamentId">
            <tournament-card :tournament="tournament" />
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
      };
  },

  created() {
      TournamentService.getTournaments().then(response => {
        this.tournaments = response.data});
  }
    
}
</script>

<style scoped>

</style>