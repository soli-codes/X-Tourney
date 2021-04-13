<template>
  <div class="d-flex flex-row">
    <div
      v-for="round in tournamentArray"
      :key="round"
      class="flex-column p-1 m-2 d-flex justify-content-around"
    >
      <div
        v-for="match in round"
        :key="match.matchId"
        class="flex-column p-0 m-1 d-flex"
      >
        {{ match.teamOneId }},{{ match.teamTwoId }}
      </div>
    </div>
  </div>
</template>

<script>
import TournamentsService from '../services/TournamentsService';
export default {
  data() {
    return {
      tournamentArray: [[]],
    };
  },
  created() {
    
    TournamentsService.getTournamentMatches(this.$route.params.tournamentId).then( (response) => {
      this.tournamentArray = response.data;
    });

  },
  methods: {

    getTeamName(id) {
      let targetTeam = this.teams.find((team) => {
        return team.teamId == id;
      });
      return targetTeam.teamName;
    },
  },
  mounted() {
    console.log(this.teams);
  },
};
</script>
