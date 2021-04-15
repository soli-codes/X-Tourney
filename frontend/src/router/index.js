import Vue from 'vue';
import Router from 'vue-router';
import Home from '../views/Home.vue';
import TheMainHelp from '../views/TheMainHelp.vue';
import Login from '../views/Login.vue';
import Logout from '../views/Logout.vue';
import Register from '../views/Register.vue';
import store from '../store/index';
import TournamentBrowse from '../views/TournamentBrowse.vue';
import TournamentCreate from '../views/TournamentCreate.vue';
import TeamsBrowse from '../views/TeamsBrowse.vue';
import TournamentDetails from '../views/TournamentDetails.vue';
import Profile from '../views/Profile.vue';
import MeetTheTeam from '../views/MeetTheTeam.vue';
import UpdateMatch from '../views/UpdateMatch.vue';
import TeamsCreate from '../views/TeamsCreate.vue';
import TeamDetails from '../views/TeamDetails.vue';

Vue.use(Router);

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    // HOMEPAGE
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true,
      },
    },
    // LOGIN
    {
      path: '/login',
      name: 'login',
      component: Login,
      meta: {
        requiresAuth: false,
      },
    },
    // LOGOUT
    {
      path: '/logout',
      name: 'logout',
      component: Logout,
      meta: {
        requiresAuth: false,
      },
    },
    // REGISTER
    {
      path: '/register',
      name: 'register',
      component: Register,
      meta: {
        requiresAuth: false,
      },
    },
    // HELP/FAQ
    {
      path: '/help',
      name: 'help',
      component: TheMainHelp,
      meta: {
        requiresAuth: false,
      },
    },
    // BROWSE ALL TOURNAMENTS
    {
      path: '/tournaments',
      name: 'tournaments',
      component: TournamentBrowse,
      meta: {
        requiresAuth: false,
      },
    },
    // CREATE A TOURNAMENT
    {
      path: '/create/tournament',
      name: 'createTournament',
      component: TournamentCreate,
      meta: {
        requiresAuth: true, // Change to true on prod
      },
    },
    // CREATE A TEAM
    {
      path: '/create/team',
      name: 'createTeam',
      component: TeamsCreate,
      meta: {
        requiresAuth: true, // Change to true on prod
      },
    },
    // BROWSE ALL TEAMS
    {
      path: '/teams',
      name: 'teams',
      component: TeamsBrowse,
      meta: {
        requiresAuth: false,
      },
    },
    // VIEW A SPECIFIC TOURNAMENT
    {
      path: '/tournaments/:tournamentId',
      name: 'tournamentDetails',
      component: TournamentDetails,
      meta: {
        requiresAuth: false,
      },
    },
    // VIEW YOUR PROFILE
    {
      path: '/profile',
      name: 'profile',
      component: Profile,
      meta: {
        requiresAuth: true,
      },
    },

    {
      path: '/meettheteam',
      name: 'meetTheTeam',
      component: MeetTheTeam,
      meta: {
        requiresAuth: false,
      },
    },

    {
      path: '/tournaments/:tournamentId/:matchId',
      name: 'updateMatch',
      component: UpdateMatch,
      meta: {
        requiresAuth: false,
      },
    },

    {
      path: '/teams/:teamId',
      name: 'teamDetails',
      component: TeamDetails,
      meta: {
        requiresAuth: false,
      },
    },
  ],
});

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some((x) => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next('/login');
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
