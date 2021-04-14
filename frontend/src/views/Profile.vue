<template>
  <div>
    <div>
        User: {{this.$store.state.user.username}}
        <img :src="this.$store.state.user.userImage" />
    </div>
    <div class="title">
      <h3>MY TEAMS</h3>
    </div>

    <div class="d-flex justify-content-around">
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
      <div v-for="tournament in this.$store.state.myTournaments" :key="tournament.tournamentId">
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

    <div v-if="this.myInvitations.length != 0" class="d-flex justify-content-around">
      <div v-for="(invite, index) in this.myInvitations" :key="index">
        <router-link :to="{ name: 'invitation' }">
          <invitation-card :invite="invite" />
        </router-link>
      </div>
    </div>

    <div>
      <label for="imageURL"
        >Profile Image URL:
        <input type="text" v-model="imageURL" />
        <button @click="saveNewImage">Save</button>
      </label>
    </div>
    <div><button @click="logout">Logout</button></div>
  </div>
</template>

<script>
import TeamCard from '../components/TeamCard.vue';
import TournamentCard from '../components/TournamentCard.vue';
import UserService from '../services/UserService.js';
import InvitationService from '../services/InvitationService.js';
import InvitationCard from '../components/InvitationCard.vue';

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
        // add in call to invitation service/controller to get all invitations
        InvitationService.getPendingInvitations(this.$store.state.user.id).then(response => {
            this.myInvitations = response.data;
        });
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
