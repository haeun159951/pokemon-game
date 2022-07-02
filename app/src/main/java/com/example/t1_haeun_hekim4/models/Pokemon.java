package com.example.t1_haeun_hekim4.models;

public class Pokemon {

    private int number;
    private String name;
    private int wins = 0;
    private int loss = 0;

    public Pokemon(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLoss() {
        return loss;
    }

    public void setLoss(int loss) {
        this.loss = loss;
    }

    @Override
    public String toString() {
        return "Pokemon: " +
                "number: " + number +
                ", name: '" + name + '\'' +
                ", wins: " + wins +
                ", loss: " + loss;
    }
}
