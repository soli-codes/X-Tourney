<template>
    <div class="card m-3">
        <img class="card-img-top" src="https://s7.orientaltrading.com/is/image/OrientalTrading/VIEWER_ZOOM/small-goldtone-trophies~39_804-g"/>
        <div class="card-body">
            <h5 class="card-title">{{ tournament.name }}</h5>
            <p class="card-text">Start Date: {{ tournament.startDate }}</p>
            <p class="card-text">{{ tournament.description }}</p>
        </div>
    </div>
</template>

<script>
export default {
    props: ["tournament"]
    
}
</script>

<style scoped>

.card-img-top {
    height: 200px;
    width: 200px;
}

</style>