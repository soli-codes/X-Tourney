<template>
    <div>
        <img :src="team.teamImage" class="mx-auto d-block"/>
        <h2>Team Name: {{ team.teamName }}</h2>
        <h5>Wins: {{ team.wins }}</h5>
        <h5>Losses: {{ team.losses }}</h5>
        <h5>
            Match Win Loss Percentage: 
            {{ team.win == 0 && team.losses == 0 ? 0 : (team.wins / team.losses).toFixed(2) }}
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
</template>

<script>
import TeamService from '../services/TeamsService.js';

export default {
    data() {
        return {
            team: {},
            teamImageURL: '',
        };
    },
    created() {
        TeamService.getTeamById(this.$route.params.teamId).then(response => {
            this.team = response.data;            
        });
    },
    computed: {
        isMyTeam() {
            
            let isMyTeam = this.$store.state.myTeams.filter(myTeam => {
                return myTeam.teamId == this.team.teamId;
            });

            if (isMyTeam.length > 0) {
                return true;
            } else return false;
        }
    },
    methods: {
    
    saveNewImage() {
      this.team.teamImage = this.teamImageURL;

      TeamService.updateTeam(this.team).then((response) => {
        if (response.status == 200) {
            this.$store.commit("SET_TEAM_IMAGE", this.team);
          this.$router.push({ name: 'profile' });
        }
      });
    },
    },
}
</script>

<style scoped>

</style>