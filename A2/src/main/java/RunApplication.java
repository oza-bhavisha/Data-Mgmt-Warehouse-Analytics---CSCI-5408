import authentication.UserAuthenticationModule;

// Main method to run
public class RunApplication {
    public static void main(String[] args) {
        // Create an instance of the UserAuthenticationModule
        UserAuthenticationModule module = new UserAuthenticationModule();

        // Start the authentication module
        module.start();
    }
}