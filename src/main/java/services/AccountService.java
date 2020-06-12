package services;

import dao.AccountDao;

public class AccountService {

    AccountDao accountDao;

    public AccountService(AccountDao accountDao){
        this.accountDao=accountDao;
    }

    public void updateBalance(double value){
        accountDao.updateBalance(value);
    }

    public Double getBalance(){
        return accountDao.getBalance();
    }
}