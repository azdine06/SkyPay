package com.skypay.config;

import com.skypay.util.Clock;
import com.skypay.util.StatementPrinter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class providing beans for the Account service.
 */
@Configuration
public class AccountConfig {

    /**
     * Provides a Clock bean using the system clock.
     */
    @Bean
    public Clock clock() {
        return Clock.SYSTEM;
    }

    /**
     * Provides a StatementPrinter bean that prints to System.out.
     */
    @Bean
    public StatementPrinter statementPrinter() {
        return new StatementPrinter(System.out);
    }
}
