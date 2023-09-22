package com.tinkoff.edu.demo.service;

import com.tinkoff.edu.demo.domain.Account;
import com.tinkoff.edu.demo.persist.AccountRepository;
import com.tinkoff.edu.demo.persist.ReportSaver;

import java.util.Collection;

public class ReportingService {
    private AccountRepository accountRepository;
    private NumberedDecorator numberedDecorator;
    private StringDecorator stringDecorator;
    private ReportSaver saver;


    public ReportingService(AccountRepository accountRepository, NumberedDecorator numberedDecorator, StringDecorator stringDecorator) {
        this.accountRepository = accountRepository;
        this.numberedDecorator = numberedDecorator;
        this.stringDecorator = stringDecorator;
    }


    public ReportingService(AccountRepository accountRepository, NumberedDecorator numberedDecorator, StringDecorator stringDecorator, ReportSaver saver) {
        this.accountRepository = accountRepository;
        this.numberedDecorator = numberedDecorator;
        this.stringDecorator = stringDecorator;
        this.saver = saver;
    }

    public String getReport(){
        Collection<Account> accounts = accountRepository.getAccounts();
        String[] NumberedAccounts = numberedDecorator.decorate(accounts);

        StringBuilder report = new StringBuilder();
        for (Account currentAccount : accounts) {
            if (currentAccount == null) break;

            String timestampedAccount = stringDecorator.decorate(currentAccount.toString());
            report.append(timestampedAccount).append("\n");
             }
        return report.toString();
    }

    public void saveReport() {
        saver.save(this.getReport());
    }
}
