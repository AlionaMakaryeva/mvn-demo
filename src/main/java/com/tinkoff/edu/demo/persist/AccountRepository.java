package com.tinkoff.edu.demo.persist;

import com.tinkoff.edu.demo.domain.Account;
import com.tinkoff.edu.demo.domain.DepositAccount;

import java.util.Collection;

public interface AccountRepository {
    int var = 0;

    void addAccount(Account account) throws NotEnoughSpaceException;
    int getAccountCount();
    double getMeanAmount();
    Collection<Account> getAccounts();
}
