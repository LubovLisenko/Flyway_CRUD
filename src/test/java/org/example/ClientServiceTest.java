package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientServiceTest {

    private Connection connection;
    private ClientService service;
    @BeforeEach
    public void beforeEach() throws SQLException {
        final String connectionUrl = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
        new DatabaseInitService().initDb(connectionUrl);

        connection = DriverManager.getConnection(connectionUrl);
        service = new ClientService(connection);
    }
    // create client with all field
    @Test
    public void testThatClientCreatedCorrectly() throws SQLException {
        List<Client> originalClient = new ArrayList<>();


        Client fullOriginalClient = new Client();
        fullOriginalClient.setName("TestName");
        originalClient.add(fullOriginalClient);

        long id = service.create(fullOriginalClient);
        Client saved = service.getById(id);
        //List<Client> clientList = service.listAll();

        Assertions.assertEquals(id, saved.getId());
        Assertions.assertEquals(fullOriginalClient.getName(), saved.getName());


    }
    @AfterEach
    public void afterEach() throws SQLException {
        connection.close();
    }

    @Test
    public void listAllTest() throws SQLException {
        Client expected= new Client();
        expected.setName("TestName");
       long id = service.create(expected);
       expected.setId(id);
       List<Client> expectedClient = Collections.singletonList(expected);
       List<Client> actualClient = service.listAll();
       Assertions.assertEquals(expectedClient, actualClient);
    }

    @Test
    public void setNameTest() throws SQLException {

        Client original= new Client();
        original.setName("TestName");
        long id = service.create(original);
        original.setId(id);

        original.setName("NewName");
        service.setName(original);
        Client updated = service.getById(id);

        Assertions.assertEquals(id, updated.getId());
        Assertions.assertEquals("NewName", updated.getName());

    }
    @Test
    public void testDelete() throws SQLException {
        Client original= new Client();
        original.setName("TestName");
        long id = service.create(original);
        service.deleteById(id);
        Assertions.assertNull(service.getById(id));


    }

}