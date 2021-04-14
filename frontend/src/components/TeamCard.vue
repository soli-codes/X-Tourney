<template>
  <div class="card m-3 border border-5 border-danger bg-dark">
    <img
      class="card-img-top"
      :src="team.teamImage"
    />
    <div class="card-body">
      <p class="card-title text-center">{{ team.teamName }}</p>
      <p class="card-text text-center text-primary">Wins: {{ team.wins }}</p>
      <p class="card-text text-center text-primary">
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
