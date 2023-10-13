package com.tinkoff.edu.demo;

import org.junit.jupiter.api.Test;

import java.sql.DriverManager;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class JdbcDemoTest {
    @Test
    public void testFixture() throws SQLException {

        try (var connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost/dbo-db",
                "user", "qwerty123"
        )) {


            //region Given


            new DbClientBuilder(connection)
                    .withLogin("123")
                    .withSecret("")
                    .withSalt("")
                    .build();
            //endregion

            //region When
            //endregion

            //region Then
            new ClientSelector(connection)
                    .forLogin()
                    .forId()
                    .select();

            var delete = connection.createStatement();
            delete.executeUpdate("delete from client where login = '123'");
        }
        //endregion

    }
}
