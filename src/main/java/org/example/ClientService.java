package org.example;

import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ClientService {

        private PreparedStatement createSt;
        private PreparedStatement getById;
        private PreparedStatement selectMaxIDSt;
        private PreparedStatement setName;
        private PreparedStatement deleteById;

        public ClientService(Connection connection) throws SQLException {
            createSt = connection
                    .prepareStatement("INSERT INTO client (name) VALUES (?)");

            getById = connection
                    .prepareStatement("SELECT name FROM client WHERE id = ?");
            selectMaxIDSt = connection.prepareStatement("SELECT max(id) AS maxID FROM client");

            setName = connection.prepareStatement("UPDATE client SET name=?  WHERE id = ?");
            deleteById = connection.prepareStatement("DELETE FROM client WHERE id = ?");

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
//            readSt.setLong(1, id);
//            ResultSet rs = readSt.executeQuery();
//
//            if (!rs.next()) {
//                return null;
//            }
//
//            String name = rs.getString("name");
//
//
//            Client result = new Client();
//            result.setName(name);
//            result.setId(id);
//
//            return result;
            return null;
        }
    }

