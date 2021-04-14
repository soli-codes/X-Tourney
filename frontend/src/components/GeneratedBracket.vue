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
        v-on:click="onClick(round, match)"
      >
        <div class="d-flex flex-row">
          <div v-if="index != 0">
            <div class="empty-spacer border-bottom border-4" />
            <div class="empty-spacer" />
          </div>
          <div>
            <div style="width: 200px; background-color: #333333">
              {{ getTeamName(match.teamOneId) }}
            </div>
            <div style="width: 200px; background-color: #333333">
              {{ getTeamName(match.teamTwoId) }}
            </div>
          </div>
          <div>
            <div
              :class="{
                'border-end': match.matchId % 2 == 0,
                'override-border': index == $store.state.matches.length - 1,
              }"
              class="empty-spacer border-bottom border-4"
            />
            <div
              :class="{
                'border-end': match.matchId % 2 == 1,
                'override-border': index == $store.state.matches.length - 1,
              }"
              class="empty-spacer border-4"
            />
          </div>
        </div>
      </div>
    </div>
    <!-- MODAL -->
    <div
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
                <option :value="modalMatch.teamOneId">{{
                  modalMatch.teamOneName
                }}</option>
                <option :value="modalMatch.teamTwoId">{{
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
            <button type="button" class="btn btn-dark" data-bs-dismiss="modal">
              Close
            </button>
            <button
              v-on:click="putMatch(modalMatch)"
              type="button"
              class="btn btn-danger"
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
  data() {
    return {
      array: [],
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
    getTeamName(teamId) {
      if (teamId == 1) {
        return 'TO BE DECIDED';
      }
      let team = this.$store.state.teams.find((team) => {
        return team.teamId == teamId;
      });
      return team.teamName;
    },

    onClick(round, match) {
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
    console.log(this.$store.state.matches);
    console.log(this.$store.state.teams);
  },
};
</script>

<style scoped>
.empty-spacer {
  width: 50px;
  height: 27px;
}

.override-border {
  border: none !important;
}
</style>
