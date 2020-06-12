package services;

import dao.AccountDao;
import dao.HistoryTransactionDao;
import model.HistoryTransaction;
import model.Transaction;

import java.util.List;

public class BankService {

    AccountDao accountDao;
    HistoryTransactionDao historyTransactionDao;

    public final static double MINIMUM_DEPOSIT_VALUE=0.01;
    public final static double OVERDRAFT_VALUE =0;

    public BankService(AccountDao accountDao,HistoryTransactionDao historyTransactionDao) {
        this.accountDao = accountDao;
        this.historyTransactionDao=historyTransactionDao;
    }

    public void makeDeposit(Double amount,Long accountID){
        if(amount>MINIMUM_DEPOSIT_VALUE)
            persitOperation(Transaction.DEPOSIT,amount,accountID);
    }

    public void makeWithDraw(Double amount,Long accountID){
        if(accountDao.getBalance(accountID)>OVERDRAFT_VALUE && amount>0)
            persitOperation(Transaction.WITHDRAW,-amount,accountID);
    }

    public Double getBalance(Long accountID){
        return accountDao.getBalance(accountID);
    }

    public List<HistoryTransaction> getTransactions(Long accountID){
        return historyTransactionDao.getTransactions(accountID);
    }

    private void persitOperation(Transaction transaction,Double amount,Long accountID){
        accountDao.updateBalance(accountID,amount);
        historyTransactionDao.addTransaction(new HistoryTransaction(transaction,amount,accountID));
    }
}