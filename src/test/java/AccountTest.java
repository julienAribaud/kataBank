import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    private Account account;

    @BeforeEach
    public void init(){
        account =new Account();
    }

    @Test
    public void Deposit_under_one_cents_not_allowed(){

        assertThat(account.makeDeposit(-100)).isFalse();
    }

    @Test
    public void deposit_equals_one_cents_not_allowed(){

        assertThat(account.makeDeposit(0.01)).isFalse();
    }

    @Test
    public void deposit_more_one_cents_allowed(){

        assertThat(account.makeDeposit(100)).isTrue();
    }
}
