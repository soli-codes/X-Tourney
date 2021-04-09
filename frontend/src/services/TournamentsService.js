import axios from 'axios';

export default {
  // return all tournaments
  getTournaments() {
    return axios.get('/tournaments');
  },

  // return tournament by id
  getTournamentById(tournamentId) {
    return axios.get(`/tournaments/${tournamentId}`)
  },

   // return teams by tournamentId
   getTournamentTeams(tournamentId) {
    return axios.get(`/teams/${tournamentId}`) // ask Korry for proper end point
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
