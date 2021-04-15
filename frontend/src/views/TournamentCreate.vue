<template>
  <div class="d-flex flex-column align-items-center">
    <div class="d-flex flex-row justify-contents-around">
      <div>
        <h5 class="m-3">Default Tournament Image:</h5>
        <img
          src="http://localhost:8081/img/vectorBlueLogoXTourney.bbc29c6e.png"
        />
      </div>
      <div>
        <h5 class="m-3">Tournament Image Preview:</h5>
        <img
          :src="tournament.tournamentImage"
          alt="Paste in an image URL and watch the magic!"
        />
      </div>
    </div>
    <form
      class="bg-primary text-dark p-5 rounded mt-3"
      v-on:submit.prevent="createTournament"
    >
      <div class="row">
        <div class="col">
          <label style="display: block" for="Name"
            >Tournament Name:
            <input
              style="display: block"
              type="text"
              name="name"
              id="name"
              v-model="tournament.name"
            />
          </label>
        </div>
        <div class="col">
          <label for="gameType"
            >Game Selection
            <select
              class="form-select"
              v-model="tournament.gameTypeId"
              aria-label="Select a game"
              id="gameOptions"
            >
              <option disabled selected value="">Select a game</option>
              <option
                v-for="game in gameTypes"
                :key="game.gameTypeId"
                :value="game.gameTypeId"
              >
                {{ game.gameType }}
              </option>
            </select>
          </label>
        </div>
      </div>
      <div class="row">
        <label style="display: block" for="tournamentImage"
          >Tournament Image URL:
          <input
            style="display: block"
            type="text"
            name="tournamentImage"
            id="tournamentImage"
            v-model="tournament.tournamentImage"
            placeholder="Its cool to be square!"
          />
        </label>
        <p>
          TIP: Be sure to use a square photo to avoid distorting your team
          image.
        </p>
      </div>
      <div class="row">
        <div class="col">
          <label for="start-date"
            >Start Date
            <input
              type="date"
              name="start-date"
              id="start-date"
              v-model="tournament.startDate"
            />
          </label>
        </div>
        <div class="col">
          <label style="display: block" for="start-time"
            >Start Time
            <input
              style="display: block"
              type="time"
              name="start-time"
              id="start-time"
              v-model="tournament.startTime"
            />
          </label>
        </div>
        <div class="col">
          <label for="end-date"
            >End Date
            <input
              type="date"
              name="end-date"
              id="end-date"
              v-model="tournament.endDate"
            />
          </label>
        </div>
      </div>
      <div class="row">
        <div class="col">
          <label style="display: block" for="signup-open"
            >Signups Open
            <input
              style="display: block"
              type="date"
              name="signup-open"
              id="signup-open"
              v-model="tournament.signupOpen"
            />
          </label>
        </div>
        <div class="col">
          <label style="display: block" for="signup-close"
            >Signups Close
            <input
              style="display: block"
              type="date"
              name="signup-close"
              id="signup-close"
              v-model="tournament.signupClose"
            />
          </label>
        </div>
      </div>

      <div class="row">
        <label for="Description"
          >Description:
          <textarea
            class="form-control"
            name="description"
            id="description"
            rows="5"
            v-model="tournament.description"
          ></textarea>
        </label>
      </div>
      <div class="row">
        <label for="Open registration"
          >Check if tournament is private (invite only)
          <input
            type="checkbox"
            name="Open registration"
            id="openRegistration"
            v-model="tournament.private"
          />
        </label>
      </div>
      <!-- <div class="row">
      <label for="Open tournament">Open Tournament 
      <input
        type="checkbox"
        name="Open Tournament"
        id="openTournament"
        v-model="tournament.open"
      />
      </label>
    </div> -->
      <div class="col-xs-4">
        <select
          class="form-select"
          v-model="tournament.maxTeams"
          aria-label="Select amount of teams participating"
          id="maxTeams"
        >
          <option disabled selected value=""
            >Select amount of teams participating</option
          >
          <option>4</option>
          <option>8</option>
          <option>16</option>
          <option>32</option>
          <option>64</option>
        </select>
      </div>
      <button type="submit">Submit</button>
    </form>
  </div>
</template>

<script>
import TournamentService from '../services/TournamentsService.js';
import GamesService from '../services/GamesService.js';
export default {
  data() {
    return {
      tournament: {
        name: '',
        description: '',
        open: '',
        maxTeams: '',
        startDate: '',
        startTime: '',
        endDate: '',
        signupOpen: '',
        signupClose: '',
        gameTypeId: '',
        eliminationType: 'Single Elimination',
        private: '',
        tournamentID: null,
        tournamentImage: '',
      },
      gameTypes: [],
    };
  },
  methods: {
    createTournament() {
      const newTournament = {
        eliminationType: this.tournament.eliminationType,
        startDate: this.tournament.startDate,
        endDate: this.tournament.endDate,
        signUpOpen: this.tournament.signupOpen,
        signUpClose: this.tournament.signupClose,
        name: this.tournament.name,
        description: this.tournament.description,
        gameTypeId: this.tournament.gameTypeId,
        maxTeamCount: this.tournament.maxTeams,
        hostId: this.$store.state.user.id,
        startTime: this.tournament.startTime,
        open: this.tournament.open,
        private: this.tournament.private,
      };
      if (
        this.tournament.tournamentImage == '' ||
        this.tournament.tournamentImage == undefined ||
        this.tournament.tournamentImage == null
      ) {
        newTournament.tournamentImage =
          'http://localhost:8081/img/vectorBlueLogoXTourney.bbc29c6e.png';
      } else {
        newTournament.tournamentImage = this.tournament.tournamentImage;
      }
      console.log(newTournament);
      TournamentService.createTournament(newTournament)
        .then((response) => {
          if (response.status === 201) {
            console.log('wow we did it');
            alert(
              `Creation of tournament ${newTournament.name} was successful!`
            );
            this.$router.push({ name: 'tournaments' });
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  created() {
    GamesService.getGames().then((response) => {
      this.gameTypes = response.data;
    });
  },
};
</script>

<style scoped>
img {
  height: 200px;
  width: 200px;
}

.rounded {
  border-radius: 10px !important;
}
</style>
