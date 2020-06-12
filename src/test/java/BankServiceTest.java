import static org.mockito.Mockito.*;

import dao.AccountDao;
import dao.HistoryTransactionDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import services.BankService;

public class BankServiceTest {

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
    public void deposit_underthan_one_cents_not_allowed(){
        bankService.makeDeposit(-100D,1L);
        verifyNoCallUpdate();
    }

    @Test
    public void deposit_of_one_cents_not_allowed(){
        bankService.makeDeposit(0.01,1L);
        verifyNoCallUpdate();
    }

    @Test
    public void deposit_morethan_one_cents_allowed(){
        bankService.makeDeposit(100D,1L);
        verify(accountDao,Mockito.times(1)).updateBalance(1L,100D);
    }

    @Test
    public void withdraw_with_overdraft_client_not_allowed(){
        when(accountDao.getBalance(1L)).thenReturn(0D);
        bankService.makeWithDraw(100D,1L);
        verifyNoCallUpdate();
    }

    @Test
    public void withdraw_with_no_overdraft_client_allowed(){
        when(accountDao.getBalance(1L)).thenReturn(100D);
        bankService.makeWithDraw(100D,1L);
        verify(accountDao,Mockito.times(1)).updateBalance(1L,-100D);
    }

    @Test
    public void client_with_2deposit_of_300_SHOULD_get_500_balance(){
        bankService.makeDeposit(200D,1L);
        bankService.makeDeposit(300D,1L);
        verify(accountDao,Mockito.times(1)).updateBalance(1L,200D);
        verify(accountDao,Mockito.times(1)).updateBalance(1L,300D);
    }

    @Test
    public void client_with_deposit_of_300_and_withdraw_of_200_SHOULD_get_100_balance(){
        when(accountDao.getBalance(1L)).thenReturn(300D);

        bankService.makeDeposit(300D,1L);
        bankService.makeWithDraw(200D,1L);

        verify(accountDao,Mockito.times(1)).updateBalance(1L,300D);
        verify(accountDao,Mockito.times(1)).updateBalance(1L,-200D);
    }

    private void verifyNoCallUpdate(){
        verify(accountDao,never()).updateBalance(Mockito.anyLong(),Mockito.anyDouble());
    }
}