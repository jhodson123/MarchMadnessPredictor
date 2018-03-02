package com.company;

public class Team {

    public String teamName;
    public int wins;
    public int totalGames;
    public double winPercentage;
    public int seed;

    public Team(String n, int w, int t, int s) {

        teamName = n;
        wins = w;
        totalGames = t;
        winPercentage = ((double)wins/(double)totalGames)*100;
        seed = s;
    }

    public int compareTo(Team otherTeam){

        if (winPercentage < otherTeam.winPercentage){

            return -1;
        }else if (winPercentage > otherTeam.winPercentage){

            return 1;
        }else
            return 0;

    }
}
