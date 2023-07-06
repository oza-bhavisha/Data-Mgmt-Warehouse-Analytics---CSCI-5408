package authentication;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/**
 * Module for user authentication.
 */
public class UserAuthenticationModule {
    private final Map<String, String> users;
    private final UserFileHandler fileHandler;
    private final Scanner scanner;

    // Constructor for a UserAuthenticationModule.
    public UserAuthenticationModule() {
        users = new HashMap<>();
        fileHandler = new UserFileHandler("user_details.txt");
        scanner = new Scanner(System.in);
    }

    // Starts the user authentication module.
    public void start() {

        // Load users from the file
        fileHandler.loadUsers(users);

        while (true) {
            displayMenu();

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createUser();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    fileHandler.saveUsers(users);
                    System.exit(0);
                    break;
                default:
                    System.out.println("Oops Invalid choice... Please try again.");
            }
        }
    }

    // Displays the menu options.
    private void displayMenu() {
        System.out.println("---------------------------");
        System.out.println("1. Create User");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.println("---------------------------");
        System.out.print("Please Enter your choice: ");
    }

    // Creates a new user.
    private void createUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        if (users.containsKey(username)) {
            System.out.println("This username already exists...");
            return;
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        String hashedPassword = PasswordHashing.hashString(password);

        System.out.print("Enter security question: ");
        String question = scanner.nextLine();

        System.out.print("Enter answer: ");
        String answer = scanner.nextLine();

        String userData = hashedPassword + "::" + question + "::" + answer;
        users.put(username, userData);
        System.out.println("Yay.. User created successfully...");
    }

    // User login.
    private void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        if (!users.containsKey(username)) {
            System.out.println("Username not found!");
            return;
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        String hashedPassword = PasswordHashing.hashString(password);

        String storedUserData = users.get(username);
        String[] userDataParts = storedUserData.split("::");
        String storedPassword = userDataParts[0];

        if (Objects.equals(hashedPassword, storedPassword)) {
            System.out.print("Enter security question: ");
            String question = scanner.nextLine();

            if (question.equals(userDataParts[1])) {
                System.out.print("Enter answer: ");
                String answer = scanner.nextLine();

                if (answer.equals(userDataParts[2])) {
                    System.out.println("Login successful...");
                } else {
                    System.out.println("Wrong answer...");
                }
            } else {
                System.out.println("Wrong security question...");
            }
        } else {
            System.out.println("Wrong password...");
        }
    }
}