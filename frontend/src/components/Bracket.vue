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
        id="match-divs"
        v-for="match in matchCounter()"
        :key="match"
        style="border-color: #FFFFFF; background-color: #000000; width: 200px;"
      >
        <div id="matches">
          <div class="team1 p-0 m-0" style="background-color: #333333">
            TEST TEAM ONE
          </div>
          <div class="team2 p-0 m-0">TEST TEAM TWO</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      rounds: 0,
      tempCounter: 0,
      teams: [1, 2, 3, 4, 5, 6, 7, 8],
    };
  },
  created() {
    if (this.teams.length <= 64) {
      this.rounds = 6;
    }
    if (this.teams.length <= 32) {
      this.rounds = 5;
    }
    if (this.teams.length <= 16) {
      this.rounds = 4;
    }
    if (this.teams.length <= 8) {
      this.rounds = 3;
    }
    if (this.teams.length <= 4) {
      this.rounds = 2;
    }
    this.tempCounter = this.rounds;
  },

  methods: {
    matchCounter() {
      this.tempCounter--;
      return Math.pow(2, this.tempCounter);
    },
  },

  // computed: {
  //   teams() {
  //     return this.$store.state.teams;
  //   },
  // },
};
</script>
