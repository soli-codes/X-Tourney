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
              $store.state.teams.length < tournament.maxTeamCount &&
              tournament.hasStarted == false
          "
        >
          <button v-on:click="signUpTeam()" class="bg-primary">
            Sign Up Your Team:
          </button>
          <select style="display: block" v-model="teamToSignUp">
            <option
              v-for="team in $store.state.myTeams"
              :value="team"
              :key="team.teamId"
              >{{ team.teamName }}</option
            >
          </select>

          <button v-on:click="inviteTeam" class="bg-primary">
            Invite Teams:
          </button>
          <select style="display: block" v-model="teamToInvite">
            <option
              v-for="team in invitableTeams"
              :value="team"
              :key="team.teamId"
              >{{ team.teamName }}</option
            >
          </select>
        </div>
        <div
          v-else-if="$store.state.token != '' && tournament.hasStarted == true"
        >
          <h4 class="text-danger">This Tournament has Started!</h4>
        </div>
        <div
          v-else-if="
            $store.state.token != '' &&
              $store.state.teams.length == tournament.maxTeamCount
          "
        >
          <h4 class="text-danger">This Tournament is Full!</h4>
        </div>
        <router-link v-else :to="{ name: 'login' }"
          >Login to Sign Up</router-link
        >
        <!-- add condition for if currentUser Id is equal to tournament host Id -->
        <button
          v-if="
            $store.state.matches.length == 0 &&
              tournament.hostId == $store.state.user.id
          "
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
          :tournament="tournament"
          v-if="
            $store.state.matches.length > 0 && $store.state.teams.length > 0
          "
        />
      </div>
      <!-- LIST OF ALL TEAMS SIGNED UP BY SEED -->
      <div
        class="d-flex flex-column align-items-center"
        v-if="$store.state.teams.length > 0"
      >
        <router-link
          v-for="(team, index) in $store.state.teams"
          :key="index"
          :team="team"
          :to="{ name: 'teamDetails', params: { teamId: team.teamId } }"
          class="d-flex justify-content-center row team-border m-3 w-75"
        >
          <div class="d-flex flex-row align-items-center">
            <h4 class="text-danger col-1">#{{ index + 1 }}</h4>
            <img class="team-image col-2" :src="team.teamImage" />
            <h5 class="text-end col-9">
              {{ team.teamName }} W/L:
              {{ (team.wins / team.losses).toFixed(2) }}
            </h5>
          </div>
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
import InvitationService from '../services/InvitationService.js';

export default {
  components: { GeneratedBracket },
  data() {
    return {
      tournament: {},
      teamToSignUp: {},
      teamToInvite: {},
      invitableTeams: [],
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
        this.$store.commit('SET_TEAMS', this.sortWinLoss(response.data));
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

    TournamentTeamService.getInvitableTeams(
      this.$route.params.tournamentId
    ).then((response) => {
      this.invitableTeams = response.data;
    });
  },

  methods: {
    teamsCheck() {
      let teamIds = [];
      this.$store.state.teams.forEach((e) => teamIds.push(e.teamId));
      console.log(teamIds);
      this.invitableTeams = this.$store.state.allTeams.filter(
        (e) => !teamIds.includes(e.teamId)
      );
      return this.invitableTeams;
    },
    generateBracket() {
      let tournamentSize = this.tournament.maxTeamCount;
      let tournamentTeams = this.generateSeedArray();
      const axiosObject = {
        tournamentSize: tournamentSize,
        tournamentId: parseInt(this.$route.params.tournamentId),
        teams: tournamentTeams,
      };
      if (
        this.$store.state.teams != null &&
        this.$store.state.teams.length > 0
      ) {
        console.log(axiosObject.teams);
        MatchServices.postMatch(axiosObject).then((response) => {
          if (response.status == 201) {
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

    inviteTeam() {
      let invite = {
        teamId: this.teamToInvite.teamId,
        tournamentId: this.$route.params.tournamentId,
        inviteStatus: 'pending',
      };
      InvitationService.createInvitation(invite).then((response) => {
        if (response.status == 201) {
          alert(`Invite sent!`);
          window.location.reload();
        }
      });
    },
    // SORTS ALL TEAMS BY WIN / LOSS RATIO AND SAVES TO SEEDARRAY
    generateSeedArray() {
      let seedArray = this.$store.state.teams;
      let seedLength = seedArray.length;
      seedArray.sort((a, b) => {
        if (isNaN(a.wins / a.losses) && isNaN(b.wins / b.losses)) {
          if (a.losses == 0 && b.losses == 0) {
            return b.wins - a.wins;
          }
          return 0;
        }
        if (isNaN(a.wins / a.losses)) {
          return 1;
        }
        if (isNaN(b.wins / b.losses)) {
          return -1;
        }
        return b.wins / b.losses - a.wins / a.losses;
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

    sortWinLoss(arr) {
      arr.sort((a, b) => {
        if (isNaN(a.wins / a.losses) && isNaN(b.wins / b.losses)) {
          if (a.losses == 0 && b.losses == 0) {
            return b.wins - a.wins;
          }
          return 0;
        }
        if (isNaN(a.wins / a.losses)) {
          return 1;
        }
        if (isNaN(b.wins / b.losses)) {
          return -1;
        }
        return b.wins / b.losses - a.wins / a.losses;
      });
      return arr;
    },

    getTeamName(teamId) {
      if (teamId == 1) {
        return 'TO BE DECIDED';
      }
      let team = this.$store.state.teams.find((team) => {
        return team.teamId == teamId;
      });
      return team.teamName;
    },
  },
};
</script>

<style scoped>
.tournament-image {
  height: 150px;
  width: 150px;
  border-style: solid;
  border-color: #ff455d;
  border-radius: 10px;
}

.team-image {
  height: 75px;
  width: 75px;
  border-style: solid;
  border-color: #ff455d;
  border-radius: 5px;
}

.team-border {
  border-radius: 25px !important;
  box-shadow: 0px 0px 5px 5px #ff455d;
}

a {
  text-decoration: none;
}
</style>
