<template>
  <div class="card m-3 border border-5 border-danger bg-dark">
    <img
      class="card-img-top"
      src="https://image-cdn.essentiallysports.com/wp-content/uploads/20200410200859/LogoVersion_Beta_Key_Art_VALORANT.jpg"
    />
    <div class="card-body">
      <h5 class="card-title text-center">{{ tournament.name }}</h5>
      <p class="card-text text-center text-primary">
        {{ tournament.startDate }}
      </p>
      <p class="card-text text-center text-primary">
        {{ tournament.description }}
      </p>
    </div>
  </div>
</template>

<script>
export default {
  props: ['tournament'],
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
  box-shadow: 0px 0px 5px 5px #ff455d;
}

.border:hover {
  box-shadow: 0px 0px 5px 5px #ff455d;
  animation: flicker 0.2s infinite alternate;
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
