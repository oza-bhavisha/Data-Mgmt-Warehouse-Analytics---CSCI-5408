package utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utility class for common operations.
 */
public class Utils {

    /**
     * Hashes a string using the MD5 algorithm.
     *
     * @param input the input string to be hashed
     * @return the hashed string
     */
    public static String hashString(String input) {
        try {
            // Reference: https://www.geeksforgeeks.org/md5-hash-in-java/

            // Create an instance of the MD5 MessageDigest
            MessageDigest digest = MessageDigest.getInstance("MD5");

            // Compute the hash bytes for the input string
            byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder builder = new StringBuilder();

            // Convert each byte to a hexadecimal string representation
            for (byte b : hashBytes) {
                builder.append(String.format("%02x", b));
            }

            // Return the hashed string
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error hashing string: " + e.getMessage());
            return null;
        }
    }
}