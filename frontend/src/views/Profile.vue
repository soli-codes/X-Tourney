<template>
  <div>
    <profile-teams />
    <profile-tournaments />
    <invitation />
    <div>
      <label for="imageURL"
        >Profile Image URL:
        <input type="text" v-model="imageURL" />
        <button @click="saveNewImage">Save</button>
      </label>
    </div>
    <div><button @click="logout">Logout</button></div>
  </div>
</template>

<script>
import UserService from '../services/UserService.js';

export default {
  data() {
    return {
      imageURL: '',
      user: {
        id: '',
        userImage: '',
      },
    };
  },
  methods: {
    logout() {
      this.$store.commit('LOGOUT');
      this.$router.push('/help');
    },
    saveNewImage() {
      this.user.id = this.$store.state.user.id;
      this.user.userImage = this.imageURL;

      UserService.updateUser(this.user).then((response) => {
        if (response.status == 200) {
          this.$router.push({ name: 'home' });
        }
      });
    },
  },
};
</script>
