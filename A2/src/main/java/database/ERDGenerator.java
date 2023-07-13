package database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The ERDGenerator class generates an Entity-Relationship Diagram (ERD) based on the data and structure files.
 */
public class ERDGenerator {
    private String dataFilePath;
    private String structureFilePath;

    /**
     * Constructor for ERDGenerator object with the specified data and structure file paths.
     *
     * @param dataFilePath      The path to the data file.
     * @param structureFilePath The path to the structure file.
     */
    public ERDGenerator(String dataFilePath, String structureFilePath) {
        this.dataFilePath = dataFilePath;
        this.structureFilePath = structureFilePath;
    }

    /**
     * Generates the Entity-Relationship Diagram (ERD) based on the data and structure files.
     */
    public void generateERD() {
        // Read table columns and table data
        Map<String, List<String>> tableColumns = readTableColumns();
        Map<String, List<String>> tableData = readTableData();

        // Print the header for the ERD
        System.out.println("--------------------------------------------------");
        System.out.println("Entity-Relationship Diagram");
        System.out.println("--------------------------------------------------");

        // Print entity representation for each table
        for (String tableName : tableColumns.keySet()) {
            System.out.println(tableName);
            List<String> columns = tableColumns.get(tableName);
            List<String> data = tableData.get(tableName);
            List<String> primaryKeys = getPrimaryKeys(columns);

            // Print entity representation
            System.out.println("Entity: " + tableName);
            System.out.println("| " + String.join(" | ", columns) + " |");
            System.out.println("| " + String.join(" | ", primaryKeys) + " |");

            // Print data
            if (data != null && !data.isEmpty()) {
                System.out.println("Data:");
                for (String row : data) {
                    System.out.println("| " + row + " |");
                }
            }
            System.out.println();
        }

        // Print relationships
        List<String> relationships = readRelationships();
        if (!relationships.isEmpty()) {
            System.out.println("Relationships:");
            for (String relationship : relationships) {
                System.out.println(relationship);
            }
        } else {
            System.out.println("No relationships found.");
        }
        System.out.println("--------------------------------------------------");
    }

    /**
     * Reads the table columns from the structure file.
     *
     * @return A map containing table names as keys and lists of column names as values.
     */
    private Map<String, List<String>> readTableColumns() {
        Map<String, List<String>> tableColumns = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(structureFilePath))) {
            String line;
            String currentTable = null;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("CREATE TABLE")) {
                    currentTable = line.replaceAll("CREATE TABLE (\\w+) \\(.*\\);", "$1");
                    tableColumns.put(currentTable, new ArrayList<>());
                } else if (line.startsWith("`") && currentTable != null) {
                    String columnName = line.replaceAll("`(\\w+)`.*", "$1");
                    tableColumns.get(currentTable).add(columnName);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading structure file: " + e.getMessage());
        }

        return tableColumns;
    }

    /**
     * Reads the table data from the data file.
     *
     * @return A map containing table names as keys and lists of data rows as values.
     */
    private Map<String, List<String>> readTableData() {
        Map<String, List<String>> tableData = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(dataFilePath))) {
            String line;
            String currentTable = null;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("INSERT INTO")) {
                    currentTable = line.replaceAll("INSERT INTO `(\\w+)`.*", "$1");
                    tableData.put(currentTable, new ArrayList<>());
                } else if (line.startsWith("(") && currentTable != null) {
                    String rowData = line.replaceAll("\\((.*)\\),", "$1");
                    tableData.get(currentTable).add(rowData);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading data file: " + e.getMessage());
        }

        return tableData;
    }

    /**
     * Retrieves the primary keys from a list of columns.
     *
     * @param columns The list of column names.
     * @return A list of primary key column names.
     */
    private List<String> getPrimaryKeys(List<String> columns) {
        List<String> primaryKeys = new ArrayList<>();
        for (String column : columns) {
            if (column.endsWith("Pk")) {
                primaryKeys.add(column);
            }
        }
        return primaryKeys;
    }

    /**
     * Reads the relationships between tables from the structure file.
     *
     * @return A list of relationships in the format "Table1 -> Table2".
     */
    private List<String> readRelationships() {
        List<String> relationships = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(structureFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("ALTER TABLE") && line.contains("FOREIGN KEY")) {
                    Pattern pattern;
                    pattern = Pattern.compile("ALTER TABLE `(\\w+)`.*REFERENCES `(\\w+)`.*");
                    Matcher matcher = pattern.matcher(line);
                    if (matcher.find()) {
                        String relationship = matcher.group(1) + " -> " + matcher.group(2);
                        relationships.add(relationship);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading structure file: " + e.getMessage());
        }

        return relationships;
    }
}