<template>
  <div
    class="bg-primary justify-content-around row row-cols-2 row-cols-sm-2 row-cols-md-3 row-cols-lg-4"
  >
    <div v-for="tournament in tournaments" :key="tournament.tournamentId">
      <tournament-card :tournament="tournament" />
    </div>
  </div>
</template>

<script>
import TournamentCard from '../components/TournamentCard.vue';
import TournamentService from '../services/TournamentsService.js';

export default {
  components: { TournamentCard },

  data() {
    return {
      tournaments: [],
    };
  },

  created() {
    TournamentService.getTournaments().then((response) => {
      this.tournaments = response.data;
    });
  },
};
</script>

<style scoped>
.d-flex {
  height: 100vh;
  flex-wrap: wrap;
}
</style>
