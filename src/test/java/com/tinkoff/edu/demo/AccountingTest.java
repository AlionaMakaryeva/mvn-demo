package com.tinkoff.edu.demo;

import com.tinkoff.edu.demo.domain.Account;
import com.tinkoff.edu.demo.domain.DepositAccount;
import com.tinkoff.edu.demo.persist.MemoryAccountRepository;
import com.tinkoff.edu.demo.persist.NotEnoughSpaceException;
import com.tinkoff.edu.demo.service.AccountService;
import com.tinkoff.edu.demo.service.NumberedDecorator;
import com.tinkoff.edu.demo.service.ReportingService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static com.tinkoff.edu.demo.domain.DepositAccount.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

public class AccountingTest {
    private MemoryAccountRepository accountRepository;
    private AccountService sut;


    @BeforeEach
    public void setUpServiceAndRepository(){
        accountRepository = new MemoryAccountRepository();
        sut = new AccountService(accountRepository);
    }
    @Test
    public void shouldGetNoAccountsCountWhenNoAccountsAdded() {
        assertThat(accountRepository.getAccountCount())
                .isEqualTo(0);

    }

    @Test
    public void shouldGetAccountsCountWhenAccountsAdded() throws NotEnoughSpaceException {
        sut.addAccount(new DepositAccount());
        assertThat(accountRepository.getAccountCount())
                .isPositive()
                .isEqualTo(1);
    }

    @Test
    public void shouldGetMeanAmountWhenAccountsExists() throws NotEnoughSpaceException {
        sut.addAccount(new DepositAccount(100.));
        sut.addAccount(new DepositAccount(0.));
        assertThat(accountRepository.getMeanAmount())
               .isEqualTo(50.);
    }
    @Test
    public void ShouldGetErrorWhenAddNullAccount() {
        assertThatThrownBy(() -> sut.addAccount(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("account must not be null");

    }

}
