import axios from 'axios';

export default {
    createInvitation(invitation) {
        return axios.post("/teamnames/tournament-invite", invitation);
    },

    getPendingInvitations(userId) {
        return axios.get(`/users/${userId}/tournament-invitations`);
    },

    updateInvitation(invitation) {
        return axios.put(`/teamnames/${invitation.teamId}/update/tournament-invite`, invitation);
    }
};