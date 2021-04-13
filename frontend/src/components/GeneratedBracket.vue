<template>
  <div class="d-flex flex-row">
    <div
      v-for="round in array"
      :key="round"
      class="flex-column p-1 m-2 d-flex justify-content-around"
    >
      <div
        v-for="match in round"
        :key="match"
        class="flex-column p-0 m-1 d-flex"
      >
        <div style="width: 200px; background-color: #333333">
          {{ match.teamOneId }}
        </div>
        <div style="width: 200px; background-color: #333333">
          {{ match.teamTwoId }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import TournamentsService from '../services/TournamentsService';
export default {
  data() {
    return {
      array: [],
      teams: [],
    };
  },
  created() {
    TournamentsService.getTournamentMatches(
      this.$route.params.tournamentId
    ).then((response) => (this.array = response.data));
  },
};
</script>
