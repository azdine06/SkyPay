package com.skypay.service;

import com.skypay.util.Clock;
import com.skypay.util.StatementPrinter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Acceptance tests for the Account class based on the exercise specification.
 * 
 * Given a client makes a deposit of 1000 on 10-01-2012
 * And a deposit of 2000 on 13-01-2012
 * And a withdrawal of 500 on 14-01-2012
 * When they print their bank statement
 * Then they would see:
 *   Date       || Amount || Balance
 *   14/01/2012 || -500   || 2500
 *   13/01/2012 || 2000   || 3000
 *   10/01/2012 || 1000   || 1000
 */
class AccountTest {

    private ByteArrayOutputStream outputStream;
    private Account account;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        StatementPrinter printer = new StatementPrinter(new PrintStream(outputStream));
        // Use a controllable clock for testing
        account = new Account(createFixedClock(LocalDate.of(2012, 1, 10)), printer);
    }

    @Test
    void should_print_statement_with_all_transactions_in_reverse_order() {
        // Given - Using a test helper that allows changing dates
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        StatementPrinter printer = new StatementPrinter(new PrintStream(out));
        
        // Create account with mutable clock for testing
        TestClock clock = new TestClock(LocalDate.of(2012, 1, 10));
        Account acc = new Account(clock, printer);
        
        // When - Perform transactions on different dates
        clock.setDate(LocalDate.of(2012, 1, 10));
        acc.deposit(1000);
        
        clock.setDate(LocalDate.of(2012, 1, 13));
        acc.deposit(2000);
        
        clock.setDate(LocalDate.of(2012, 1, 14));
        acc.withdraw(500);
        
        // Then - Print statement
        acc.printStatement();
        
        String output = out.toString();
        assertThat(output).contains("Date || Amount || Balance");
        assertThat(output).contains("14/01/2012 || -500 || 2500");
        assertThat(output).contains("13/01/2012 || 2000 || 3000");
        assertThat(output).contains("10/01/2012 || 1000 || 1000");
    }

    @Test
    void should_reject_zero_deposit() {
        assertThatThrownBy(() -> account.deposit(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("montant");
    }

    @Test
    void should_reject_zero_withdrawal() {
        assertThatThrownBy(() -> account.withdraw(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("montant");
    }

    @Test
    void should_reject_withdrawal_exceeding_balance() {
        account.deposit(100);
        
        assertThatThrownBy(() -> account.withdraw(200))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("insuffisant");
    }

    @Test
    void should_allow_withdrawal_equal_to_balance() {
        account.deposit(100);
        account.withdraw(100); // Should not throw
        
        account.printStatement();
        String output = outputStream.toString();
        assertThat(output).contains("|| 0"); // Balance should be 0
    }

    // Helper method to create a fixed clock
    private Clock createFixedClock(LocalDate date) {
        return () -> date;
    }

    // Test helper class for mutable clock
    private static class TestClock implements Clock {
        private LocalDate date;

        TestClock(LocalDate date) {
            this.date = date;
        }

        void setDate(LocalDate date) {
            this.date = date;
        }

        @Override
        public LocalDate today() {
            return date;
        }
    }
}

