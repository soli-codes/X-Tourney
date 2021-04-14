<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
      <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >
        Thank you for registering, please sign in.
      </div>
      <div class="d-flex flex-column align-items-center mb-4">
        <label for="username" class="sr-only">Username</label>
        <input
          type="text"
          id="username"
          class="form-control w-50"
          placeholder="Username"
          v-model="user.username"
          required
          autofocus
        />
        <label for="password" class="sr-only mt-2">Password</label>
        <input
          type="password"
          id="password"
          class="form-control w-50"
          placeholder="Password"
          v-model="user.password"
          required
        />
      </div>
      <div class="column">
        <div><button type="submit">Sign In</button></div>
        <div>
          <router-link :to="{ name: 'register' }" class="link-info"
            >Need an account?</router-link
          >
        </div>
      </div>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';
import TeamsService from '../services/TeamsService';
import TournamentsService from '../services/TournamentsService';

export default {
  name: 'login',
  components: {},
  data() {
    return {
      user: {
        username: '',
        password: '',
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit('SET_AUTH_TOKEN', response.data.token);
            this.$store.commit('SET_USER', response.data.user);
            TeamsService.getTeamsByUserId(this.$store.state.user.id).then( (response) => {
              this.$store.commit('SET_MY_TEAMS', response.data);
            });
            TournamentsService.getTournamentsByUser(this.$store.state.user.id).then( (response) => {
              this.$store.commit('SET_MY_TOURNAMENTS', response.data);
            });
            this.$router.push('/tournaments');
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>
