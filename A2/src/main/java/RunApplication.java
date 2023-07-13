import authentication.UserAuthenticationModule;
import database.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The RunApplication class is responsible for running the application and handling user queries.
 */
public class RunApplication {
    private static String dataFilePath;
    public static void main(String[] args) {

        String dataFilePath = "logs/data.txt"; // Path to your data file
        String structureFilePath = "logs/structure.txt"; // Path to your structure file

        // Create an instance of the UserAuthenticationModule
        UserAuthenticationModule module = new UserAuthenticationModule();

        // Start the authentication module
        module.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String query;
        boolean exit = false;

        if (module.isAuthenticated()) {
            // Database creation functionality
            boolean databaseCreated = false;

            while (!exit) {
                try {
                    System.out.println("----------------------------------------------------------------------------------");
                    System.out.println("Enter your query in the following format:");
                    System.out.println("Create Database: create database <database_name>;");
                    System.out.println("Create Table: create table <table_name> (<column1>, <column2>, ...);");
                    System.out.println("Insert Data: insert into <table_name> values (<value1>, <value2>, ...);");
                    System.out.println("Select Data: select * from <table_name> where <condition>;");
                    System.out.println("Delete Data: delete from <table_name> where <condition>;");
                    System.out.println("Update Data: update <table_name> set <column_name>=<new_value> where <condition>;");
                    System.out.println("Type 'exit' to exit the application.");
                    System.out.println("----------------------------------------------------------------------------------");
                    query = reader.readLine();

                    if (query.equalsIgnoreCase("exit")) {
                        exit = true;
                    } else if (query.toLowerCase().startsWith("create database")) {
                        if (databaseCreated) {
                            System.out.println("Database already exists!");
                        } else {
                            CreateDatabase.createDatabase(query);
                            databaseCreated = true;
                            System.out.println("Database created successfully!");
                        }
                    } else if (databaseCreated) {
                        if (query.toLowerCase().startsWith("create table")) {
                            CreateTable.createTable(query);
                        } else if (query.toLowerCase().startsWith("select")) {
                            SelectTable.selectTable(query);
                        } else if (query.toLowerCase().startsWith("insert into")) {
                            InsertTable.insertTable(query);
                        } else if (query.toLowerCase().startsWith("update")) {
                            UpdateTable.updateTable(query);
                        } else if (query.toLowerCase().startsWith("delete from")) {
                            DeleteTable.deleteTable(query);
                        } else {
                            System.out.println("Invalid query!");
                        }
                    } else {
                        System.out.println("Please create the database first using the 'CREATE DATABASE' query.");
                    }
                } catch (IOException e) {
                    System.out.println("Error reading input: " + e.getMessage());
                }
            }
        } else {
            System.out.println("Authentication failed. Exiting the application.");
        }

        TransactionHandler transactionHandler = new TransactionHandler(dataFilePath);
        transactionHandler.handleTransaction();

        ERDGenerator erdGenerator = new ERDGenerator(dataFilePath, structureFilePath);
        erdGenerator.generateERD();
    }
}