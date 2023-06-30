package org.example;

import org.flywaydb.core.Flyway;

public class DatabaseInitService {

    public void initDb(String  connectionUrl){
        // Create the Flyway instance and point it to the database
       // String connectionUrl = new Prefs().getString(Prefs.DB_JDBC_CONNECTION_URL);
        Flyway flyway = Flyway
                .configure()
                .dataSource(connectionUrl, null, null)
                .load();

        // Start the migration
        flyway.migrate();
    }
    }

