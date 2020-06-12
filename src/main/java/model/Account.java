package model;

public class Account {

    private final Double currentBalance;

    public Account(Double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public double getCurrentBalance(){
        return currentBalance;
    }
}