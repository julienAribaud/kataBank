package impl;

import model.Account;
import model.HistoryTransaction;
import model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.BankService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BankServiceCollectionImplTest {

    BankService ramBankImpl;
    Long accountID=1L;

    @BeforeEach
    public void init(){
        AccountMapDao accountMapDao=new AccountMapDao();
        accountMapDao.addAccount(new Account(0D),accountID);
        HistoryTransactionMapDao historyTransactionMapDao=new HistoryTransactionMapDao();
        ramBankImpl=new BankService(accountMapDao,historyTransactionMapDao);
    }


    @Test
    public void one_deposit_of_350_SHOULD_have_1_transaction_and_350_balance(){

        ramBankImpl.makeDeposit(350D,accountID);
        assertThat(ramBankImpl.getBalance(accountID)).isEqualTo(350);

        List<HistoryTransaction> transactionList=ramBankImpl.getTransactions(accountID);
        assertThat(transactionList.size()).isEqualTo(1);
        assertThat(transactionList.get(0).getAmount()).isEqualTo(350);
        assertThat(transactionList.get(0).getTransaction()).isEqualTo(Transaction.DEPOSIT);
    }

    @Test
    public void one_deposit_of_450_AND_one_withdraw_of_250_SHOULD_have_2_transaction_and_200_balance(){

        ramBankImpl.makeDeposit(450D,accountID);
        ramBankImpl.makeWithDraw(250D,accountID);
        assertThat(ramBankImpl.getBalance(accountID)).isEqualTo(200);

        List<HistoryTransaction> transactionList=ramBankImpl.getTransactions(accountID);
        assertThat(transactionList.size()).isEqualTo(2);
        assertThat(transactionList.get(0).getAmount()).isEqualTo(450);
        assertThat(transactionList.get(0).getTransaction()).isEqualTo(Transaction.DEPOSIT);
        assertThat(transactionList.get(1).getAmount()).isEqualTo(-250);
        assertThat(transactionList.get(1).getTransaction()).isEqualTo(Transaction.WITHDRAW);
    }
}