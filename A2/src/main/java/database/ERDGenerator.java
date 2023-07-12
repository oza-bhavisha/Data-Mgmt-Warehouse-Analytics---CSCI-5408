package database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ERDGenerator {
    private String dataFilePath;
    private String structureFilePath;

    public ERDGenerator(String dataFilePath, String structureFilePath) {
        this.dataFilePath = dataFilePath;
        this.structureFilePath = structureFilePath;
    }

    public void generateERD() {
        Map<String, List<String>> tableColumns = readTableColumns();
        Map<String, List<String>> tableData = readTableData();

        System.out.println("--------------------------------------------------");
        System.out.println("Entity-Relationship Diagram");
        System.out.println("--------------------------------------------------");

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

    private List<String> getPrimaryKeys(List<String> columns) {
        List<String> primaryKeys = new ArrayList<>();
        for (String column : columns) {
            if (column.endsWith("Pk")) {
                primaryKeys.add(column);
            }
        }
        return primaryKeys;
    }

    private List<String> readRelationships() {
        List<String> relationships = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(structureFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("ALTER TABLE") && line.contains("FOREIGN KEY")) {
                    String relationship = line.replaceAll("ALTER TABLE `(\\w+)`.*REFERENCES `(\\w+)`.*", "$1 -> $2");
                    relationships.add(relationship);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading structure file: " + e.getMessage());
        }

        return relationships;
    }
}