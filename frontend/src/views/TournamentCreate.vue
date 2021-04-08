<template>
  <form v-on:submit.prevent="createTournament">
    <div class="form-group">
      <select
        class="form-select"
        v-model="tournament.gameTypeId"
        aria-label="Select a game"
        id="gameOptions"
      >
        <option value="1">Select a game</option>
        <option value="2">Test</option>
        <option value="3">Test</option>
        <option value="4">Test</option>
        <option value="5">Test</option>
      </select>
    </div>
    <div class="form-group">
      <select
        class="form-select"
        v-model="tournament.eliminationType"
        aria-label="Select a game"
        id="eliminationType"
      >
        <option value="1">Select an elimination type</option>
        <option value="2">Test</option>
        <option value="3">Test</option>
        <option value="4">Test</option>
        <option value="5">Test</option>
      </select>
    </div>
    <div class="form-group">
      <label for="Name">Name</label>
      <input type="text" name="name" id="name" v-model="tournament.name" />
    </div>
    <div class="form-group">
      <label for="start-date">
        Start Date
      </label>
      <input
        type="date"
        name="start-date"
        id="start-date"
        v-model="tournament.startDate"
      />
      <input
        type="time"
        name="start-time"
        id="start-time"
        v-model="tournament.startTime"
      />
    </div>
    <div class="form-group">
      <label for="end-date">
        End Date
      </label>
      <input
        type="date"
        name="end-date"
        id="end-date"
        v-model="tournament.endDate"
      />
    </div>
    <div class="form-group">
      <label for="signup-open">
        Signup Date
      </label>
      <input
        type="date"
        name="signup-open"
        id="signup-open"
        v-model="tournament.signupOpen"
      />
    </div>
    <div class="form-group">
      <label for="signup-close">
        Close Signup
      </label>
      <input
        type="date"
        name="signup-close"
        id="signup-close"
        v-model="tournament.signupClose"
      />
    </div>

    <div class="form-group">
      <label for="Description">Description</label>
      <textarea
        name="description"
        id="description"
        cols="30"
        rows="10"
        v-model="tournament.description"
      ></textarea>
    </div>
    <div class="form-group">
      <label for="Open registration">Open registration</label>
      <input
        type="checkbox"
        name="Open registration"
        id="openRegistration"
        v-model="tournament.open"
      />
    </div>
    <div class="form-group">
      <label for="Open tournament">Open Tournament</label>
      <input
        type="checkbox"
        name="Open Tournament"
        id="open-tournament"
        v-model="tournament.open"
      />
    </div>
    <div class="form-group">
      <label for="Max Teams">Max Teams</label>
      <input
        type="number"
        name="max-teams"
        id="max-teams"
        v-model="tournament.maxTeams"
      />
    </div>
    <button type="submit">Submit</button>
  </form>
</template>

<script>
import TournamentService from '../services/TournamentsService.js';
import GamesService from '../services/GamesService.js';
export default {
  data() {
    return {
      tournament: {
        name: '',
        description: '',
        open: '',
        maxTeams: '',
        startDate: '',
        startTime: '',
        endDate: '',
        signupOpen: '',
        signupClose: '',
        gameTypeId: '',
        eliminationType: '',
        private: '',
        tournamentID: null,
      },
      gameTypes: [],
    };
  },
  methods: {
    createTournament() {
      const newTournament = {
        name: this.tournament.name,
        description: this.tournament.description,
        open: this.tournament.open,
        private: this.tournament.private,
        maxTeams: this.tournament.maxTeams,
        startDate: this.tournament.startDate,
        startTime: this.tournament.startTime,
        endDate: this.tournament.endDate,
        signUpOpen: this.tournament.signupOpen,
        signUpClose: this.tournament.signupClose,
        gameTypeId: this.tournament.gameTypeId,
        eliminationType: this.tournament.eliminationType,
      };
      console.log(newTournament);
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
  created() {
    GamesService.getGames.then((response) => {
      this.gameTypes = response.data;
    });
  },
  mounted() {
    this.gameTypes.forEach((game) => {
      document
        .getElementById('gameOptions')
        .insertAdjacentHTML(
          'beforeend',
          `<option value="${game.game_type_id}">${game.game_type}</option>`
        );
    });
  },
};
</script>
