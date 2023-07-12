package authentication;

/**
 * Represents a user with authentication information.
 */
public class User {
    private String username;
    private String password;
    private String question;
    private String answer;

    /**
     * Constructor for User object with the specified username, password, security question, and answer.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @param question the security question chosen by the user
     * @param answer   the answer to the security question
     */
    public User(String username, String password, String question, String answer) {
        this.username = username;
        this.password = password;
        this.question = question;
        this.answer = answer;
    }

    /**
     * Returns the username of the user.
     *
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username the username of the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the password of the user.
     *
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the security question chosen by the user.
     *
     * @return the security question chosen by the user
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Sets the security question chosen by the user.
     *
     * @param question the security question chosen by the user
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Returns the answer to the security question.
     *
     * @return the answer to the security question
     */
    public String getAnswer() {
        return answer;
    }


    /**
     * Sets the answer to the security question.
     *
     * @param answer the answer to the security question
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }
}