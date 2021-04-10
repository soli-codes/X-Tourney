import axios from 'axios';

export default {
  getMatches(id) {
    return axios.get(`/tournaments/${id}/matches`);
  },

  postMatch(match) {
    return axios.post(`/tournaments/${id}/matches/post`, match);
  },

  updateMatch(match) {
    return axios.put(`tournaments/${id}/matches/put`, match);
  },
};
