<template>
  <div class="bg-secondary">
    <!-- make each div into a row flexbox // each will be a StepX button-->
    <router-link
      to="#"
      tag="div"
      class="d-flex flex-row justify-content-between align-items-center
                    m-4 bg-dark
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
          tournaments and more as an official X-Tourney Community Member
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
      to="#"
      tag="div"
      class="d-flex flex-row justify-content-between align-items-center
                    m-4 bg-dark
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
        <p class="text-primary">lorem ipsum</p>
      </div>
      <div>
        <img
          class="image border-right"
          src="https://images.saymedia-content.com/.image/ar_1:1%2Cc_fill%2Ccs_srgb%2Cfl_progressive%2Cq_auto:good%2Cw_1200/MTc0MjEyMzcyNzI5NTcxMTk2/best-of-michael-jordan-top-10-highlights-dunks.jpg"
        />
      </div>
    </router-link>
    <router-link
      to="#"
      tag="div"
      class="d-flex flex-row justify-content-between align-items-center
                m-4 bg-dark
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
          Ready to manage your very own squad? Our creation tool makes it easy to
          build, manage, and enroll your team in competitions.
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
      to="#"
      tag="div"
      class="d-flex flex-row justify-content-between align-items-center
                m-4 bg-dark
                border border-5 border-danger rounded-3"
    >
      <div>
        <img
          class="image border-left"
          src="https://f4.bcbits.com/img/a2721135437_16.jpg"
        />
      </div>
      <!-- make div into vertical flexbox // will contain StepX title and description -->
      <div class="d-flex flex-column justify-content-around align-items-center">
        <h2>Host a Tournament</h2>
        <p class="text-primary">lorem ipsum</p>
      </div>
      <div>
        <img
          class="image border-right"
          src="https://images.hdqwalls.com/download/warrior-fantasy-art-4k-v6-2932x2932.jpg"
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

.image {
  height: 175px;
  width: 175px;
}

.border {
  border-radius: 25px !important;
  box-shadow: 0px 0px 2px 2px #ff455d;
}

.border:hover {
  box-shadow: 0px 0px 5px 5px #ff455d;
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
</style>
