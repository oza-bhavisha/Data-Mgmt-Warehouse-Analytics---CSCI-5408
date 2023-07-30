import java.io.*;
import java.util.*;

/**
 * The WordCounter class is responsible for reading words from a file,
 * counting their occurrences, and providing various operations related to word frequency.
 */
public class WordCounter {
    private final Map<String, Integer> wordFrequency;

    public WordCounter() {
        wordFrequency = new HashMap<>();
    }

    /**
     * Reads a file and counts the frequency of each word.
     *
     * @param fileName The name of the file to read words from.
     */
    public void countWordsFromFile(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();

            String[] words = content.toString().replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
            for (String word : words) {
                wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints the word frequencies to the console.
     */
    public void printWordFrequency() {
        System.out.println("++++++++++++++++++++++");
        System.out.println("Word count:");
        System.out.println("++++++++++++++++++++++");
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    /**
     * Gets the least common words based on their frequency.
     *
     * @return An array of the least common words.
     */
    public String[] getLeastCommonWords() {
        int minCount = Integer.MAX_VALUE;
        for (int count : wordFrequency.values()) {
            if (count < minCount) {
                minCount = count;
            }
        }
        return getWordsWithFrequency(minCount);
    }

    /**
     * Gets the most common words based on their frequency.
     *
     * @return An array of the most common words.
     */
    public String[] getMostCommonWords() {
        int maxCount = Integer.MIN_VALUE;
        for (int count : wordFrequency.values()) {
            if (count > maxCount) {
                maxCount = count;
            }
        }
        return getWordsWithFrequency(maxCount);
    }

    /**
     * Gets the words with a specific frequency.
     *
     * @param frequency The frequency of the words to retrieve.
     * @return An array of words with the given frequency.
     */
    private String[] getWordsWithFrequency(int frequency) {
        return wordFrequency.entrySet().stream().filter(entry -> entry.getValue() == frequency).map(Map.Entry::getKey).toArray(String[]::new);
    }
}