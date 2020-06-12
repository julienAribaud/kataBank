public class Account {

    private final Double currentBalance;
    public final static double MINIMUM_DEPOSIT_VALUE=0.01;
    public final static double OVERDRAFT_VALUE =0;

    public Account(Double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public boolean makeDeposit(double amount) {
        return amount>MINIMUM_DEPOSIT_VALUE;
    }

    public boolean makeWithdraw() {
        return currentBalance>OVERDRAFT_VALUE;
    }
}
