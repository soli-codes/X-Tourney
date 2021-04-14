<template>
    <div class="card m-3 border border-5 border-danger bg-dark">
    <img src="../assets/invite_icon.png" class="card-img-top" />
    <div class="card-body">
      <p class="card-title text-center">Tournament Name:</p>
      <p class="card-text text-center text-primary">{{ tournament.name }}</p>
      <p class="card-title text-center">Team Invited:</p>
      <p class="card-text text-center text-primary">{{ team.teamName }}</p>
    </div>
  </div>
</template>

<script>
import TournamentService from '../services/TournamentsService.js';
import TeamService from '../services/TeamsService.js';

export default {
    name: 'invitation',
    props: ['invite'],
    data() {
        return {
            team: {},
            tournament: {},
        };
    },

    created() {
        TournamentService.getTournamentById(this.invite.tournamentId).then(response => {
            this.tournament = response.data;
        });

        TeamService.getTeamById(this.invite.teamId).then(response => {
            this.team = response.data;
        });
    },
}
</script>

<style scoped>
.card-img-top {
  height: 200px;
  width: 200px;
  border-top-left-radius: 20px;
  border-top-right-radius: 20px;
}

.card {
  width: 210px;
  }

.card-title {
  color: #00fff2;
  padding-bottom: 0;
  margin-bottom: 0;
}

.card-text {
  padding-bottom: 0;
  margin-bottom: 0;
}

.border {
  border-radius: 25px !important;
  /* box-shadow: 0px 0px 5px 2px #ff455d; */
}

.border:hover {
  box-shadow: 0px 0px 5px 2px #ff455d;
  animation: flicker 1.5s infinite alternate;
}

@keyframes flicker {
  0%,
  19%,
  21%,
  23%,
  25%,
  54%,
  56%,
  100% {
    box-shadow: 0 0 0.5rem #fff, inset 0 0 0.5rem #fff, 0 0 2rem var(#ff455d),
      inset 0 0 2rem var(#ff455d), 0 0 4rem var(#ff455d),
      inset 0 0 4rem var(#ff455d);
  }

  20%,
  24%,
  55% {
    box-shadow: none;
  }
}
</style>