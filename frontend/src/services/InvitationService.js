import axios from 'axios';

export default {
    createInvitation(invitation) {
        return axios.post("/teamnames/invite", invitation);
    },

    getPendingInvitations(userId) {
        return axios.get(`/users/${userId}/invitations`);
    },

    updateInvitation(invitation) {
        return axios.put(`/teamnames/${invitation.teamId}/updateInvite`, invitation);
    }
};