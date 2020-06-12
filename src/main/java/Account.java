public class Account {

    private Double currentBalance;
    public final static double MINIMUM_DEPOSIT_VALUE=0.01;
    public final static double OVERDRAFT_VALUE =0;

    public Account(Double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public boolean makeDeposit(double amount) {
        if(amount<=MINIMUM_DEPOSIT_VALUE)
            return false;

        currentBalance+=amount;
        return true;
    }

    public boolean makeWithdraw(double amount) {
        if(currentBalance<=OVERDRAFT_VALUE){
            return false;
        }

        currentBalance-=amount;
        return true;
    }

    public double getCurrentBalance(){
        return currentBalance;
    }
}
