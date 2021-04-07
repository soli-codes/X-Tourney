<template>
  <form v-on:submit.prevent="createTournament">
    <div class="form-group">
      <label for="Name">Name</label>
      <input type="text" name="name" id="name" v-model="tournament.title" />
    </div>
    <div class="form-group"></div>
    <div class="form-group">
      <label for="Description">Description</label>
      <textarea
        name="description"
        id="description"
        cols="30"
        rows="10"
      ></textarea>
    </div>
  </form>
</template>

<script>
import TournamentService from '../services/TournamentsService.js';
export default {
  data() {
    return {
      tournament: {
        name: '',
        description: '',
        open: '',
        maxTeams: '',
        startDate: '',
        endDate: '',
        signupOpen: '',
        signupClose: '',
        gameType: '',
        tournamentID: null,
      },
    };
  },
  methods: {
    createTournament() {
      const newTournament = {
        title: this.tournament.title,
      };
      TournamentService.createTournament(newTournament)
        .then((response) => {
          if (response.status === 201) {
            this.$router.push(`/tournaments/${this.tournament.tournamentID}`);
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>
