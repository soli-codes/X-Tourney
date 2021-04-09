import axios from 'axios';

export default {

    // return all teams
    getTeams() {
        return axios.get('/teams');
    },

   

    // create team
    createTeam(team) {
        return axios.post('/teams', team);
    },

    // update team
    updateTeam(team) {
        return axios.put(`/teams/${team.team_id}`, team);
    },

    // delete team
    deleteTeam(teamID) {
        return axios.delete(`/teams/${teamID}`);
    },
}