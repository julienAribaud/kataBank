package model;

public class HistoryTransaction{

    Transaction transaction;
    Double amount;
    Long accountID;

    public HistoryTransaction(Transaction transaction,Double amount,Long accountID) {
        this.transaction = transaction;
        this.amount=amount;
        this.accountID=accountID;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public Double getAmount() {
        return amount;
    }
}