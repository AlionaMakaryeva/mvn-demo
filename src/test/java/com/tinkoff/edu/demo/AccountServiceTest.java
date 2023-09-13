package com.tinkoff.edu.demo;

import com.tinkoff.edu.demo.domain.Account;
import com.tinkoff.edu.demo.persist.MemoryAccountRepository;
import com.tinkoff.edu.demo.persist.NotEnoughSpaceException;
import com.tinkoff.edu.demo.service.AccountService;
import com.tinkoff.edu.demo.service.NumberedDecorator;
import com.tinkoff.edu.demo.service.ReportingService;

import java.time.Instant;

import static com.tinkoff.edu.demo.domain.DepositAccount.of;

public class AccountServiceTest {
    public static void main(String[] args) throws NotEnoughSpaceException {
        MemoryAccountRepository accountRepository = new MemoryAccountRepository();
        AccountService accountService = new AccountService(accountRepository);
        accountService.addClient(100., 20., 50., 60.);

        Account account = of(100.); //factory method
        accountService.addAccount(account);

        System.out.println(accountRepository.getAccountCount());
        System.out.println(accountRepository.getMeanAmount());
        System.out.println(account.toString());

        ReportingService reportingService = new ReportingService(
                accountRepository,
                new NumberedDecorator(),
                s -> Instant.now() + " " + s );

        System.out.println(reportingService.getReport());

        try {
            accountService.addAccount(null);
            System.out.println("FAIL");
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }
}
