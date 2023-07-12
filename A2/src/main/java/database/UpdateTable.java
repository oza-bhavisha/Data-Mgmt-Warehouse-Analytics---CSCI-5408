package database;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The UpdateTable class is responsible for updating data in a table in a database.
 */
public class UpdateTable {
    private static final String FILE_NAME = "logs/update-table_data.txt";
    private static final String DELIMITER = "\n";

    /**
     * Updates the data in a table based on the given query.
     *
     * @param query the UPDATE query
     */
    public static void updateTable(String query) {
        // Parse and validate the UPDATE query using regex
        Pattern pattern = Pattern.compile("update\\s+(\\w+)\\s+set\\s+(\\w+)\\s*=\\s*(\\w+)\\s+where\\s+(\\w+)\\s*=\\s*(\\w+)\\s*;");
        Matcher matcher = pattern.matcher(query);

        if (matcher.matches()) {
            String tableName = matcher.group(1);
            String setClause = matcher.group(2);
            String whereClause = matcher.group(3);

            // Update the data in the custom file format
            try {
                File file = new File(System.getProperty("user.dir") + File.separator + FILE_NAME);
                if (!file.exists()) {
                    file.createNewFile();
                }

                BufferedReader reader = new BufferedReader(new FileReader(file));
                StringBuilder fileContent = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(DELIMITER);
                    if (parts.length >= 2 && parts[0].equals(tableName)) {
                        String[] columns = parts[1].split(",");
                        String[] values = parts[2].split(",");

                        // Update the matching row based on the WHERE clause
                        if (matchesWhereClause(whereClause, columns, values)) {
                            updateValues(setClause, columns, values);
                        }
                        line = parts[0] + DELIMITER + String.join(",", columns) + DELIMITER + String.join(",", values);
                    }
                    fileContent.append(line).append("\n");
                }

                // Write the updated content back to the file
                try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                    writer.print(fileContent.toString());
                }

                System.out.println("Data updated successfully!");
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Invalid UPDATE query!");
        }
    }

    /**
     * Checks if the WHERE clause matches the row.
     *
     * @param whereClause the WHERE clause of the UPDATE query
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
     * Updates the values based on the SET clause.
     *
     * @param setClause the SET clause of the UPDATE query
     * @param columns   the columns of the table
     * @param values    the values of the row
     */
    private static void updateValues(String setClause, String[] columns, String[] values) {
        // Update the values based on the SET clause
        String[] updates = setClause.split(",");
        for (String update : updates) {
            String[] parts = update.split("=");
            if (parts.length == 2) {
                String columnName = parts[0].trim();
                String columnValue = parts[1].trim();

                int index = getColumnIndex(columnName, columns);
                if (index >= 0 && index < values.length) {
                    values[index] = columnValue;
                }
            }
        }
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