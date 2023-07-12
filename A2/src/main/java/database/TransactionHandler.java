package database;

import java.io.*;
import java.util.*;

/**
 * Utility class for handling database transactions.
 */
public class TransactionHandler {
    private final List<String> queries;
    private final String dataFilePath;

    /**
     * Constructor for a TransactionHandler object with the specified data file path.
     *
     * @param dataFilePath the path to the data file
     */
    public TransactionHandler(String dataFilePath) {
        this.dataFilePath = dataFilePath;
        this.queries = new ArrayList<>();
    }

    /**
     * Handles the database transaction.
     */
    public void handleTransaction() {
        Scanner scanner = new Scanner(System.in);
        boolean inTransaction = false;
        boolean exitTransaction = false;

        while (true) {
            displayMenu();
            System.out.print("Enter your query: ");
            String query = scanner.nextLine();

            if (query.equalsIgnoreCase("begin transaction")) {
                if (inTransaction) {
                    System.out.println("A transaction is already in progress.");
                } else {
                    inTransaction = true;
                    System.out.println("Transaction started.");
                }
            } else if (query.equalsIgnoreCase("end transaction")) {
                if (!inTransaction) {
                    System.out.println("No transaction in progress.");
                } else {
                    inTransaction = false;
                    executeQueries();

                    if (queries.isEmpty()) {
                        exitTransaction = true;
                    }

                    if (exitTransaction) {
                        System.out.println("Transaction committed.");
                        break;
                    } else {
                        System.out.println("Transaction executed. Continuing the transaction.");
                    }
                }
            } else if (query.equalsIgnoreCase("rollback")) {
                if (!inTransaction) {
                    System.out.println("No transaction in progress.");
                } else {
                    inTransaction = false;
                    queries.clear();
                    System.out.println("Transaction rolled back.");
                }
            } else if (inTransaction) {
                queries.add(query);
                System.out.println("Query added to transaction.");
            } else {
                System.out.println("You're not in a transaction. Use 'begin transaction' to start a new transaction.");
            }
        }
    }

    /**
     * Executes the queries in the current transaction and writes the response to the data file.
     */
    private void executeQueries() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataFilePath, true))) {
            for (String query : queries) {
                // Process the query and update/delete/insert in the data file and Write the response to the data file
                String r = "-----------------------------------------";
                String s = "\n";
                String response = "Response for Transaction: " + query;
                writer.write(r);
                writer.write(s);
                writer.write(response);
                writer.newLine();
            }
            queries.clear();

            System.out.println("Queries executed successfully.");

            if (queries.isEmpty()) {
                boolean exitTransaction = true;
            }
        } catch (IOException e) {
            System.out.println("Error executing queries: " + e.getMessage());
        }
    }

    /**
     * Displays the transaction menu.
     */
    private void displayMenu() {
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("Enter your query or command:");
        System.out.println("Begin Transaction: begin transaction");
        System.out.println("End Transaction: end transaction");
        System.out.println("Commit: commit");
        System.out.println("Rollback: rollback");
        System.out.println("----------------------------------------------------------------------------------");
    }
}