package authentication;

import Utils.Utils;

import java.util.*;

/**
 * Module for user authentication.
 */
public class UserAuthenticationModule {
    private final Map<String, User> usersMap;
    private final UserFileHandler fileHandler;
    private final Scanner scanner;

    // Constructor for a UserAuthenticationModule.
    public UserAuthenticationModule() {
        fileHandler = new UserFileHandler("user_details.txt");
        scanner = new Scanner(System.in);
        usersMap = new HashMap<>();
    }

    // Starts the user authentication module.
    public void start() {

        // Load users from the file
        fileHandler.loadUsers(usersMap);

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
        if (usersMap.containsKey(username)) {
            System.out.println("This username already exists...");
            return;
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        String hashedPassword = Utils.hashString(password);

        System.out.print("Enter security question: ");
        String question = scanner.nextLine();

        System.out.print("Enter answer: ");
        String answer = scanner.nextLine();

        usersMap.put(username, new User(username, hashedPassword, question, answer));
        fileHandler.saveUsers(usersMap);
        System.out.println("Yay.. User created successfully...");
    }

    // User login.
    private void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        if (!usersMap.containsKey(username)) {
            System.out.println("Username not found!");
            return;
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        String hashedPassword = Utils.hashString(password);

        User storedUserData = usersMap.get(username);
        String storedPassword = storedUserData.getPassword();

        if (Objects.equals(hashedPassword, storedPassword)) {
            System.out.print("Enter security question: ");
            String question = scanner.nextLine();

            if (question.equals(storedUserData.getQuestion())) {
                System.out.print("Enter answer: ");
                String answer = scanner.nextLine();

                if (answer.equals(storedUserData.getAnswer())) {
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