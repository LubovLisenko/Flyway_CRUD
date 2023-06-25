package org.example;

public class Main {
    public static void main(String[] args) {
        Database database = Database.getInstance();
        new DatabaseInitService().initDb(database);

    }
}