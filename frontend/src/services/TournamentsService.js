import axios from 'axios';

export default {
  // return all tournaments
  getTournaments() {
    return axios.get('/tournaments');
  },

  // create tournament
  createTournament(tournament) {
    return axios.post('/tournaments', tournament);
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
