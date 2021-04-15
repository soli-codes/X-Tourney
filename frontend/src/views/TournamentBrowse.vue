<template>
  <div>
    <div class="d-flex flex-start">
      <input type="text" v-model="filter.name" placeholder="Filter by Name" />
      <input type="date" v-model="filter.startDate" placeholder="Start Date" />
      <input
        type="text"
        v-model="filter.description"
        placeholder="Filter by Description"
      />
      <p class="ms-1 text-center">Sort By:</p>
      <select class="form-select ms-1" v-model="sortBy">
        <option>Display All</option>
        <option>Accepting Enrollment</option>
        <option>Public Sign-Up</option>
        <option>Invitation Only</option>
        <option>Start Date</option>
        <option>Sign Up Deadline</option>
        <option>Max Teams Low to High</option>
        <option>Max Teams High to Low</option>
      </select>
      <p class="ms-1 text-center">Game Type:</p>
      <select class="form-select ms-1" v-model="filter.gameType">
        <option value="">Display All</option>
        <option
          v-for="gameType in gameTypes"
          :value="gameType.gameTypeId"
          :key="gameType.gameTypeId"
        >
          {{ gameType.gameType }}
        </option>
      </select>
    </div>
    <div class="d-flex justify-content-around">
      <div
        v-for="tournament in filteredList"
        :key="tournament.tournamentId"
        @click="clearStore"
      >
        <router-link
          :to="{
            name: 'tournamentDetails',
            params: { tournamentId: tournament.tournamentId },
          }"
        >
          <tournament-card :tournament="tournament" />
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
import TournamentCard from '../components/TournamentCard.vue';
import TournamentService from '../services/TournamentsService.js';
import GamesService from '../services/GamesService.js';

export default {
  components: { TournamentCard },

  data() {
    return {
      tournaments: [],
      gameTypes: [],
      filter: {
        name: '',
        startDate: '',
        description: '',
        gameType: '',
      },
      sortBy: '',
    };
  },

  methods: {
    clearStore(state) {
      this.$store.commit('CLEAR_STORE', state);
    },
  },

  created() {
    TournamentService.getTournaments().then((response) => {
      this.tournaments = response.data;
    });

    GamesService.getGames().then((response) => {
      this.gameTypes = response.data;
    });
  },

  computed: {
    filteredList() {
      let filteredTournaments = this.tournaments;

      if (this.filter.name != '') {
        filteredTournaments = filteredTournaments.filter((tournament) => {
          return tournament.name
            .toLowerCase()
            .includes(this.filter.name.toLowerCase());
        });
      }

      if (this.filter.startDate != '') {
        filteredTournaments = filteredTournaments.filter((tournament) => {
          return (
            Date.parse(tournament.startDate) > Date.parse(this.filter.startDate)
          );
        });
      }

      if (this.filter.description != '') {
        filteredTournaments = filteredTournaments.filter((tournament) => {
          return tournament.description
            .toLowerCase()
            .includes(this.filter.description.toLowerCase());
        });
      }

      if (this.sortBy == 'Accepting Enrollment') {
        filteredTournaments = filteredTournaments.filter((tournament) => {
          return tournament.open;
        });
      }

      if (this.sortBy == 'Public Sign-Up') {
        filteredTournaments = filteredTournaments.filter((tournament) => {
          return !tournament.private;
        });
      }

      if (this.sortBy == 'Invitation Only') {
        filteredTournaments = filteredTournaments.filter((tournament) => {
          return tournament.private;
        });
      }

      if (this.sortBy == 'Start Date') {
        filteredTournaments.sort((a, b) => {
          if (a.startDate > b.startDate) {
            return 1;
          }
          if (a.startDate < b.startDate) {
            return -1;
          }
          return 0;
        });
      }

      if (this.sortBy == 'Sign Up Deadline') {
        filteredTournaments.sort((a, b) => {
          if (a.signUpClose > b.signUpClose) {
            return 1;
          } else return -1;
        });
      }

      if (this.sortBy == 'Max Teams Low to High') {
        filteredTournaments.sort((a, b) => {
          if (a.maxTeamCount > b.maxTeamCount) {
            return 1;
          } else return -1;
        });
      }

      if (this.sortBy == 'Max Teams High to Low') {
        filteredTournaments.sort((a, b) => {
          if (a.maxTeamCount < b.maxTeamCount) {
            return 1;
          } else return -1;
        });
      }

      if (this.filter.gameType != '') {
        filteredTournaments = filteredTournaments.filter((tournament) => {
          return tournament.gameTypeId == this.filter.gameType;
        });
      }
      return filteredTournaments;
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
