package com.tinkoff.edu.demo.service;

import com.tinkoff.edu.demo.domain.Account;
import com.tinkoff.edu.demo.domain.DepositAccount;
import com.tinkoff.edu.demo.persist.AccountRepository;
import com.tinkoff.edu.demo.persist.NotEnoughSpaceException;

public class AccountService {
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {

        this.accountRepository = accountRepository;
    }

    public void addClient(double... amounts) throws NotEnoughSpaceException {
        for (double amount : amounts) {
            try {
                accountRepository.addAccount(new DepositAccount(amount));
            } catch (NotEnoughSpaceException e) {
                e.printStackTrace();
                throw e;
            }
        }
    }

    public void addAccount(Account account) throws NotEnoughSpaceException {
        try {
            accountRepository.addAccount(account);
        } catch (IllegalArgumentException | NotEnoughSpaceException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
