<template>
  <div class="d-flex flex-row">
    <div
      v-for="(round, index) in $store.state.matches"
      :key="index"
      class="flex-column py-1 px-0 my-2 mx-0 d-flex justify-content-around"
    >
      <div
        v-for="(match, matchIndex) in round"
        :key="matchIndex"
        class="flex-column p-0 m-1 d-flex"
        data-bs-toggle="modal"
        data-bs-target="#exampleModal"
        v-on:click="onClick(match)"
      >
        <div class="d-flex flex-row">
          <div v-if="index != 0">
            <div
              :class="{
                'border-bottom-lit':
                  match.teamOneId != 1 || match.teamTwoId != 1,
              }"
              class="empty-spacer border-bottom border-4"
            />
            <div
              :class="{
                'empty-spacer-bottom-lit':
                  match.teamOneId != 1 || match.teamTwoId != 1,
              }"
              class="empty-spacer empty-spacer-bottom"
            />
          </div>
          <div>
            <div
              class="competitor-one-div border border-bottom-0 border-3 text-primary d-flex justify-contents-center"
              :class="{
                'border-lit':
                  match.winningTeamId != '' && match.winningTeamId != null,
              }"
            >
              {{ getTeamName(match.teamOneId) }}
            </div>
            <div
              class="competitor-two-div border border-top-0 border-3 text-primary d-flex justify-contents-center"
              :class="{
                'border-lit':
                  match.winningTeamId != '' && match.winningTeamId != null,
              }"
            >
              {{ getTeamName(match.teamTwoId) }}
            </div>
          </div>
          <div>
            <div
              :class="{
                'border-end': match.matchId % 2 == 0,
                'border-bottom-lit':
                  match.winningTeamId != '' && match.winningTeamId != null,
                'override-border': index == $store.state.matches.length - 1,
              }"
              class="empty-spacer border-bottom border-4"
            />
            <div
              :class="{
                'border-end': match.matchId % 2 == 1,
                'empty-spacer-bottom-lit':
                  match.winningTeamId != '' && match.winningTeamId != null,
                'override-border': index == $store.state.matches.length - 1,
              }"
              class="empty-spacer empty-spacer-bottom border-4"
            />
          </div>
        </div>
      </div>
    </div>
    <!-- MODAL -->

    <div
      v-if="tournament.hostId == $store.state.user.id"
      class="modal fade"
      id="exampleModal"
      tabindex="-1"
      aria-labelledby="exampleModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog">
        <div class="modal-content bg-primary text-dark">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">
              Match #{{ modalMatch.matchId }}
            </h5>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body">
            <div>
              <label>Select Match Winner:</label>
              <select v-model="modalMatch.winningTeamId">
                <option :value="modalMatch.teamOneId" @click="buttonEnable">{{
                  modalMatch.teamOneName
                }}</option>
                <option :value="modalMatch.teamTwoId" @click="buttonEnable">{{
                  modalMatch.teamTwoName
                }}</option>
              </select>
            </div>
            <div>
              <label>Select Match Loser:</label>
              <select v-model="modalMatch.losingTeamId">
                <option :value="modalMatch.teamOneId">{{
                  modalMatch.teamOneName
                }}</option>
                <option :value="modalMatch.teamTwoId">{{
                  modalMatch.teamTwoName
                }}</option>
              </select>
            </div>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-dark"
              data-bs-dismiss="modal"
              @click="buttonDisable"
            >
              Close
            </button>
            <button
              v-on:click="putMatch(modalMatch)"
              type="button"
              class="btn btn-danger"
              :disabled="isDisabled"
            >
              Save Updates
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import TournamentsService from '../services/TournamentsService';
import MatchServices from '../services/MatchServices';

export default {
  props: ['tournament'],
  data() {
    return {
      array: [],
      isDisabled: true,
      modalMatch: {
        matchId: '',
        tournamentId: '',
        teamOneId: '',
        teamTwoId: '',
        teamOneName: '',
        teamTwoName: '',
        winningTeamId: '',
        losingTeamId: '',
        winningTeamScore: '',
        losingTeamScore: '',
        matchDate: '',
        matchTime: '',
        nextMatch: '',
      },
    };
  },
  methods: {
    buttonEnable() {
      return (this.isDisabled = false);
    },
    buttonDisable() {
      return (this.isDisabled = true);
    },
    getTeamName(teamId) {
      if (teamId == 1) {
        return 'TO BE DECIDED';
      }
      let team = this.$store.state.teams.find((team) => {
        return team.teamId == teamId;
      });
      return team.teamName;
    },

    onClick(match) {
      console.log(this.$store.state.matches[0].length);
      this.modalMatch = match;
      this.modalMatch.teamOneName = this.getTeamName(match.teamOneId);
      this.modalMatch.teamTwoName = this.getTeamName(match.teamTwoId);
      if (
        match.matchId ==
        this.$store.state.matches[this.$store.state.matches.length - 1][0]
          .matchId
      ) {
        this.modalMatch.nextMatch = null;
      } else {
        this.modalMatch.nextMatch =
          this.$store.state.matches[0].length + Math.ceil(match.matchId / 2);
      }
    },

    putMatch(updatedMatch) {
      console.log(updatedMatch);
      MatchServices.updateMatch(updatedMatch).then((response) => {
        if (response.status == 200) {
          window.location.reload();
          console.log('got here :)');
        } else {
          console.log('we failed :(');
        }
      });
    },
  },
  created() {
    console.log(this.tournament);
  },
};
</script>

<style scoped>
.competitor-one-div {
  width: 200px;
  background-color: rgb(0, 0, 15);
  border-top-left-radius: 5px;
  border-top-right-radius: 5px;
}

.competitor-two-div {
  width: 200px;
  background-color: #555555;
  border-bottom-left-radius: 5px;
  border-bottom-right-radius: 5px;
}

.empty-spacer {
  width: 50px;
  height: 27px;
  border-color: grey !important;
}

.border {
  border-color: grey !important;
}

.border-lit {
  border-color: lime !important;
  box-shadow: 0 0 10px 0px lime;
  animation-name: flicker;
  animation-duration: 1.7s;
  animation-iteration-count: infinite;
}

.empty-spacer-bottom-lit {
  box-shadow: 0px -9px 10px -7px lime;
  border-color: lime !important;
  animation-name: flicker;
  animation-duration: 1.7s;
  animation-iteration-count: infinite;
  /* animation-direction: alternate; */
}

.border-bottom {
  border-color: grey !important;
}

.border-bottom-lit {
  box-shadow: 0px 6px 10px -7px lime;
  border-color: lime !important;
  animation-name: flicker;
  animation-duration: 1.7s;
  animation-iteration-count: infinite;
  /* animation-direction: alternate; */
}

.override-border {
  border: none !important;
  box-shadow: none;
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
    box-shadow: 0 0 0.5rem #fff, inset 0 0 0.5rem #fff, 0 0 2rem var(lime),
      inset 0 0 2rem var(lime), 0 0 4rem var(lime), inset 0 0 4rem var(lime);
  }

  20%,
  24%,
  55% {
    box-shadow: none;
  }
}
</style>
