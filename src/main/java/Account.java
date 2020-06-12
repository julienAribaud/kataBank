public class Account {

    public final static double MINIMUM_DEPOSIT_VALUE=0.01;

    public boolean makeDeposit(double amount) {
        return (amount>MINIMUM_DEPOSIT_VALUE);
    }
}
