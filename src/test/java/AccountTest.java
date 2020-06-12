import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    private Account account;

    @BeforeEach
    public void init(){
        account =new Account(0D);
    }

    @Test
    public void deposit_underthan_one_cents_not_allowed(){

        assertThat(account.makeDeposit(-100)).isFalse();
    }

    @Test
    public void deposit_of_one_cents_not_allowed(){

        assertThat(account.makeDeposit(0.01)).isFalse();
    }

    @Test
    public void deposit_morethan_one_cents_allowed(){

        assertThat(account.makeDeposit(100)).isTrue();
    }

    @Test
    public void withdraw_with_overdraft_client_not_allowed_(){

        assertThat(account.makeWithdraw()).isFalse();
    }

    @Test
    public void withdraw_with_no_overdraft_client_allowed_(){

        account=new Account(100D);
        assertThat(account.makeWithdraw()).isTrue();
    }

}
