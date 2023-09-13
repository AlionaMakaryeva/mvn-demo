package com.tinkoff.edu.demo.domain;

import java.util.Objects;

public abstract class AbstractAccount  implements Account {
    private double amount; // == 0.0

    public AbstractAccount(double amount) {
        this.amount = amount;
    }

    public AbstractAccount() {
        this(100.);
    }

    public double getAmount() {

        return amount;
    }

    @Override
    public String toString() {
        return ", " + this.getAmount();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractAccount that = (AbstractAccount) o;
        return Double.compare(that.amount, amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
