import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';

Vue.use(Vuex);

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token');
const currentUser = JSON.parse(localStorage.getItem('user'));

if (currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    teams: [],
    matches: [],
    myTeams: [],
    myTournaments: [],
    allTeams: [],
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user', JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },

    SET_TEAMS(state, teams) {
      state.teams = teams;
    },

    SET_ALL_TEAMS(state, teams) {
      state.allTeams = teams;
    },

    SET_MATCHES(state, matches) {
      state.matches = matches;
    },

    SET_USER_IMAGE(state, imageURL) {
      state.user.userImage = imageURL;
    },

    SET_TEAM_IMAGE(state, team) {
      state.myTeams.forEach((myTeam) => {
        if (myTeam.teamId == team.teamId) {
          myTeam.teamImage = team.teamImage;
        }
      });
    },

    SET_MY_TEAMS(state, teams) {
      state.myTeams = teams;
    },

    SET_MY_TOURNAMENTS(state, tournaments) {
      state.myTournaments = tournaments;
    },
    CLEAR_STORE(state) {
      state.teams = [];
      state.matches = [];
    },
  },
});
