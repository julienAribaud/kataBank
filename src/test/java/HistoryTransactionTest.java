import model.HistoryTransaction;
import model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import services.AccountService;
import services.BankService;
import services.HistoryTransactionService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HistoryTransactionTest {

    @Mock
    private AccountService accountService;

    @Mock
    private HistoryTransactionService historyTransactionService;

    @InjectMocks
    private BankService bankService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void deposit_morethan_one_cents_generate_an_history_transaction(){
        bankService.makeDeposit(100D);

        ArgumentCaptor<HistoryTransaction> historyTransactionArgumentCaptor = ArgumentCaptor.forClass(HistoryTransaction.class);

        verify(historyTransactionService,Mockito.times(1)).addTransaction(historyTransactionArgumentCaptor.capture());

        HistoryTransaction historyTransaction = historyTransactionArgumentCaptor.getValue();

        assertThat(historyTransaction.getAmount()).isEqualTo(100);
        assertThat(historyTransaction.getTransaction()).isEqualTo(Transaction.DEPOSIT);
    }

    @Test
    public void withdraw_with_no_overdraft_generate_an_history_transaction(){

        when(accountService.getBalance()).thenReturn(300D);
        bankService.makeWithDraw(200D);

        ArgumentCaptor<HistoryTransaction> historyTransactionArgumentCaptor = ArgumentCaptor.forClass(HistoryTransaction.class);

        verify(historyTransactionService,Mockito.times(1)).addTransaction(historyTransactionArgumentCaptor.capture());

        HistoryTransaction historyTransaction = historyTransactionArgumentCaptor.getValue();

        assertThat(historyTransaction.getAmount()).isEqualTo(-200);
        assertThat(historyTransaction.getTransaction()).isEqualTo(Transaction.WITHDRAW);
    }

}
