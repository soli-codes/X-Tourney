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
          v-if="this.matches.length == 0"
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
      <generated-bracket v-if="this.matches.length > 0" />
      <!-- displays registered teams in a list -->
      <div v-for="team in teams" :key="team.teamId" :team="team" class="d-flex">
        <!-- <img :src="team.teamImage" /> -->
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
    TournamentsService.getTournamentMatches(
      this.$route.params.tournamentId
    ).then((response) => {
      this.matches = response.data;
    });
  },

  methods: {
    checkTeams() {
      console.log(this.teams);
    },
    checkMatches() {
      console.log(this.matches);
    },

    generateBracket() {
      let tournamentSize = this.tournament.maxTeamCount;
      const axiosObject = {
        tournamentSize: tournamentSize,
        tournamentId: parseInt(this.$route.params.tournamentId),
        teams: this.generateSeedArray(),
      };
      console.log(axiosObject.teams);
      // fetch('http://localhost:8080/matches/create', {
      //   method: 'POST',
      //   headers: {
      //     'Content-Type': 'application/json',
      //   },
      //   body: axiosObject,
      // })
      //   .then((response) => response.json())
      //   .then((data) => console.log('Success:', data))
      //   .catch((error) => {
      //     console.log('error:', error);
      //   });
      if (this.teams != null && this.teams.length > 0) {
        MatchServices.postMatch(axiosObject).then((response) => {
          if (response.status == 201) {
            console.log(response);
            window.location.reload();
          } else {
            console.log(response.error);
          }
        });
      }
    },
    // SORTS ALL TEAMS BY WIN / LOSS RATIO AND SAVES TO SEEDARRAY
    generateSeedArray() {
      let seedArray = this.teams;
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
      return seededArray;
    },

    computed: {
      // imagePath() {
      //   if(!this.tournament || !this.tournament.tournamentImage) return '';
      //   return require(`@/${this.tournament.tournamentImage}`);
      // },
      teams() {
        return this.$store.state.teams;
      },
      matches() {
        return this.$store.state.matches;
      },
    },
  },
};
</script>

<style scoped>
.image {
  width: 150px;
  height: 150px;
}
</style>
