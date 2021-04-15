<template>
  <div class="card m-3 border border-5 border-danger">
    <img
      class="card-img-top"
      :src="team.teamImage"
    />
    <div class="card-body">
      <p class="card-title text-center text-primary fs-6">{{ team.teamName }}</p>
      <p class="card-text text-center text-warning">Wins: {{ team.wins }}</p>
      <p class="card-text text-center text-warning">
        Losses: {{ team.losses }}
      </p>
    </div>
  </div>
</template>

<script>
export default {
  props: ['team'],
};
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
  background-color: rgb(0,0,15);
}

.card-title {
  color: #00fff2;
}

.card-title,
.card-text {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.border {
  border-radius: 25px !important;
  box-shadow: 0px 0px 5px 2px lime;
}

.border:hover {
  box-shadow: 0px 0px 5px 2px lime;
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
    box-shadow: 0 0 0.5rem #fff, inset 0 0 0.5rem #fff, 0 0 2rem var(lime),
      inset 0 0 2rem var(lime), 0 0 4rem var(lime),
      inset 0 0 4rem var(lime);
  }

  20%,
  24%,
  55% {
    box-shadow: none;
  }
}
</style>
