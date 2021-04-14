import axios from 'axios';

export default {

    // POST A TEAM TO A TOURNAMENT BY TOURNAMENT ID
    postTournamentTeam(teamToSignUp) {
        axios.post(`/tournament/${teamToSignUp.teamId}/teamnames/post`, teamToSignUp);
    },
    
}