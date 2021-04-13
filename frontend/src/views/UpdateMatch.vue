<template>
    <form>
        <label>Winning Team: </label>
        <select class="form-select" v-model="winningTeamId" />
        <label>Losing Team: </label>
        <select class="form-select" v-model="losingTeamId" />
    </form>
</template>

<script>
export default {
    data() {
        return {
            winningTeamId: "",
            losingTeamId: "",
        }
    }
    
}
</script>