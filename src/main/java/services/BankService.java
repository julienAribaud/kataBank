package services;

public class BankService {

    AccountService accountService;
    public final static double MINIMUM_DEPOSIT_VALUE=0.01;
    public final static double OVERDRAFT_VALUE =0;

    public BankService(AccountService accountService) {
        this.accountService = accountService;
    }

    public void makeDeposit(Double amount){
        if(amount>MINIMUM_DEPOSIT_VALUE)
            accountService.updateBalance(amount);
    }

    public void makeWithDraw(Double amount){
        if(accountService.getBalance()>OVERDRAFT_VALUE && amount>0)
            accountService.updateBalance(-amount);
    }
}