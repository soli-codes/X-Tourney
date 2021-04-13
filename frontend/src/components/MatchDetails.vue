<template>
    <div>
        <form v-on:submit.prevent="putMatch(newMatch)">
            <div>
                <label>Match Winner:</label>
                <select v-model="newMatch.winningTeamId">
                    <option :value=" match.teamOneId ">{{ getTeamName(match.teamOneId) }}</option>
                    <option :value=" match.teamTwoId ">{{ getTeamName(match.teamTwoId) }}</option>
                </select>
            </div>
            <div>
                <label>Match Loser:</label>
                <select v-model="newMatch.losingTeamId">
                    <option :value=" match.teamOneId ">{{ getTeamName(match.teamOneId) }}</option>
                    <option :value=" match.teamTwoId ">{{ getTeamName(match.teamTwoId) }}</option>
                </select>
            </div>
            <div>
                <label>Winning Team Score: </label>
                <input type="text" v-model="newMatch.winningTeamScore" placeholder="Winning Team Score" />
            </div>
            <div>
                <label>Losing Team Score: </label>
                <input type="text" v-model="this.newMatch.losingTeamScore" placeholder="Losing Team Score" />
            </div>
            <button type="submit" />
        </form>
    </div>
</template>

<script>
import MatchServices from '../services/MatchServices';
    
export default {
    props: [ 'match' ],

    data() {
        return {
            newMatch: {
            
                matchId: '',
                tournamentId: '',
                teamOneId: '',
                teamTwoId: '',
                winningTeamId: '',
                losingTeamId: '',
                winningTeamScore: '',
                losingTeamScore: '',
                matchDate: '',
                matchTime: '',
                nextMatch: '',

            }
        }
    },

    created() {

        this.newMatch.matchDate = this.match.matchDate;
        this.newMatch.matchTime = this.match.matchTime;
        this.newMatch.matchId = this.match.matchId;
        this.newMatch.tournamentId = this.match.tournamentId;
        this.newMatch.teamOneId = this.match.teamOneId;
        this.newMatch.teamTwoId = this.match.teamTwoId;

    },

    methods: {

        getTeamName(teamId) {
            if (teamId == 1) {
                return 'TO BE DECIDED' ;
            }
            let team = this.$store.state.teams.find((team) => {
                return team.teamId == teamId;
            });
            return team.teamName;
        },

        putMatch(newMatch) {
            MatchServices.updateMatch(newMatch);
        }

    }
    
}
</script>

<style scoped>

</style>