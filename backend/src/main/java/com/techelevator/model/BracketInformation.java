package com.techelevator.model;


import java.util.List;

public class BracketInformation {
    private int tournamentSize;
    private int tournamentId;
    private List<Integer> teams;

    public BracketInformation() {

    }

    public int getTournamentId() {
        return tournamentId;
    }

    public List<Integer> getTeams() {
        return teams;
    }


    public int getTournamentSize() {
        return tournamentSize;
    }
}
