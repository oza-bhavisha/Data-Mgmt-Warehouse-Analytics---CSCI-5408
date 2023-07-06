package authentication;

import java.io.*;
import java.util.Map;

/**
 * Utility class for handling user file operations.
 */
public class UserFileHandler {
    private final String filePath;

    /**
     * Constructs a UserFileHandler object.
     *
     * @param filePath the path of the user file
     */
    public UserFileHandler(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads user data from the user file into the provided map.
     *
     * @param users the map to store the loaded user data
     */
    public void loadUsers(Map<String, String> users) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line= reader.readLine()) != null) {
                String[] parts = line.split("::");
                users.put(parts[0], parts[1]);
            }
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
    }

    /**
     * Saves user data from the provided map into the user file.
     *
     * @param users the map containing the user data to be saved
     */
    public void saveUsers(Map<String, String> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, String> entry : users.entrySet()) {
                String line = entry.getKey() + "::" + entry.getValue();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }
}