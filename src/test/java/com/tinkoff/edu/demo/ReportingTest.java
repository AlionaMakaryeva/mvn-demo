package com.tinkoff.edu.demo;

import com.tinkoff.edu.demo.domain.DepositAccount;
import com.tinkoff.edu.demo.persist.MemoryAccountRepository;
import com.tinkoff.edu.demo.persist.NotEnoughSpaceException;
import com.tinkoff.edu.demo.persist.ReportSaver;
import com.tinkoff.edu.demo.service.NumberedDecorator;
import com.tinkoff.edu.demo.service.ReportingService;
import com.tinkoff.edu.demo.service.StringDecorator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ReportingTest {
    private NumberedDecorator numDecorator;
    private MemoryAccountRepository accountRepo;
    private ReportingService sut;
    private StringDecorator stringDecorator;

    @BeforeEach
    public void setUpReporting() {
        numDecorator = new NumberedDecorator();
        accountRepo = new MemoryAccountRepository();
        stringDecorator = s -> Instant.now() + " " + s;

    }

    @Test
    public void shouldGetReportWhenNoAccounts() {

        sut = new ReportingService(
                accountRepo,
                numDecorator,
                stringDecorator
        );
        assertThat(sut.getReport())
                .contains("");
    }

    @Test
    public void shouldGetReportWhenAccountExists() throws NotEnoughSpaceException {
        accountRepo.addAccount(new DepositAccount(50.));
        var stubDecorator = mock(StringDecorator.class); //test doubler
        when(stubDecorator.decorate(anyString()))
                .thenAnswer(par -> "decorated " + par.getArgument(0));
        sut = new ReportingService(
                accountRepo,
                numDecorator,
                stubDecorator
        );
        assertThat(sut.getReport())
                .contains("decorated ")
                .contains("DepositAccount")
                .contains("50.");
    }

    @Test
    public void shouldSaveReportWhenAccountExists() throws NotEnoughSpaceException {
        accountRepo.addAccount(new DepositAccount(50.));
        var stubDecorator = mock(StringDecorator.class); //test doubler
        when(stubDecorator.decorate(anyString()))
                .thenAnswer(par -> "decorated " + par.getArgument(0));


        var mockSaver = mock(ReportSaver.class);
        sut = new ReportingService(
                accountRepo,
                numDecorator,
                stubDecorator,
                mockSaver

        );

        sut.saveReport();
        verify(mockSaver).save("decorated DepositAccount, 50.0\n");
    }
}
