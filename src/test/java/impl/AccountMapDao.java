package impl;

import dao.AccountDao;
import model.Account;

import java.util.HashMap;

public class AccountMapDao implements AccountDao {

    private final HashMap<Long, Account> accountHashMap;
    {
        accountHashMap=new HashMap<>();
    }

    public void updateBalance(Long accountID, Double amount) {
        accountHashMap.get(accountID).updateBalance(amount);
    }

    public Double getBalance(Long accountID) {
        return accountHashMap.get(accountID).getCurrentBalance();
    }

    public void addAccount(Account account,Long accountId){
        accountHashMap.put(accountId,account);
    }
}
