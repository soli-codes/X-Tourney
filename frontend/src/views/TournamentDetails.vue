<template>
  <div>
    <div>
      <div
        class="d-flex justify-content-between mx-2 mt-4 border-bottom border-danger pb-2"
      >
        <img :src="tournament.tournamentImage" class="image" />
        <h1>{{ tournament.name }}</h1>
        <button v-if="$store.state.token != ''">Sign Up</button>
        <router-link v-else to="login">Login to Sign Up</router-link>
        <!-- add condition for if currentUser Id is equal to tournament host Id -->
        <button
          id="generateBracket"
          v-on:click="generateBracket()"
        >
          Generate Bracket
        </button>
      </div>
      <div>
        <p>{{ tournament.description }}</p>
      </div>
      <!-- empty bracket if not generated yet, populated automatically updated bracket if it has been generated -->
      <generated-bracket />
      <!-- displays registered teams in a list -->
      <div v-for="(team, index) in teams" :key="index" :team="team" class="d-flex align-center">
        <h4> {{ index + 1 }}</h4>
        <img :src="team.teamImage" class="image" />
        <h4>{{ team.teamName }}</h4>
      </div>
    </div>
  </div>
</template>

<script>
import TournamentsService from '../services/TournamentsService';
import MatchServices from '../services/MatchServices';
import GeneratedBracket from '../components/GeneratedBracket.vue';

export default {
  components: { GeneratedBracket },
  data() {
    return {
      tournament: {},
      teams: [],
      seedCounter: 0,

      modalMatch: {

        matchId: '',
        tournamentId: '',
        teamOneId: '',
        teamTwoId: '',
        winningTeamId: '',
        losingTeamId: '',
        winningTeamScore: '',
        losingTeamScore: '',
        matchDate: '',
        matchTime: '',
        nextMatch: '',

      },
    };
  },

  created() {
    TournamentsService.getTournamentById(this.$route.params.tournamentId).then(
      (response) => {
        this.tournament = response.data;
      }
    );

    TournamentsService.getTournamentTeams(this.$route.params.tournamentId).then(
      (response) => {
        this.teams = response.data;
        this.teams.sort((a, b) => {
        if (a.wins / a.losses > b.wins / b.losses) {
          return 1;
        } else return -1;
      });
      }
    );
  },

  methods: {
    
   generateBracket() {
     if(this.teams != null && this.teams.length > 0) {
      let seedArray = this.generateSeedArray();
      
      MatchServices.postMatch(this.teams.length, this.$route.params.tournamentId, seedArray);

      }
    },
    // SORTS ALL TEAMS BY WIN / LOSS RATIO AND SAVES TO SEEDARRAY
    generateSeedArray() {
      let seedArray = this.teams;

      seedArray.sort((a, b) => {
        if (a.wins / a.losses > b.wins / b.losses) {
          return 1;
        } else return -1;
      });
      return seedArray;
    },

    getSeed() {
      this.seedCounter++;
      return this.seedCounter;
    },

    updateModalMatch(match) {
      this.modalMatch.matchId = match.id;
      this.modalMatch.tournamentId = match.tournamentId;
      this.modalMatch.teamOneId = match.teamOneId;
      this.modalMatch.teamTwoId = match.teamTwoId;
      this.modalMatch.winningTeamId = match.winningTeamId;
      this.modalMatch.losingTeamId = match.losingTeamId;
    },
  },

  computed: {
    // imagePath() {
    //   if(!this.tournament || !this.tournament.tournamentImage) return '';
    //   return require(`@/${this.tournament.tournamentImage}`);
    // },

  },
  
}
</script>

<style scoped>



.image {
  height: 150px;
  width: 150px;
}

</style>