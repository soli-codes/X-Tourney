<template>
  <form v-on:submit.prevent="createTournament">
    <div class="form-group">
      <select
        class="form-select"
        v-model="tournament.gameTypeId"
        aria-label="Select a game"
        id="gameOptions"
      >
        <option value="0">Select a game</option>
        <option
          v-for="game in gameTypes"
          :key="game.gameTypeId"
          :value="game.gameTypeId"
        >
          {{ game.gameType }}
        </option>
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
        <option value="2">Single Elimination</option>
        <option value="3">Double Elimination</option>
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
        v-model="tournament.private"
      />
    </div>
    <div class="form-group">
      <label for="Open tournament">Open Tournament</label>
      <input
        type="checkbox"
        name="Open Tournament"
        id="openTournament"
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
        eliminationType: this.tournament.eliminationType,
        startDate: this.tournament.startDate,
        endDate: this.tournament.endDate,
        signUpOpen: this.tournament.signupOpen,
        signUpClose: this.tournament.signupClose,
        name: this.tournament.name,
        description: this.tournament.description,
        gameTypeId: this.tournament.gameTypeId,
        maxTeamCount: this.tournament.maxTeams,
        hostId: 4,
        startTime: this.tournament.startTime,
        open: this.tournament.open,
        private: this.tournament.private,
      };
      if (newTournament.tournamentImage == null) {
        newTournament.tournamentImage = '..src/assets/X-Tourney_Logo.png';
      }
      console.log(newTournament);
      TournamentService.createTournament(newTournament)
        .then((response) => {
          if (response.status === 201) {
            console.log('wow we did it');
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  created() {
    GamesService.getGames().then((response) => {
      this.gameTypes = response.data;
    });
  },
  mounted() {
    this.gameTypes.forEach((game) => {
      document
        .getElementById('gameOptions')
        .insertAdjacentHTML(
          'beforeend',
          `<option value="${game.gameTypeId}">${game.gameType}</option>`
        );
    });
  },
};
</script>
