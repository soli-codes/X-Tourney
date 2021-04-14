import axios from 'axios';

export default {

    // POST A TEAM TO A TOURNAMENT BY TOURNAMENT ID
    postTournamentTeam(tournamentId, teamToSignUp) {
        axios.post(`/tournament/${tournamentId}/teamnames/post`, teamToSignUp);
    },
    
}