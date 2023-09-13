package com.tinkoff.edu.demo.service;

import com.tinkoff.edu.demo.domain.Account;
@FunctionalInterface
public interface StringDecorator {
    String decorate(String currentAccount);
}
