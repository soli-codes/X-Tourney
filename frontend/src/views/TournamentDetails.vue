<template>
  <div>
    <div>
      <div
        class="d-flex justify-content-between mx-2 mt-4 border-bottom border-danger pb-2"
      >
        <img :src="tournament.tournamentImage" class="image" />
        <h1>{{ tournament.name }}</h1>
        <div v-if="$store.state.token != ''">
          <button class="bg-primary">Sign Up Your Team</button>
          <select v-model="teamToSignUp">
            <option
            v-for="team in $store.state.myTeams"
            :value="team"
            :key="team"
            v-on:click="signUpTeam()"
            >{{ team.teamName }}</option>
          </select>
        </div>
        <router-link v-else to="login">Login to Sign Up</router-link>
        <!-- add condition for if currentUser Id is equal to tournament host Id -->
        <button
          v-if="$store.state.matches.length == 0"
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
        <h4>#{{ index + 1 }}</h4>
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
import TournamentTeamService from '../services/TournamentTeamService';

export default {
  components: { GeneratedBracket },
  data() {
    return {
      tournament: {},
      teamToSignUp: {},
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

    TournamentsService.getTournamentMatches(
      this.$route.params.tournamentId
    ).then((response) => {
      this.$store.commit('SET_MATCHES', response.data);
    });
  },

  methods: {
    generateBracket() {
      let tournamentSize = this.tournament.maxTeamCount;
      const axiosObject = {
        tournamentSize: tournamentSize,
        tournamentId: parseInt(this.$route.params.tournamentId),
        teams: this.generateSeedArray(),
      };
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

    signUpTeam() {
      TournamentTeamService.postTournamentTeam(this.teamToSignUp);
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
  },


};
</script>

<style scoped>
.image {
  height: 150px;
  width: 150px;
}
</style>
