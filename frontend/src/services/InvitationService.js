import axios from 'axios';

export default {
    createInvitation(invitation) {
        return axios.post("/teamnames/invite", invitation);
    },

    getPendingInvitations(teamId) {
        return axios.get(`/teamnames/${teamId}/invitations`);
    },

    updateInvitation(invitation) {
        return axios.put(`/teamnames/${invitation.teamId}/updateInvite`, invitation);
    }
};