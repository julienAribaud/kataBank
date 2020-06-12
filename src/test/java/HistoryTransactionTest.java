import dao.AccountDao;
import dao.HistoryTransactionDao;
import model.HistoryTransaction;
import model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import services.BankService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HistoryTransactionTest {

    @Mock
    private AccountDao accountDao;

    @Mock
    private HistoryTransactionDao historyTransactionDao;

    @InjectMocks
    private BankService bankService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void deposit_morethan_one_cents_generate_an_history_transaction(){
        bankService.makeDeposit(100D,1L);

        ArgumentCaptor<HistoryTransaction> historyTransactionArgumentCaptor = ArgumentCaptor.forClass(HistoryTransaction.class);

        verify(historyTransactionDao,Mockito.times(1)).addTransaction(historyTransactionArgumentCaptor.capture());

        HistoryTransaction historyTransaction = historyTransactionArgumentCaptor.getValue();

        assertThat(historyTransaction.getAmount()).isEqualTo(100);
        assertThat(historyTransaction.getTransaction()).isEqualTo(Transaction.DEPOSIT);
    }

    @Test
    public void withdraw_with_no_overdraft_generate_an_history_transaction(){

        when(accountDao.getBalance(1L)).thenReturn(300D);
        bankService.makeWithDraw(200D,1L);

        ArgumentCaptor<HistoryTransaction> historyTransactionArgumentCaptor = ArgumentCaptor.forClass(HistoryTransaction.class);

        verify(historyTransactionDao,Mockito.times(1)).addTransaction(historyTransactionArgumentCaptor.capture());

        HistoryTransaction historyTransaction = historyTransactionArgumentCaptor.getValue();

        assertThat(historyTransaction.getAmount()).isEqualTo(-200);
        assertThat(historyTransaction.getTransaction()).isEqualTo(Transaction.WITHDRAW);
    }

}
