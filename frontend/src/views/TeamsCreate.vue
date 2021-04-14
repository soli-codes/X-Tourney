<template>
<div>
    <h5>Default Team Image: </h5>
    <img src="http://localhost:8081/img/vectorBlueLogoXTourney.bbc29c6e.png"> 

    <h5>Team Image Preview: </h5>
    <img :src="this.team.teamImage">
    <form v-on:submit.prevent="">
      <label for="Name">Name:</label>
          <input type="text" name="teamName" id="teamName" v-model="team.teamName" />
        <label for="teamImage">Team Image URL: </label>
        <input type="text" name="teamImage" id="teamImage" v-model="team.teamImage">
    </form>
  </div>
</template>

<!--'http://localhost:8081/img/vectorBlueLogoXTourney.bbc29c6e.png' -->

<script>

export default {
  data() {
    return {
      team: {
        teamName: '',
        teamImage: '',
      }
    };
  }
}
</script>

<style scoped>
img {
  height: 200px;
  width: 200px;
}
</style>