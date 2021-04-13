import axios from 'axios';

export default {

    updateUser(user) {
        return axios.put('/users/update', user);
    }
}