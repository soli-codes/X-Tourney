<template>
  <div class="bg-dark">
    <!-- make each div into a row flexbox // each will be a StepX button-->
    <router-link
      :to="{ name: 'login' }"
      tag="div"
      class="d-flex flex-row justify-content-between align-items-center
                    m-4 step-card
                    border border-5 border-danger rounded-3"
    >
      <div>
        <img
          class="image border-left"
          src="https://board.rankedgaming.com/uploads/monthly_2018_09/WoWIcon22.jpg.6c6c8cee7da0c4bb901a83977968d7b0.jpg"
        />
      </div>
      <!-- make div into vertical flexbox // will contain StepX title and description -->
      <div class="d-flex flex-column justify-content-around align-items-center">
        <h2>Create a Profile</h2>
        <p class="text-primary">
          Show off your stats and become eligible to join teams, participate in
          tournaments and more as an official X-Tourney Community Member.
        </p>
      </div>
      <div>
        <img
          class="image border-right"
          src="https://i.pinimg.com/originals/b5/bb/75/b5bb75cddad27275368607ee09263bec.jpg"
        />
      </div>
    </router-link>
    <router-link
      :to="{ name: 'teams' }"
      tag="div"
      class="d-flex flex-row justify-content-between align-items-center
                    m-4 step-card
                    border border-5 border-danger rounded-3"
    >
      <div>
        <img
          class="image border-left"
          src="https://cdn.vox-cdn.com/thumbor/WPYTc55lvg1IQ6dOKsA5F2pNTWc=/0x61:2044x1424/1400x1400/filters:focal(0x61:2044x1424):format(jpeg)/cdn.vox-cdn.com/uploads/chorus_image/image/47244358/GettyImages-2912742.0.jpg"
        />
      </div>
      <!-- make div into vertical flexbox // will contain StepX title and description -->
      <div class="d-flex flex-column align-items-center">
        <h2>Scout the Opposition</h2>
        <p class="text-primary">
          See where you stand by browsing all of the teams on X-Tourney. See how
          you compare by checking statistics in our team-details view.
        </p>
      </div>
      <div>
        <img
          class="image border-right"
          src="https://images.saymedia-content.com/.image/ar_1:1%2Cc_fill%2Ccs_srgb%2Cfl_progressive%2Cq_auto:good%2Cw_1200/MTc0MjEyMzcyNzI5NTcxMTk2/best-of-michael-jordan-top-10-highlights-dunks.jpg"
        />
      </div>
    </router-link>
    <router-link
      :to="{ name: 'createTeam' }"
      tag="div"
      class="d-flex flex-row justify-content-between align-items-center
                m-4 step-card
                border border-5 border-danger rounded-3"
    >
      <div>
        <img
          class="image border-left"
          src="https://playerswiki.com/uploads/bio/rickie-fowler.jpg"
        />
      </div>
      <!-- make div into vertical flexbox // will contain StepX title and description -->
      <div class="d-flex flex-column justify-content-around align-items-center">
        <h2>Manage a Team</h2>
        <p class="text-primary">
          Ready to manage your very own squad? Our creation tool makes it easy
          to build, manage, and enroll your team in competitions.
        </p>
      </div>
      <div>
        <img
          class="image border-right"
          src="https://images.wsj.net/im-39167?width=1280&size=1"
        />
      </div>
    </router-link>
    <router-link
      :to="{ name: 'createTournament' }"
      tag="div"
      class="d-flex flex-row justify-content-between align-items-center
                m-4 step-card
                border border-5 border-danger rounded-3"
    >
      <div>
        <img
          class="image border-left"
          src="https://pyxis.nymag.com/v1/imgs/41a/0dd/1503366ab1d665452cb9f0132647b97f15-03-the-hulk.rsquare.w700.jpg"
        />
      </div>
      <!-- make div into vertical flexbox // will contain StepX title and description -->
      <div class="d-flex flex-column justify-content-around align-items-center">
        <h2>Host a Tournament</h2>
        <p class="text-primary">
          Ready to do more than just play? Create your own tournament today!
        </p>
      </div>
      <div>
        <img
          class="image border-right"
          src="https://ringthedamnbell.files.wordpress.com/2019/12/herjerc.jpg?w=670"
        />
      </div>
    </router-link>
  </div>
</template>

<script>
export default {
  name: 'TheMainHelp',
};
</script>

<style scoped>
@import url(https://fonts.googleapis.com/css?family=Exo+2:200i);

.step-card {
  background-color: rgb(0, 0, 15);
}

.image {
  height: 175px;
  width: 175px;
}

.border {
  border-radius: 25px !important;
  box-shadow: 0px 0px 2px 2px lime;
}

.border:hover {
  box-shadow: 0px 0px 5px 5px lime;
  transition-duration: 100ms;
}

.border-left {
  border-top-left-radius: 20px;
  border-bottom-left-radius: 20px;
}

.border-right {
  border-top-right-radius: 20px;
  border-bottom-right-radius: 20px;
}

.flex-column {
  width: 50%;
}

p {
  text-align: center;
}

h2 {
  color: #00fff2;
}
@media (max-width: 500px) {
  img {
    display: none;
  }
}
</style>
