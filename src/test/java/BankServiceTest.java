import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import services.AccountService;
import services.BankService;

public class BankServiceTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private BankService bankService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void deposit_underthan_one_cents_not_allowed(){
        bankService.makeDeposit(-100D);
        verify(accountService,never()).updateBalance(Mockito.anyDouble());
    }

    @Test
    public void deposit_of_one_cents_not_allowed(){
        bankService.makeDeposit(0.01);
        verify(accountService,never()).updateBalance(Mockito.anyDouble());
    }

    @Test
    public void deposit_morethan_one_cents_allowed(){
        bankService.makeDeposit(100D);
        verify(accountService,Mockito.times(1)).updateBalance(100D);
    }

    @Test
    public void withdraw_with_overdraft_client_not_allowed(){
        when(accountService.getBalance()).thenReturn(0D);
        bankService.makeWithDraw(100D);
        verify(accountService,never()).updateBalance(Mockito.anyDouble());
    }

    @Test
    public void withdraw_with_no_overdraft_client_allowed(){
        when(accountService.getBalance()).thenReturn(100D);
        bankService.makeWithDraw(100D);
        verify(accountService,Mockito.times(1)).updateBalance(-100D);
    }

    @Test
    public void client_with_2deposit_of_300_SHOULD_get_500_balance(){
        bankService.makeDeposit(200D);
        bankService.makeDeposit(300D);
        verify(accountService,Mockito.times(1)).updateBalance(200D);
        verify(accountService,Mockito.times(1)).updateBalance(300D);
    }

    @Test
    public void client_with_deposit_of_300_and_withdraw_of_200_SHOULD_get_100_balance(){
        when(accountService.getBalance()).thenReturn(300D);

        bankService.makeDeposit(300D);
        bankService.makeWithDraw(200D);

        verify(accountService,Mockito.times(1)).updateBalance(300D);
        verify(accountService,Mockito.times(1)).updateBalance(-200D);
    }
}