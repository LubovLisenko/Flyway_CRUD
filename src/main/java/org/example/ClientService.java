package org.example;

import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ClientService {

    private PreparedStatement createSt;
    private PreparedStatement getById;
    private PreparedStatement selectMaxIDSt;
    private PreparedStatement updateSt;
    private PreparedStatement deleteById;
    private PreparedStatement listAll;

    public ClientService(Connection connection) throws SQLException {
        createSt = connection
                .prepareStatement("INSERT INTO client (name) VALUES (?)");

        getById = connection
                .prepareStatement("SELECT name FROM client WHERE id = ?");
        selectMaxIDSt = connection.prepareStatement("SELECT max(id) AS maxID FROM client");

        updateSt = connection.prepareStatement("UPDATE client SET name = ?  WHERE id = ?");
        deleteById = connection.prepareStatement("DELETE FROM client WHERE id = ?");
        listAll = connection.prepareStatement("SELECT id, name FROM client");

    }

    public long create(Client client) throws SQLException {
        createSt.setString(1, client.getName());
        createSt.executeUpdate();

        long id;
        try (ResultSet rs = selectMaxIDSt.executeQuery()) {
            rs.next();
            id = rs.getLong("maxId");
        }
        return id;
    }

    public Client getById(long id) throws SQLException {
        getById.setLong(1, id);
        try (ResultSet rs = getById.executeQuery()) {
            if (!rs.next()) {
                return null;
            }

            Client result = new Client();
            result.setId(id);
            result.setName(rs.getString("name"));
            return result;
        }
    }

    public void deleteById(long id) throws SQLException {
        deleteById.setLong(1, id);
        deleteById.executeUpdate();

    }

    public void setName(Client client) throws SQLException {
        updateSt.setString(1, client.getName());
        updateSt.setLong(2, client.getId());
        updateSt.executeUpdate();


    }

    public List<Client> listAll() throws SQLException {

        try (ResultSet rs = listAll.executeQuery()) {
            List<Client> result = new ArrayList<>();
            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getLong("id"));
                client.setName(rs.getString("name"));
                result.add(client);
            }

            return result;
        }

    }
}



