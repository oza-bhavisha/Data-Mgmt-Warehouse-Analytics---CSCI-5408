import java.sql.*;

public class GCPLab4RunApplication {
    public static void main(String[] args) {
        try {
            // Establish a connection to the GCP database
            Connection connection = DBConnection.getRemoteDBConnection();

            Statement statement = connection.createStatement();

            // Execute a SELECT query to fetch item details from the inventory table
            String sqlQuery = "SELECT * FROM inventory";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            // Process the result set
            while (resultSet.next()) {
                int itemId = resultSet.getInt("item_id");
                String itemName = resultSet.getString("item_name");
                int availableQuantity = resultSet.getInt("available_quantity");

                // Print the item details
                System.out.println("Item ID: " + itemId);
                System.out.println("Item Name: " + itemName);
                System.out.println("Available Quantity: " + availableQuantity);
                System.out.println("--------------------");
            }

            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();

            createOrderTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void createOrderTable() {
        try {
            // Establish a connection to the local database
            Connection connection = DBConnection.getLocalDBConnection();

            // Create a statement object to execute SQL queries
            Statement statement = connection.createStatement();

            // Execute a CREATE TABLE query to create the order_info table
            String createTableQuery = "CREATE TABLE orders ("
                    + "order_id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "user_id INT,"
                    + "item_name VARCHAR(255),"
                    + "quantity INT,"
                    + "order_date DATE)";
            statement.executeUpdate(createTableQuery);

            System.out.println("Order table created successfully!");

            // Update the available quantity in the remote database
            updateRemoteInventory(connection);

            // Close the resources
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateRemoteInventory(Connection connection) {
        try {
            Connection remoteconnection = DBConnection.getRemoteDBConnection();
            // Create a statement object to execute SQL queries
            Statement statement = remoteconnection.createStatement();
            // Execute an UPDATE query to update the available quantity in the remote inventory table
            String updateQuery = "UPDATE inventory SET available_quantity = available_quantity - 1 where item_id=1";
            statement.executeUpdate(updateQuery);

            System.out.println("Updated quantity in the remote inventory!");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



