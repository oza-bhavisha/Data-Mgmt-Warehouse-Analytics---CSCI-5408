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
     * @param userMap the map to store the loaded user data
     */
    public void loadUsers(Map<String, User> userMap) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line= reader.readLine()) != null) {
                String[] parts = line.split("::");
                userMap.put(parts[0], new User(parts[0], parts[1], parts[2], parts[3]));
            }
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
    }

    /**
     * Saves user data from the provided map into the user file.
     *
     * @param userMap the map containing the user data to be saved
     */
    public void saveUsers(Map<String, User> userMap) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, User> entry : userMap.entrySet()) {
                User user = entry.getValue();
                String remainingLine = user.getPassword() + "::" + user.getQuestion() + "::" + user.getAnswer();
                String line = entry.getKey() + "::" + remainingLine;
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }
}