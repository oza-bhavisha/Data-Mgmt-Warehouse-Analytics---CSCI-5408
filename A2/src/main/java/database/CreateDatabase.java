package database;


import java.io.*;

/**
 * The CreateDatabase class is responsible for creating a database.
 */
public class CreateDatabase {
    private static final String DATABASE_FILE = "logs/create-database.txt";

    /**
     * Creates a database based on the given query.
     *
     * @param query the CREATE DATABASE query
     */
    public static void createDatabase(String query) {
        // Parse and validate the CREATE DATABASE query
        String databaseName = parseDatabaseName(query);

        if (databaseName != null) {
            // Save the database name to a text file
            try (PrintWriter writer = new PrintWriter(new FileWriter(DATABASE_FILE))) {
                writer.println(databaseName);
                System.out.println("Database created: " + databaseName);
            } catch (IOException e) {
                System.out.println("Error creating the database!");
            }
        } else {
            System.out.println("Invalid CREATE DATABASE query!");
        }
    }

    /**
     * Parses the database name from the CREATE DATABASE query.
     *
     * @param query the CREATE DATABASE query
     * @return the database name, or null if not found or invalid query
     */
    private static String parseDatabaseName(String query) {
        String[] parts = query.split(" ");

        if (parts.length >= 3 && parts[0].equalsIgnoreCase("create") && parts[1].equalsIgnoreCase("database")) {
            return parts[2];
        }

        return null;
    }
}