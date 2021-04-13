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
          v-if="$store.state.matches.length == 0"
          v-on:click="generateBracket()"
        >
          Generate Bracket
        </button>
      </div>
      <div>
        <p>{{ tournament.description }}</p>
      </div>
      <!-- empty bracket if not generated yet, populated automatically updated bracket if it has been generated -->
      <generated-bracket
        v-if="$store.state.matches.length > 0 && $store.state.teams.length > 0"
      />
      <!-- LIST OF ALL TEAMS SIGNED UP BY SEED -->
      <div
        v-for="(team, index) in $store.state.teams"
        :key="index"
        :team="team"
        class="d-flex"
      >
        <h4>#{{ index + 1}}</h4>
        <img :src="team.teamImage" class="image" />
        <h4>{{ team.teamName }}</h4>
      </div>
      <!-- MODAL -->
      <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <!-- <match-details
        v-if="$store.state.matches.length > 0 && $store.state.teams.length > 0"
        :match="modalMatch" /> -->
      </div>
    </div>
  </div>
</template>

<script>
import TournamentsService from '../services/TournamentsService';
import MatchServices from '../services/MatchServices';
import GeneratedBracket from '../components/GeneratedBracket.vue';
// import MatchDetails from '../components/MatchDetails.vue';

export default {
  components: { GeneratedBracket,  },
  data() {
    return {
      tournament: {},
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
        this.$store.commit('SET_TEAMS', response.data);
      }
    );

    TournamentsService.getTournamentMatches(this.$route.params.tournamentId).then(
      (response) => {
        this.$store.commit('SET_MATCHES', response.data);
      }
    )

  },

  methods: {

    generateBracket() {
      let tournamentSize = this.tournament.maxTeamCount;
      console.log(tournamentSize);
      const axiosObject = {
        tournamentSize: tournamentSize,
        tournamentId: parseInt(this.$route.params.tournamentId),
        teams: this.generateSeedArray(),
      };
      console.log(axiosObject.teams);
      if (
        this.$store.state.teams != null &&
        this.$store.state.teams.length > 0
      ) {
        MatchServices.postMatch(axiosObject).then((response) => {
          if (response.status == 201) {
            console.log(response);
            window.location.reload();
          } else {
            console.log('error');
          }
        });
      }
    },
    // SORTS ALL TEAMS BY WIN / LOSS RATIO AND SAVES TO SEEDARRAY
    generateSeedArray() {
      let seedArray = this.$store.state.teams;
      let seedLength = seedArray.length;
      seedArray.sort((a, b) => {
        if (a.wins / a.losses > b.wins / b.losses) {
          return 1;
        } else return -1;
      });
      let seededArray = [];
      for (let i = 0; i < seedLength; i++) {
        seededArray.push(seedArray[i].teamId);
      }
      for (let i = seededArray.length; i < this.tournament.maxTeamCount; i++) {
        seededArray.push(1);
      }
      return seededArray;
    },

    // onClickChild (modalMatch) {
    //     this.modalMatch.matchId = modalMatch.matchId;
    //     this.modalMatch.tournamentId = modalMatch.tournamentId;
    //     this.modalMatch.teamOneId = modalMatch.teamOneId;
    //     this.modalMatch.teamTwoId = modalMatch.teamTwoId;
    //     this.modalMatch.winningTeamId = modalMatch.winningTeamId;
    //     this.modalMatch.losingTeamId = modalMatch.losingTeamId;
    //     this.modalMatch.winningTeamScore = modalMatch.winningTeamScore;
    //     this.modalMatch.losingTeamScore = modalMatch.losingTeamScore;
    //     this.modalMatch.matchDate = modalMatch.matchDate;
    //     this.modalMatch.matchTime = modalMatch.matchTime;
    //     this.modalMatch.nextMatch = modalMatch.nextMatch;
    // },
  },

    computed: {
      teams() {
        console.log('test');
        return this.$store.state.teams;
      },
      matches() {
        return this.$store.state.matches;
      },
    },

    // imagePath() {
    //   if(!this.tournament || !this.tournament.tournamentImage) return '';
    //   return require(`@/${this.tournament.tournamentImage}`);
    // },
}

  

</script>

<style scoped>



.image {
  height: 150px;
  width: 150px;
}

</style>
