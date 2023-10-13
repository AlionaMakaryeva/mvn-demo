package com.tinkoff.edu.demo;

import org.junit.jupiter.api.AssertionFailureBuilder;

import java.sql.Connection;
import java.sql.SQLException;

public class DbClientBuilder {
    private String login;
    private String secret;
    private String salt;
    private Connection connection;

    public DbClientBuilder(Connection connection) {
        this.connection = connection;
    }

    public DbClientBuilder withLogin(String login) {
        this.login = login;
        return this;
    }

    public DbClientBuilder withSecret(String secret) {
        this.secret = secret;
        return this;
    }

    public DbClientBuilder withSalt(String salt) {
        this.salt = salt;
        return this;
    }

    public void build() throws SQLException {
        var insert = connection.prepareStatement(
                "INSERT INTO public.client(LOGIN, SECRET, SALT) " +
                        "values (?, 'sdfs', 'fsdf')");
        insert.setString(1, "123");
        insert.executeUpdate();
    }
}
