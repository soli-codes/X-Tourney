<template>
  <div>
    <div>
        <div class="d-flex justify-content-between mx-2 mt-4 border-bottom border-danger pb-2">
            <img :src="imagePath" />
            <h1>{{ tournament.name }}</h1>
            <button v-if="$store.state.token != ''">Sign Up</button>
            <router-link v-else to="login">Login to Sign Up</router-link>
            <!-- add condition for if currentUser Id is equal to tournament host Id -->
            <button
              v-if="this.matches == null"
              id="generateBracket"
              v-on:click="generateBracket()">
                Generate Bracket
            </button>
        </div>
        <div>
            <p>{{ tournament.description }}</p>
        </div>
        <!-- empty bracket if not generated yet, populated automatically updated bracket if it has been generated -->
        <bracket v-if="this.matches == null"/>
        <generated-bracket v-else :matches="this.matches" :teams="this.teams" />
        <!-- displays registered teams in a list -->
        <div v-for="team in this.teams" :key="team.teamId" :team="team" class="d-flex">
            <img :src="team.teamImage" />
            <h4>{{ team.teamName }}</h4>
        </div>
    </div>
  </div>
</template>

<script>
import Bracket from '../components/Bracket.vue';
import TournamentsService from '../services/TournamentsService';
import MatchServices from '../services/MatchServices';
import GeneratedBracket from '../components/GeneratedBracket.vue';

export default {
  components: { Bracket, GeneratedBracket },
  data() {
    return {
      tournament: {},
      teams: [],
      matches: [],
    };
  },

  created() {
    TournamentsService.getTournamentById(this.$route.params.tournamentId).then(
      (response) => {
        this.tournament = response.data;
      }
    );

    // should work once getTournamentTeams end point is up and running
    TournamentsService.getTournamentTeams(this.$route.params.tournamentId).then(
      (response) => {
        this.teams = response.data;
      }
    );

  // will pull down matches sorted by matchId and place them in an arry to be displayed in bracket
    TournamentsService.getTournamentMatches(this.$route.params.tournamentId).then(
      (response) => {
        this.matches = response.data;
      }
    );

  },

  methods: {

   generateBracket() {
     if(this.teams != null && this.teams.length > 0) {
      let seedArray = this.generateSeedArray();

      let j = this.teams.length - 1;
      for(let i = 0; i <= j; i++) {
        let newMatch = {};

          newMatch.tournament_id = this.tournament.tournamentId;
          newMatch.team_1_id = seedArray[i.teamId];
          newMatch.team_2_id = seedArray[j.teamId];
          j--;

        MatchServices.postMatch(newMatch);

      }
     }
    },
    // SORTS ALL TEAMS BY WIN / LOSS RATIO AND SAVES TO SEEDARRAY
    generateSeedArray() {
      let seedArray = this.teams;
      
      seedArray.sort((a, b) => {
          if ((a.wins / a.losses) > (b.wins / b.losses)) {
              return 1;
          } else return -1;
      });
      return seedArray;
    }
  
  },

  computed: {
    imagePath() {
      if(!this.tournament || !this.tournament.tournamentImage) return '';
      return require(`@/${this.tournament.tournamentImage}`);
    }
  },

}
</script>

<style scoped></style>
