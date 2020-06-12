package dao;

import model.HistoryTransaction;

import java.util.List;

public interface HistoryTransactionDao {

    void addTransaction(HistoryTransaction historyTransaction);

    List<HistoryTransaction> getTransactions(Long accountId);
}