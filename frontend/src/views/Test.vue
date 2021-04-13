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
        @click="checkLength(round, match)"
      >
        {{ match.id }},{{ match.team1 }},{{ match.team2 }}
      </div>
    </div>
  </div>
</template>

<script>
import MatchServices from '../services/MatchServices';
export default {
  data() {
    return {
      array: [
        [
          { id: 1, team1: 'cat', team2: 'dog' },
          { id: 2, team1: 'ferret', team2: 'garfield' },
          { id: 3, team1: 'cat', team2: 'dog' },
          { id: 4, team1: 'ferret', team2: 'garfield' },
        ],
        [
          { id: 5, team1: 'cat', team2: 'dog' },
          { id: 6, team1: 'ferret', team2: 'garfield' },
        ],
        [{ id: 7, team1: 'cat', team2: 'ferret' }],
      ],
    };
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
