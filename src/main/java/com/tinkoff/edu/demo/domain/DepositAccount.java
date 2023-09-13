package com.tinkoff.edu.demo.domain;


/**
 * Data objects, DTO
 */
public final class DepositAccount extends AbstractAccount { // -> objects


    public DepositAccount(double amount) {
        super(amount);
        if (amount < 0) throw new RuntimeException("Negative amount for deposit");
    }

    public DepositAccount() {
       this(100.);
    }

    /**
     * factory method
     */
    public static DepositAccount of(double amount) {
        //db, file
        return new DepositAccount(100.);
    }

    @Override
    public final String toString() {
        return "DepositAccount" + super.toString();
    }
}
