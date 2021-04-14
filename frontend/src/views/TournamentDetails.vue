<template>
  <div>
    <div>
      <div
        class="d-flex justify-content-between align-items-center mx-2 mt-4 border-bottom border-danger pb-2"
      >
        <img :src="tournament.tournamentImage" class="tournament-image" />
        <h1>{{ tournament.name }}</h1>
        <div
          v-if="
            $store.state.token != '' &&
              $store.state.teams.length < tournament.maxTeamCount
          "
        >
          <button v-on:click="signUpTeam()" class="bg-primary">
            Sign Up Your Team:
          </button>
          <select style="display: block" v-model="teamToSignUp">
            <option
              v-for="team in $store.state.myTeams"
              :value="team"
              :key="team"
              >{{ team.teamName }}</option
            >
          </select>
        </div>
        <div v-else-if="$store.state.token != ''">
          <h4 class="text-danger">This Tournament is Full!</h4>
        </div>
        <router-link v-else :to="{ name: 'login' }"
          >Login to Sign Up</router-link
        >
        <!-- add condition for if currentUser Id is equal to tournament host Id -->
        <button
          v-if="$store.state.matches.length == 0 && tournament.hostId == $store.state.user.id"
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
      <div class="d-flex">
        <generated-bracket
          v-if="
            $store.state.matches.length > 0 && $store.state.teams.length > 0
          "
        />
      </div>
      <!-- LIST OF ALL TEAMS SIGNED UP BY SEED -->
      <div class="d-flex flex-column align-items-center">
      <router-link
        v-for="(team, index) in sortWinLoss()"
        :key="index"
        :team="team"
        :to="{ name: 'teamDetails', params: { teamId: team.teamId }}"
        class="d-flex justify-content-around align-items-center team-border m-3 w-75">
          <div class="d-flex flex-row align-items-center">
            <h4 class="text-danger">#{{ index + 1 }}</h4>
            <img class="team-image" :src="team.teamImage" />
          </div>
          <h5>
            {{ team.teamName }} W/L: {{ (team.wins / team.losses).toFixed(2) }}
          </h5>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
import TournamentsService from '../services/TournamentsService';
import MatchServices from '../services/MatchServices';
import GeneratedBracket from '../components/GeneratedBracket.vue';
import TournamentTeamService from '../services/TournamentTeamService';
import TeamsService from '../services/TeamsService';

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
    TeamsService.getTeamsByUserId(this.$store.state.user.id).then(
      (response) => {
        this.$store.commit('SET_MY_TEAMS', response.data);
      }
    );
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
      console.log(this.tournament.tournamentId);
      console.log(this.teamToSignUp);
      TournamentTeamService.postTournamentTeam(
        this.tournament.tournamentId,
        this.teamToSignUp
      ).then((response) => {
        if (response.status == 201) {
          window.location.reload();
        } else {
          console.log('error');
        }
      });
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

    sortWinLoss() {
      let sortedArray = this.$store.state.teams;
      sortedArray.sort((a, b) => {
        if (a.wins / a.losses < b.wins / b.losses) {
          return 1;
        } else return -1;
      });
      return sortedArray;
    },
  },
};
</script>

<style scoped>
.tournament-image {
  height: 150px;
  width: 150px;
}

.team-image {
  height: 75px;
  width: 75px;
}

.team-border {
  border-radius: 25px !important;
  box-shadow: 0px 0px 5px 5px #ff455d;
}

a {
  text-decoration: none;
}
</style>
