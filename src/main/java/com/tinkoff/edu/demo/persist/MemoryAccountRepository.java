package com.tinkoff.edu.demo.persist;

import com.tinkoff.edu.demo.domain.Account;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Stream;

/**
 * constructor DI
 */
public class MemoryAccountRepository implements AccountRepository {
    public static final int MAX_ACCOUNTS = 10_000; //hard-coded > config
    private Collection<Account> accounts = new HashSet<>();

    /**
     * @throws ???
     */
    public void addAccount(Account account) throws NotEnoughSpaceException{
        if (account == null) throw new IllegalArgumentException("account must not be null");

        if (accounts.size() <= MAX_ACCOUNTS - 1) {
            accounts.add(account);
        } else {
            throw new NotEnoughSpaceException("Account store is full");
        }
    }

    public int getAccountCount() {
        return accounts.size();
    }

    public double getMeanAmount() {
//        double accountsSum = 0;
//        for (Account current : accounts) { //foreach
//            accountsSum += current.getAmount();
//        }

       var accountsSum = accounts.stream()
               .filter(accountElement -> accountElement != null)
               .map(accountElement -> accountElement.getAmount())
               .reduce((a1, a2) -> a1 + a2)
               .orElse(0.);

        return accountsSum / accounts.size();
    }

    public Collection<Account> getAccounts() {
        return Collections.unmodifiableCollection(this.accounts);
    }
}
