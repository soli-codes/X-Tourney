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

    // RETURN TEAMS BY USER

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
}