package services;

import model.HistoryTransaction;
import model.Transaction;

public class BankService {

    AccountService accountService;
    HistoryTransactionService historyTransactionService;

    public final static double MINIMUM_DEPOSIT_VALUE=0.01;
    public final static double OVERDRAFT_VALUE =0;

    public BankService(AccountService accountService,HistoryTransactionService historyTransactionService) {
        this.accountService = accountService;
        this.historyTransactionService=historyTransactionService;
    }

    public void makeDeposit(Double amount){
        if(amount>MINIMUM_DEPOSIT_VALUE)
            persitOperation(Transaction.DEPOSIT,amount);
    }

    public void makeWithDraw(Double amount){
        if(accountService.getBalance()>OVERDRAFT_VALUE && amount>0)
            persitOperation(Transaction.WITHDRAW,-amount);
    }

    private void persitOperation(Transaction transaction,Double amount){
        accountService.updateBalance(amount);
        historyTransactionService.addTransaction(new HistoryTransaction(transaction,amount));
    }
}