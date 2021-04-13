import axios from 'axios';

export default {
  getMatches() {
    return axios.get('/matches');
  },

  getMatchById(id) {
    return axios.get(`/matches/${id}`);
  },

  postMatch(axiosObject) {
    return axios.post('/matches/create', axiosObject);
  },

  updateMatch(match) {
    return axios.put('/matches/update', match);
  },

  // DELETE MATCH END POINT EXISTS BUT NOT IMPLEMENTED BECAUSE IT'S NOT NEEDED
};
