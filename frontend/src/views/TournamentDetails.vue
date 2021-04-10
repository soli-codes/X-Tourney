<template>
    <div>
        <div class="d-flex justify-content-between mx-2 mt-4 border-bottom border-danger pb-2">
            <img :src="tournament.tournamentImage" />
            <h1>{{ tournament.name }}</h1>
            <button v-if="$store.state.token != ''">Sign Up</button>
            <router-link v-else to="login">Login to Sign Up</router-link>
        </div>
        <div>
            <p>{{ tournament.description }}</p>
        </div>
        <!-- bracket should display here -->
        
        <!-- displays registered teams in a list -->
        <div v-for="team in teams" :key="team.teamId" :team="team" class="d-flex">
            <img :src="team.teamImage" />
            <h4>{{ team.name }}</h4>
        </div>
    </div>
</template>

<script>
import TournamentsService from '../services/TournamentsService';

export default {
    data() {
        return {
            tournament: {},
            teams: [],

        }
    },

    created() {
        
        TournamentsService.getTournamentById(this.$route.params.tournamentId).then( response => {
            this.tournament = response.data;
        });
        
        // should work once getTournamentTeams end point is up and running
        TournamentsService.getTournamentTeams(this.$route.params.tournamentId).then( response => {
            this.teams = response.data;
            
        });

        

    }
    
}
</script>

<style scoped>

</style>