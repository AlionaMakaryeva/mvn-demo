package com.tinkoff.edu.demo.service;

import com.tinkoff.edu.demo.domain.Account;
import com.tinkoff.edu.demo.persist.AccountRepository;

import java.util.Collection;

public class ReportingService {
    private AccountRepository accountRepository;
    private NumberedDecorator numberedDecorator;
    private StringDecorator timestampDecorator;

    public ReportingService(AccountRepository accountRepository, NumberedDecorator numberedDecorator, StringDecorator timestampDecorator) {
        this.accountRepository = accountRepository;
        this.numberedDecorator = numberedDecorator;
        this.timestampDecorator = timestampDecorator;
    }

    public String getReport(){
        Collection<Account> accounts = accountRepository.getAccounts();
        String[] NumberedAccounts = numberedDecorator.decorate(accounts);

        StringBuilder report = new StringBuilder();
        for (Account currentAccount : accounts) {
            if (currentAccount == null) break;

            String timestampedAccount = timestampDecorator.decorate(currentAccount.toString());
            report.append(timestampedAccount).append("\n");
             }
        return report.toString();
    }
}
