<template>
    <div>
        <div v-for="tournament in tournaments" :key="tournament.tournament_id" :tournament="tournament">
            <tourney-card :>
        </div>
    </div>
</template>

<script>
import TournamentCard from '../components/TournamentCard.vue'
export default {
  components: { TournamentCard },
    
}
</script>

<style scoped>

</style>