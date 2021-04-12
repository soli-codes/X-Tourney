<template>
  <div class="d-flex flex-row" id="mainBracket">
    <div
      class="flex-column p-1 m-2 d-flex justify-content-around"
      id="rounds"
      v-for="round in rounds"
      :key="round"
    >
      <div
        class="flex-column p-0 m-1 d-flex"
        id="match divs"
        v-for="match in matchCounter()"
        :key="match"
        style="border-color: #FFFFFF; background-color: #000000; width: 200px;"
      >
      <!-- DISPLAYS KNOWN MATCH TEAMS IF IN THE FIRST COLUMN -->
      <div v-if="round == 1" :match="countMatches()">
        <div class="team1 p-0 m-0" style="background-color: #333333">{{ match.teamOneId }}</div>
        <div class="team2 p-0 m-0">{{ match.teamTwoId }}</div>
      </div>
      <!-- BINDS TEAMS BASED ON WINNERS OF PREVIOUS MATCHES -->
      <div v-else>
          <div :match="countMatchWinner()" class="team1 p-0 m-0" style="background-color: #333333">{{ getTeamName(match.winningTeamId) }}</div>
          <div :match="countMatchWinner()" class="team2 p-0 m-0">{{ getTeamName(match.winningTeamId) }}</div>
      </div>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  data() {
    return {
      props: [ 'matches', 'teams' ],
      rounds: "",
      tempCounter: 0,
      tempMatchCounter: -1,
      tempMatchWinner: -1,
    };
  },
  created() {

    if (this.teams.length <= 64) {
      this.rounds = 7;
    }
    if (this.teams.length <= 32) {
      this.rounds = 6;
    }
    if (this.teams.length <= 16) {
      this.rounds = 5;
    }
    if (this.teams.length <= 8) {
      this.rounds = 4;
    }
    if (this.teams.length <= 4) {
      this.rounds = 3;
    }
    this.tempCounter = this.rounds;
  },

  methods: {
    matchCounter() {
      this.tempCounter--;
      return Math.pow(2, this.tempCounter);
    },

    countMatches() {
        this.tempMatchCounter++;
        return this.matches[this.tempMatchCounter];
    },

    countMatchWinner() {
        this.tempMatchWinner++;
        return this.matches[this.tempMatchWinner]
    },

    getTeamName(teamId) {
        let targetTeam = this.teams.find(team => {
            return team.teamId = teamId;
        })
        return targetTeam.teamName;
    },

  },
};
</script>
