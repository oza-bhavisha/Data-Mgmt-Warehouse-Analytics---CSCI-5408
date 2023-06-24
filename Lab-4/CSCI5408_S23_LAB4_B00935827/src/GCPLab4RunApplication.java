import java.sql.*;

public class GCPLab4RunApplication {
    public static void main(String[] args) {
        try {
            // Establish a connection to the GCP database
            Connection connection = DBConnection.getRemoteDBConnection();

            Statement statement = connection.createStatement();

            // Query-1: SELECT query to fetch item details
            String sqlQuery = "SELECT * FROM inventory";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                int itemId = resultSet.getInt("item_id");
                String itemName = resultSet.getString("item_name");
                int availableQuantity = resultSet.getInt("available_quantity");

                System.out.println("*************************");
                System.out.println("Item ID is: " + itemId);
                System.out.println("Item Name is: " + itemName);
                System.out.println("Available Quantity is: " + availableQuantity);
                System.out.println("*************************");
            }

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

            Statement statement = connection.createStatement();

            // Query-2: CREATE TABLE query to create the order table
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

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateRemoteInventory(Connection connection) {
        try {
            Connection remoteconnection = DBConnection.getRemoteDBConnection();
            
            Statement statement = remoteconnection.createStatement();

            // Execute an UPDATE query to update the available quantity in the remote inventory table
            String updateQuery = "UPDATE inventory SET available_quantity = available_quantity - 1 where item_id=1";
            statement.executeUpdate(updateQuery);

            System.out.println("Yay... Updated in the remote inventory!!!!!!");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



