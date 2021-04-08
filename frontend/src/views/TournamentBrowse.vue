<template>
  <div class="bg-secondary">
    <div class="d-flex flex-start bg-secondary">
      <input type="text" v-model="filter.name" placeholder="Filter by Name" />
      <input type="text" v-model="filter.startDate" placeholder="Start Date" />
      <input
        type="text"
        v-model="filter.description"
        placeholder="Filter by Description"
      />
      <p class="ms-1">Sort By:</p>
      <select class="form-select ms-1" v-model="sortBy">
        <option>Open Enrollment</option>
        <option>Private Enrollment</option>
        <option>Start Date</option>
        <option>Sign Up By</option>
        <option>Max Teams Asc</option>
        <option>Max Teams Desc</option>
      </select>
    </div>
    <div
      class="justify-content-around row row-cols-2 row-cols-sm-2 row-cols-md-3 row-cols-lg-4"
    >
      <div v-for="tournament in filteredList" :key="tournament.tournamentId">
        <tournament-card :tournament="tournament" />
      </div>
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
      filter: {
        name: '',
        startDate: '',
        description: '',
      },
      sortBy: '',
    };
  },

  created() {
    TournamentService.getTournaments().then((response) => {
      this.tournaments = response.data;
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
          return tournament.startDate
            .toLowerCase()
            .includes(this.filter.startDate.toLowerCase());
        });
      }

      if (this.filter.description != '') {
        filteredTournaments = filteredTournaments.filter((tournament) => {
          return tournament.description
            .toLowerCase()
            .includes(this.filter.description.toLowerCase());
        });
      }

      if (this.sortBy == 'Open Enrollment') {
        filteredTournaments = filteredTournaments.filter((tournament) => {
          return tournament.open;
        });
      }
      console.log(filteredTournaments.length);
      return filteredTournaments;
    },
  },
};
</script>

<style scoped>
.justify-content-around {
  height: 100vh;
  flex-wrap: wrap;
}

.form-select {
  width: 10%;
}
</style>
