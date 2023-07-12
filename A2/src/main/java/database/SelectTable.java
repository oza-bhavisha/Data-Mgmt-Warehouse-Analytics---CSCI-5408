package database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The SelectTable class is responsible for retrieving and displaying table information based on the SELECT query.
 */
public class SelectTable {
    private static final String FILE_NAME = "logs/select-table_data.txt";
    private static final String TABLE_DELIMITER = "\n";
    private static final String ROW_DELIMITER = ";";

    /**
     * Retrieves and displays table information based on the given SELECT query.
     *
     * @param query the SELECT query
     */
    public static void selectTable(String query) {
        // Parse and validate the SELECT query using regex
        Pattern pattern = Pattern.compile("select\\s+(\\*|[a-zA-Z_,]+)\\s+from\\s+([a-zA-Z_]+)(?:\\s+where\\s+([a-zA-Z\\d_]+\\s*=\\s*\\d+));?");
        Matcher matcher = pattern.matcher(query);

        if (matcher.matches()) {
            String columns = matcher.group(1);
            String tableName = matcher.group(2);
            String condition = matcher.group(3);

            // Retrieve and display the table information from the custom file format
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
                StringBuilder tableContent = new StringBuilder();
                String line;
                boolean foundTable = false;

                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(TABLE_DELIMITER);
                    foundTable = true;
                    String tableColumns = parts[1];
                    String[] rows = parts[2].split(ROW_DELIMITER);

                    for (String row : rows) {
                        String[] values = row.split(",");
                        if (evaluateCondition(condition, tableColumns, values)) {
                            if (columns.equals("*")) {
                                System.out.println(line);
                            } else {
                                String[] selectedColumns = columns.split(",");
                                StringBuilder selectedValues = new StringBuilder();
                                for (String selectedColumn : selectedColumns) {
                                    int columnIndex = getColumnIndex(selectedColumn.trim(), tableColumns);
                                    selectedValues.append(values[columnIndex].trim()).append(", ");
                                }
                                // Remove trailing comma and space
                                String selectedValuesString = selectedValues.toString().trim();
                                if (selectedValuesString.endsWith(",")) {
                                    selectedValuesString = selectedValuesString.substring(0, selectedValuesString.length() - 1);
                                }
                                System.out.println(parts[0] + TABLE_DELIMITER + parts[1] + TABLE_DELIMITER + selectedValuesString);
                            }
                        }
                    }
                    break;


                }

                if (!foundTable) {
                    System.out.println("Table '" + tableName + "' not found!");
                }

                // Display the updated table content
                System.out.println(tableContent.toString());
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Invalid SELECT query!");
        }
    }

    /**
     * Evaluates the condition based on the table columns and values.
     *
     * @param condition    the condition of the WHERE clause
     * @param tableColumns the columns of the table
     * @param values       the values of the row
     * @return true if the condition is met, false otherwise
     */
    private static boolean evaluateCondition(String condition, String tableColumns, String[] values) {
        // Evaluate the condition based on the table columns and values
        if (condition != null) {
            String[] parts = condition.split("=");
            if (parts.length == 2) {
                String columnName = parts[0].trim();
                String columnValue = parts[1].trim();

                int index = getColumnIndex(columnName, tableColumns);
                if (index >= 0 && index < values.length) {
                    String actualValue = values[index].trim();
                    return actualValue.equals(columnValue);
                }
            }
            return false;
        }
        return true;
    }

    /**
     * Gets the index of the column in the table columns.
     *
     * @param columnName   the name of the column
     * @param tableColumns the columns of the table
     * @return the index of the column in the table columns, or -1 if not found
     */
    private static int getColumnIndex(String columnName, String tableColumns) {
        // Get the index of the column in the table columns
        String[] columns = tableColumns.split(",");
        for (int i = 0; i < columns.length; i++) {
            if (columns[i].trim().equals(columnName)) {
                return i;
            }
        }
        return -1;
    }
}