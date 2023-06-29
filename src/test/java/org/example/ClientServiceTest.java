package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ClientServiceTest {

    private Connection connection;
    private ClientService service;
    @BeforeEach
    public void beforeEach() throws SQLException {
        connection = DriverManager.getConnection("jdbc:h2:mem:testdb");
        service = new ClientService(connection);
    }
    // create client with all field
    @Test
    public void testThatClientCreatedCorrectly() throws SQLException {
        Client original = new Client();
        original.setName("TestName");

        long id = service.create(original);
        Client saved = service.getById(id);

        Assertions.assertEquals(id, saved.getId());
        Assertions.assertEquals(original.getName(), saved.getName());


    }
    @AfterEach
    public void afterEach() throws SQLException {
        connection.close();
    }

}