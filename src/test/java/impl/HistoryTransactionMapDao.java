package impl;

import dao.HistoryTransactionDao;
import model.HistoryTransaction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HistoryTransactionMapDao implements HistoryTransactionDao {

    private ArrayList<HistoryTransaction> historyTransactionList= new ArrayList<>();

    public void addTransaction(HistoryTransaction historyTransaction) {
        historyTransactionList.add(historyTransaction);
    }

    public List<HistoryTransaction> getTransactions(final Long accountId) {
        return historyTransactionList.stream()
                .filter(historyTransactionList->historyTransactionList.getAcountID().equals(accountId))
                .collect(Collectors.toList());
    }
}
