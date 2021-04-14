<template>
  <div>
    <div>
      User: {{ this.$store.state.user.username }}
      <img :src="this.$store.state.user.userImage" />
    </div>
    <div class="title">
      <h3>MY TEAMS</h3>
    </div>

    <div
      class="d-flex justify-content-around"
      v-if="this.$store.state.myTeams.length > 0"
    >
      <div v-for="team in this.$store.state.myTeams" :key="team.teamId">
        <router-link
          :to="{
            name: 'teamDetails',
            params: { teamId: team.teamId },
          }"
        >
          <team-card :team="team" />
        </router-link>
      </div>
    </div>

    <div class="title">
      <h3>MY TOURNAMENTS</h3>
    </div>

    <div class="d-flex justify-content-around">
      <div
        v-for="tournament in this.$store.state.myTournaments"
        :key="tournament.tournamentId"
      >
        <router-link
          :to="{
            name: 'tournamentDetails',
            params: { tournamentId: tournament.tournamentId },
          }"
        >
          <tournament-card :tournament="tournament" />
        </router-link>
      </div>
    </div>

    <div class="title">
      <h3>PENDING INVITATIONS</h3>
    </div>

    <div
      v-if="this.myInvitations.length != 0"
      class="d-flex justify-content-around"
    >
      <div v-for="(invite, index) in this.myInvitations" :key="index">
        <router-link :to="{ name: 'invitation' }">
          <invitation-card :invite="invite" />
        </router-link>
      </div>
    </div>

    <div>
      <label for="imageURL"
        >Update Your Profile Image URL:
        <input type="text" v-model="imageURL" />
        <button @click="saveNewImage">Save</button>
      </label>
    </div>
    <div><button @click="logout">Logout</button></div>
    <div
      class="modal fade"
      id="exampleModal"
      tabindex="-1"
      aria-labelledby="exampleModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog">
        <div class="modal-content bg-primary text-dark">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">
              Match #{{ modalMatch.matchId }}
            </h5>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body">
            <div>
              <label>Select Match Winner:</label>
              <select v-model="modalMatch.winningTeamId">
                <option :value="modalMatch.teamOneId">{{
                  modalMatch.teamOneName
                }}</option>
                <option :value="modalMatch.teamTwoId">{{
                  modalMatch.teamTwoName
                }}</option>
              </select>
            </div>
            <div>
              <label>Select Match Loser:</label>
              <select v-model="modalMatch.losingTeamId">
                <option :value="modalMatch.teamOneId">{{
                  modalMatch.teamOneName
                }}</option>
                <option :value="modalMatch.teamTwoId">{{
                  modalMatch.teamTwoName
                }}</option>
              </select>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-dark" data-bs-dismiss="modal">
              Close
            </button>
            <button
              v-on:click="putMatch(modalMatch)"
              type="button"
              class="btn btn-danger"
            >
              Save Updates
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import TeamCard from '../components/TeamCard.vue';
import TournamentCard from '../components/TournamentCard.vue';
import UserService from '../services/UserService.js';
import InvitationService from '../services/InvitationService.js';
import InvitationCard from '../components/InvitationCard.vue';
import TeamsService from '../services/TeamsService';
import TournamentsService from '../services/TournamentsService';

export default {
  components: {
    TournamentCard,
    TeamCard,
    InvitationCard,
  },
  data() {
    return {
      imageURL: '',
      user: {
        id: '',
        userImage: '',
      },
      myInvitations: [],
    };
  },

  created() {
    console.log(this.$store.state.myTeams);
    // add in call to invitation service/controller to get all invitations
    InvitationService.getPendingInvitations(this.$store.state.user.id).then(
      (response) => {
        this.myInvitations = response.data;
      }
    );
    TeamsService.getTeamsByUserId(this.$store.state.user.id).then(
      (response) => {
        this.$store.commit('SET_MY_TEAMS', response.data);
      }
    );
    TournamentsService.getTournamentsByUser(this.$store.state.user.id).then(
      (response) => {
        this.$store.commit('SET_MY_TOURNAMENTS', response.data);
      }
    );
  },
  methods: {
    logout() {
      this.$store.commit('LOGOUT');
      this.$router.push('/help');
    },
    saveNewImage() {
      this.user.id = this.$store.state.user.id;
      this.user.userImage = this.imageURL;

      UserService.updateUser(this.user).then((response) => {
        if (response.status == 200) {
          this.$router.push({ name: 'home' });
        }
      });
    },
  },
};
</script>

<style scoped>
div.title {
  width: 300px;
  margin: auto;
}

h3 {
  text-align: center;
}

a {
  text-decoration: none;
}
</style>
