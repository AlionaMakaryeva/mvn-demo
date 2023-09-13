package com.tinkoff.edu.demo.service;

import com.tinkoff.edu.demo.domain.Account;
import com.tinkoff.edu.demo.persist.MemoryAccountRepository;

import java.util.Collection;

public class NumberedDecorator {
    public String[] decorate(Collection<Account> accounts) {
        String[] decoratedAccounts = new String[accounts.size()];
        int counter = 0;
        for (Account current : accounts) {

            decoratedAccounts[counter] = counter + " " + current;
            counter++;
        }
        return new String[0];
    }
}
