import axios from 'axios';

export default {
  // return all teams
  getTeams() {
    return axios.get('/teamnames');
  },

  // RETURN TEAM BY ID
  getTeamById(id) {
    return axios.get(`/teamnames/${id}`);
  },

  // create team
  createTeam(team) {
    return axios.post('/teamnames/create', team);
  },

  // update team
  updateTeam(team) {
    return axios.put(`/teamnames/${team.teamId}/update`, team);
  },

  // delete team
  deleteTeam(teamID) {
    return axios.delete(`/teamnames/${teamID}`);
  },

  // RETURN ALL TEAMS WITH THE GIVEN USER ID
  getTeamsByUserId(userId) {
    return axios.get(`/teamnames/user/${userId}`);
  },

  // ADD USER TO A TEAM
  postUserToTeam(thisTeamId, thisUserId) {
    let teamNameUser = {
      teamId: thisTeamId,
      userId: thisUserId,
    };
    console.log(teamNameUser);
    return axios.post('/teamnameusers/create', teamNameUser);
  }
};
