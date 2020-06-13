package model;

public class Account{

    private Double currentBalance;

    public Account(Double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public double getCurrentBalance(){
        return currentBalance;
    }

    public void updateBalance(Double amount) {
        this.currentBalance+=amount;
    }
}