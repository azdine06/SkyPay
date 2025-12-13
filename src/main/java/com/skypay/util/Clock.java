package com.skypay.util;

import java.time.LocalDate;

public interface Clock {
    LocalDate today();
    Clock SYSTEM = LocalDate::now;
}
