package com.skypay.service;

import jakarta.transaction.Transaction;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.time.Clock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service

public class Account implements AccountService {
    private final List<Transaction> transactions = new ArrayList<>();
    private final Clock clock;
    private final StatementPrinter printer;
    private int balance = 0;

    public Account(Clock clock, PrintStream out) {
        this.clock   = Objects.requireNonNull(clock);
        this.printer = new StatementPrinter(Objects.requireNonNull(out));
    }

    @Override
    public void deposit(int amount) {
        addTransaction(amount);
    }

    @Override
    public void withdraw(int amount) {
        addTransaction(-amount);
    }

    @Override
    public void printStatement() {
        printer.print(transactions);
    }

    // ---------- Méthode interne commune ------------------------
    private void addTransaction(int rawAmount) {
        if (rawAmount == 0) {
            throw new IllegalArgumentException("Le montant ne peut pas être nul.");
        }
        if (rawAmount < 0 && balance + rawAmount < 0) { // retrait > solde
            throw new IllegalStateException("Solde insuffisant.");
        }

        balance += rawAmount; // Met à jour le solde après le dépôt ou le retrait
        LocalDate date = clock.today();
        transactions.add(new Transaction(date, rawAmount, balance));
    }
}
