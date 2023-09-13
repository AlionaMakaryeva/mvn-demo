package com.tinkoff.edu.demo.domain;


/**
 * Data objects, DTO
 */
public class CreditAccount extends AbstractAccount { // -> objects


    public CreditAccount(double amount) {
       super(amount);
    }

    public CreditAccount() {
        this(100.);
    }

    /**
     * factory method
     */
    public static CreditAccount of(double amount) {
        //db, file
        return new CreditAccount(100.);
    }

    @Override
    public String toString() {
        return "CreditAccount" + super.toString();
    }


}
