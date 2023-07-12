package database;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The DeleteTable class is responsible for deleting rows from a table in a database.
 */
public class DeleteTable {

    private static final String FILE_NAME = "delete-table_data.txt";
    private static final String DELIMITER = "\n";

    /**
     * Deletes rows from a table based on the given query.
     *
     * @param query the DELETE query
     */
    public static void deleteTable(String query) {
        // Parse and validate the DELETE query using regex
        Pattern pattern = Pattern.compile("DELETE FROM ([a-zA-Z0-9_]+) WHERE (.+)");
        Matcher matcher = pattern.matcher(query);

        if (matcher.matches()) {
            String tableName = matcher.group(1);
            String whereClause = matcher.group(2);

            // Delete the matching rows from the custom file format
            try {
                File inputFile = new File(FILE_NAME);
                File tempFile = new File("temp_table_data.txt");

                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                PrintWriter writer = new PrintWriter(new FileWriter(tempFile));

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(DELIMITER);
                    if (parts.length >= 2 && parts[0].equals(tableName)) {
                        String[] columns = parts[1].split(",");
                        String[] values = parts[2].split(",");

                        // Skip the row if it matches the WHERE clause
                        if (!matchesWhereClause(whereClause, columns, values)) {
                            writer.println(parts[0] + DELIMITER + String.join(",", columns) + DELIMITER + String.join(",", values));
                        }
                    } else {
                        writer.println(line);
                    }
                }

                writer.close();
                reader.close();

                // Replace the original file with the updated data
                if (inputFile.delete()) {
                    if (!tempFile.renameTo(inputFile)) {
                        System.out.println("Error deleting table data!");
                    } else {
                        System.out.println("Data deleted successfully!");
                    }
                } else {
                    System.out.println("Error deleting table data!");
                }
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Invalid DELETE query!");
        }
    }

    /**
     * Checks if the WHERE clause matches the row.
     *
     * @param whereClause the WHERE clause of the DELETE query
     * @param columns     the columns of the table
     * @param values      the values of the row
     * @return true if the WHERE clause matches the row, false otherwise
     */
    private static boolean matchesWhereClause(String whereClause, String[] columns, String[] values) {
        // Check if the WHERE clause matches the row
        String[] conditions = whereClause.split("AND");
        for (String condition : conditions) {
            String[] parts = condition.split("=");
            if (parts.length == 2) {
                String columnName = parts[0].trim();
                String columnValue = parts[1].trim();

                int index = getColumnIndex(columnName, columns);
                if (index >= 0 && index < values.length && values[index].equals(columnValue)) {
                    continue;
                }
            }
            return false;
        }
        return true;
    }

    /**
     * Gets the index of a column in the array.
     *
     * @param columnName the name of the column
     * @param columns    the array of column names
     * @return the index of the column in the array, or -1 if not found
     */
    private static int getColumnIndex(String columnName, String[] columns) {
        // Get the index of a column in the array
        for (int i = 0; i < columns.length; i++) {
            if (columns[i].trim().equals(columnName)) {
                return i;
            }
        }
        return -1;
    }
}