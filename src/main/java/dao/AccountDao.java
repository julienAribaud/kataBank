package dao;

public interface AccountDao{

    void updateBalance(Long accountID,Double amount);

    Double getBalance(Long accountID);
}