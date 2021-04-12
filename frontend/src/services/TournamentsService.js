import axios from 'axios';

export default {
  // return all tournaments
  getTournaments() {
    return axios.get('/tournaments');
  },

  // return tournament by id
  getTournamentById(tournamentId) {
    return axios.get(`/tournaments/${tournamentId}`);
  },

  getTournamentsByUser(userId) {
    return axios.get(`/tournaments/user/${userId}`)
  },

  getTournamentByHost(hostId) {
    return axios.get(`/tournaments/host/${hostId}`)
  },

   // return teams by tournamentId
   getTournamentTeams(tournamentId) {
    return axios.get(`/tournaments/${tournamentId}/teams`);
  },

  // RETURN MATCHES BY TOURNAMENT ID
  getTournamentMatches(tournamentId) {
    return axios.get(`/tournaments/${tournamentId}/matches`);
  },

  // create tournament
  createTournament(tournament) {
    return axios.post('/tournaments/create', tournament);
  },

  // update tournament
  updateTournament(tournament) {
    return axios.put(`/tournaments/${tournament.tournament_id}`, tournament);
  },

  // delete tournament
  deleteTournament(tournamentID) {
    return axios.delete(`/tournaments/${tournamentID}`);
  },
};
