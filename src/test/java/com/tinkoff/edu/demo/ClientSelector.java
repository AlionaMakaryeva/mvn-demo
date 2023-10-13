package com.tinkoff.edu.demo;

import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class ClientSelector {
    private Connection connection;
    public ClientSelector (Connection connection) {
        this.connection = connection;
    }

    public ClientSelector forLogin() {
        return this;
    }

    public ClientSelector forId() {
        return this;
    }

    public void select() throws SQLException {
        var select = connection.prepareStatement("select * from public.client where login = '123'");
        var records = select.executeQuery();
        while (records.next()) {
            assertThat(records.getLong("ID")).isGreaterThanOrEqualTo(0);
            assertThat(records.getString("LOGIN")).isEqualTo("123");
            assertThat(records.getBoolean("ENABLED")).isTrue();
        }
    }
}
