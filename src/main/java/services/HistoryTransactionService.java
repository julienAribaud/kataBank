package services;

import dao.HistoryTransactionDao;
import model.HistoryTransaction;

import java.util.List;

public class HistoryTransactionService {

    HistoryTransactionDao historyTransactionDao;

    public HistoryTransactionService(HistoryTransactionDao historyTransactionDao) {
        this.historyTransactionDao = historyTransactionDao;
    }

    public List<HistoryTransaction> getTransactions(){
        return historyTransactionDao.getTransactions();
    }

    public void addTransaction(HistoryTransaction historyTransaction){
        historyTransactionDao.addTransaction(historyTransaction);
    }
}
