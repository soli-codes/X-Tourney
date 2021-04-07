<template>
    <div class="card m-3">
        <img class="card-img-top" v-bind:src="tournament.image"/>
        <div class="card-body">
            <h5 class="card-title">{{ tournament.name }}</h5>
            <p class="card-text">Start Date: {{ tournament.start_date }}</p>
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

</style>