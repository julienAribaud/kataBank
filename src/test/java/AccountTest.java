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
    public void withdraw_with_overdraft_client_not_allowed(){

        assertThat(account.makeWithdraw(100)).isFalse();
    }

    @Test
    public void withdraw_with_no_overdraft_client_allowed(){

        account=new Account(100D);
        assertThat(account.makeWithdraw(100)).isTrue();
    }

    @Test
    public void new_client_with_no_deposit_SHOULD_get_init_balance(){
        assertThat(account.getCurrentBalance()).isEqualTo(0);
    }

    @Test
    public void client_with_2deposit_of_200_SHOULD_get_400_balance(){
        account.makeDeposit(200);
        account.makeDeposit(200);
        assertThat(account.getCurrentBalance()).isEqualTo(400);
    }

    @Test
    public void client_with_deposit_of_400_and_withdraw_of_300_SHOULD_get_100_balance(){
        account.makeDeposit(400);
        account.makeWithdraw(300);
        assertThat(account.getCurrentBalance()).isEqualTo(100);
    }
}