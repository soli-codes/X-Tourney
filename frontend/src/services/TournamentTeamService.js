import axios from 'axios';

export default {

    // POST A TEAM TO A TOURNAMENT BY TOURNAMENT ID
    postTournamentTeam(tournamentId, teamToSignUp) {
        return axios.post(`/tournaments/${tournamentId}/create`, teamToSignUp);
    },

    getInvitableTeams(tournamentId) {
        return axios.get(`/tournaments/${tournamentId}/invitableteams`);
    },
    
}