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
        style="width: 200px; background-color: #333333"
        @click="checkLength(round, match)"
      >
        {{ match.teamOneId }},{{ match.teamTwoId }}
      </div>
    </div>
  </div>
</template>

<script>
import MatchServices from '../services/MatchServices';
import TournamentsService from '../services/TournamentsService';
export default {
  data() {
    return {
      array: [],
      teams: [],
    };
  },
  created() {
    TournamentsService.getTournamentMatches(3).then((response) => {
      this.array = response.data;
    });
    TournamentsService.getTournamentTeams(3).then((response) => {
      this.teams = response.data;
    });
  },
  methods: {
    checkLength(round, match) {
      console.log(round);
      console.log(match.id);
    },
    updateBracket() {
      const match1 = {
        winning_team_id: 1,
        losing_team_id: 2,
        match_id: 1,
        tournament_id: 5,
      };

      MatchServices.updateMatch(match1)
        .then((response) => {
          if (response.status === 201) {
            console.log('wow we did it');
          }
        })
        .catch(() => {
          console.log('we are sad');
        });
    },
  },
};

// UPDATE matches SET winning_team_id = ?, losing_team_id = ? WHERE tournament_id = ? AND match_id = ?
// UPDATE matches SET team_1_id, WHERE tournament_id = ? AND match_id = ?
</script>
