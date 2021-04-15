<template>
<div class="d-flex flex-column align-items-center">
  <div class="d-flex flex-row justify-contents-around">
    <div>
      <h5 class="m-3">Default Team Image: </h5>
      <img src="http://localhost:8081/img/vectorBlueLogoXTourney.bbc29c6e.png"> 
    </div>
    <div>
      <h5 class="m-3">Team Image Preview: </h5>
      <img :src="this.team.teamImage" alt="Paste in an image URL and watch the magic!">
    </div>
  </div>
    <form class="bg-primary text-dark p-5 rounded mt-3" 
    v-on:submit.prevent="createTeam">
      <div class="col">
      <label for="Name">Team Name: 
        <input type="text" name="teamName" 
          id="teamName" v-model="team.teamName" 
          placeholder="Make it original!"/>
      </label>     
      </div>

      <div class="col">
      <label for="teamImage">Team Image URL: 
        <input type="text" name="teamImage" 
        id="teamImage" v-model="team.teamImage"
        placeholder="Its cool to be square!">
      </label> 
        <p>TIP: Be sure to use a square photo to avoid distorting your team image.</p>
      </div>
      <button type="submit">Submit</button>
    </form>
  </div>
</template>

<script>
import TeamsService from '../services/TeamsService.js';

export default {
  data() {
    return {
      team: {
        teamName: '',
        teamImage: '',
      }
    };
  },

  methods: {
    createTeam() {
      if (this.team.teamImage == '' || this.team.teamImage == undefined || this.team.teamImage == null) {
        this.team.teamImage = 'http://localhost:8081/img/vectorBlueLogoXTourney.bbc29c6e.png';
      }
      TeamsService.createTeam(this.team).then(response => {
        if (response.status == 201) {
          let newTeam = response.data;
          TeamsService.postUserToTeam(newTeam.teamId, this.$store.state.user.id).then(response => {
            if(response.status == 201) {
              alert(`Creation of team ${this.team.teamName} was successful!`);
              this.$router.push({name: 'teams'});
            }
          })   
        }
      });
      
    }
  },
}
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