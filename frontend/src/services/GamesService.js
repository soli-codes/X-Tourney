import axios from 'axios';

export default {
  // return all games
  getGames() {
    return axios.get('/gametypes');
  },
};
