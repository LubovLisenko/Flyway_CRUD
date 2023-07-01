package org.example;

import org.flywaydb.core.Flyway;

public class DatabaseInitService {
    public static final String connectionUrl = "jdbc:h2:./test";

   public void initDb(){

        Flyway flyway = Flyway
                .configure()
                .dataSource(connectionUrl, null, null)
                .load();

        // Start the migration
        flyway.migrate();
    }
    }

