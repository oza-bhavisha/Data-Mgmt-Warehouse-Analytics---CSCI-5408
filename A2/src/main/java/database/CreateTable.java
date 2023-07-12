package database;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The CreateTable class is responsible for creating tables in a database.
 */
public class CreateTable {
    private static final String FILE_NAME = "logs/create-table_data.txt";
    private static final String DELIMITER = "\n";

    /**
     * Creates a table based on the given query.
     *
     * @param query the CREATE TABLE query
     */
    public static void createTable(String query) {
        // Parse and validate the CREATE TABLE query using regex
        Pattern pattern = Pattern.compile("create\\s+table\\s+(\\w+)\\s+\\((.+)\\);?");
        Matcher matcher = pattern.matcher(query);

        if (matcher.matches()) {
            String tableName = matcher.group(1);
            String columns = matcher.group(2);

            // Store the table information in the custom file format
            try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, true))) {
                writer.println(tableName + DELIMITER + columns);
                writer.flush(); // Flush the PrintWriter to ensure the data is written immediately
                System.out.println("Table created successfully!");
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Invalid CREATE TABLE query!");
        }
    }
}