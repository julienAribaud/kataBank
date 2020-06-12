package model;

public class HistoryTransaction {

    Transaction transaction;
    Double amount;

    public HistoryTransaction(Transaction transaction,Double amount) {
        this.transaction = transaction;
        this.amount=amount;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public Double getAmount() {
        return amount;
    }
}