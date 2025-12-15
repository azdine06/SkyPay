package com.skypay.util;

import com.skypay.model.Transaction;

import java.io.PrintStream;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class StatementPrinter {
    private static final String HEADER = "Date || Amount || Balance";
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final PrintStream out;

    public StatementPrinter(PrintStream out) {
        this.out = out;
    }

    public void print(List<Transaction> txs) {
        out.println(HEADER);
        for (int i = txs.size() - 1; i >= 0; i--) {
            Transaction t = txs.get(i);
            out.printf("%s || %d || %d%n",
                    t.date().format(FMT),
                    t.amount(),
                    t.balance());
        }
    }
}
