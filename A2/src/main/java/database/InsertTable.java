package database;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The InsertTable class is responsible for inserting values into a table in a database.
 */
public class InsertTable {
    private static final String FILE_NAME = "logs/insert-table_data.txt";
    private static final String DELIMITER = "\n";

    /**
     * Inserts values into a table based on the given query.
     *
     * @param query the INSERT query
     */
    public static void insertTable(String query) {
        // Parse and validate the INSERT query using regex
        Pattern pattern = Pattern.compile("insert\\s+into\\s+(\\w+)\\s+values\\s*\\((.+)\\)\\s*;\\s*");
        Matcher matcher = pattern.matcher(query);

        if (matcher.matches()) {
            String tableName = matcher.group(1);
            String values = matcher.group(2);

            // Store the inserted values in the custom file format
            try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, true))) {
                writer.print(DELIMITER + values);
                System.out.println("Data inserted successfully!");
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Invalid INSERT query!");
        }
    }
}