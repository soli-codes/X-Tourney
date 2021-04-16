<template>
  <div>
    <div
      id="content"
      class="d-flex flex-row flex-wrap justify-content-center  "
    >
      <img :src="team.teamImage" />
      <div id="details">
        <h2>Team Name: {{ team.teamName }}</h2>
        <h5>Wins: {{ team.wins }}</h5>
        <h5>Losses: {{ team.losses }}</h5>
        <h5>
          Match Win Loss Percentage:
          {{
            team.win == 0 && team.losses == 0
              ? 0
              : (team.wins / team.losses).toFixed(2)
          }}
        </h5>
        <h5>Tournaments Entered: {{ team.tournamentsEntered }}</h5>
        <h5>Tournaments Won: {{ team.tournamentWins }}</h5>

        <div v-if="isMyTeam">
          <label for="teamImageURL"
            >Update Your Team Image URL:
            <input type="text" v-model="teamImageURL" />
            <button @click="saveNewImage">Save</button>
          </label>
        </div>
      </div>
    </div>
    <div class="d-flex justify-contents-center mt-3">
      <button class="button bg-primary" @click="joinTeam()">Join Team</button>
    </div>
  </div>
</template>

<script>
import TeamsService from '../services/TeamsService.js';

export default {
  data() {
    return {
      team: {},
      teamImageURL: '',
    };
  },
  created() {
    console.log('created');
    TeamsService.getTeamById(this.$route.params.teamId).then((response) => {
      this.team = response.data;
    });
  },

  computed: {
    isMyTeam() {
      let isMyTeam = this.$store.state.myTeams.filter((myTeam) => {
        return myTeam.teamId == this.team.teamId;
      });

      if (isMyTeam.length > 0) {
        return true;
      } else return false;
    },
  },
  methods: {
    saveNewImage() {
      this.team.teamImage = this.teamImageURL;

      TeamsService.updateTeam(this.team).then((response) => {
        if (response.status == 200) {
          this.$store.commit('SET_TEAM_IMAGE', this.team);
          this.$router.push({ name: 'profile' });
        }
      });
    },

    joinTeam() {
      TeamsService.postUserToTeam(
        this.team.teamId,
        this.$store.state.user.id
      ).then((response) => {
        console.log(response.data);
        if (response.data == false) {
          window.alert("Congrats, You're On The Team!");
        } else if (response.data == true) {
          window.alert("You're already on this team!");
        } else {
          window.alert('Uh oh, something went wrong.');
        }
      });
    },
  },
};
</script>

<style scoped>
img {
  margin-top: 35px;
  width: 300px;
  height: 300px;
  border-radius: 10px;
  border-color: #ff455d;
  border-style: solid;
  margin-right: 40px;
}

img {
  margin-top: 35px;
  width: 300px;
  height: 300px;
  border-radius: 10px;
  border-color: #ff455d;
  border-style: solid;
  margin-right: 40px;
}

#content {
  align-items: center;
  justify-items: center;
}

.button {
  margin-left: auto;
  margin-right: auto;
}
</style>
