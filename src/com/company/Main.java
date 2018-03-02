package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static Team[] teams;
    public static Team[] finalEight;
    public static Team[] finalFour;
    public static Team[] finals;

    public static Random rand = new Random();
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        int numTeams = 16;

        String[] adjectives = new String[]{"Slimy", "Stinky", "Heavy", "Cute", "Purple", "Handsome", "Ugly", "Wet", "Fluffy", "Crooked", "Robotic", "Horny"};
        String[] names = new String[]{"Rabbits", "Monkeys", "Pears", "Alligators", "Giraffes", "Hippos", "Sea lions", "Whales", "Cats", "Dogs", "Panthers"};


        teams = new Team[numTeams];

        for (int i = 0; i < numTeams; i++) {
            int adj = rand.nextInt(adjectives.length);
            int animal = rand.nextInt(names.length);
            String name = adjectives[adj] + " " + names[animal];
            int wins = rand.nextInt(30);
            teams[i] = new Team(name, wins, 30, i + 1);
        }


        Team[][] bracket = createBracket(teams);
        viewBracket(bracket);
        finalEight = chooseWinners(bracket);

        operator();


        System.out.println("Final Eight");
        System.out.println("------------");
        bracket = createBracket(finalEight);
        viewBracket(bracket);
        finalFour = chooseWinners(bracket);

        operator();

        System.out.println("Final Four");
        System.out.println("------------");
        bracket = createBracket(finalFour);
        viewBracket(bracket);
        finals = chooseWinners(bracket);

        operator();

        System.out.println("Finals");
        System.out.println("------------");
        bracket = createBracket(finals);
        viewBracket(bracket);

        int picker = rand.nextInt(2); //(record + conference)*(strength of schedules);
        System.out.println("Winner: " + finals[picker].teamName);
    }

    public static Team[][] createBracket(Team[] teams) {

        Team[][] bracket = new Team[teams.length / 2][2];

        for (int i = 0; i < bracket.length; i++) {
            bracket[i][0] = teams[i];
            bracket[i][1] = teams[teams.length - i - 1];
        }

        return bracket;
    }

    public static void viewBracket(Team[][] bracket) {

        for (int i = 0; i < bracket.length; i++) {

            System.out.println(bracket[i][0].seed + " " + bracket[i][0].teamName + " " + bracket[i][0].winPercentage);
            System.out.println(bracket[i][1].seed + " " + bracket[i][1].teamName + " " + bracket[i][1].winPercentage);
            System.out.println(" ");
        }
    }

    public static Team[] chooseWinners(Team[][] bracket){
        Team[] temp = new Team[bracket.length];

        for (int k=0; k<bracket.length; k++){

            int winner = bracket[k][0].compareTo(bracket[k][1]);
            if (winner == 1){
                temp[k] = bracket[k][0];
            }else if(winner == -1){
                temp[k] = bracket[k][1];
            }else {
                int p = rand.nextInt(2);
                if (p == 0){
                    temp[k] = bracket[k][0];
                }else temp[k] = bracket[k][1];
            }
        }

        return temp;
    }

    public static void operator(){

        while(true){

            System.out.println("Would you like to ADVANCE the bracket, CHECK a teams record, or EXIT");
            String response = input.nextLine();

            if (response.equalsIgnoreCase("EXIT")){
                System.exit(0);
            }else if (response.equalsIgnoreCase("ADVANCE")){
                return;
            }else if (response.equalsIgnoreCase("CHECK")){

                System.out.println("What team will you like to check");
                String name = input.nextLine();
                Team checkedTeam = findTeam(name);
                System.out.println(checkedTeam.teamName + " record is " + checkedTeam.wins + "/"
                        + (checkedTeam.totalGames - checkedTeam.wins));
            }
        }
    }

    public static Team findTeam(String name){

        for(Team t: teams){

            if (t.teamName.equalsIgnoreCase(name)){

                return t;
            }
        }
        return null;
    }
}
