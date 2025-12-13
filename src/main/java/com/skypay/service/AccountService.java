package com.skypay.service;

import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    void deposit(int amount);
    void withdraw(int amount);
    void printStatement();
}
