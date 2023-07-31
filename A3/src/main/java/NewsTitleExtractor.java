import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The NewsTitleExtractor class is responsible for extracting news titles from a given file
 * and determining their polarity (positive, negative, or neutral) based on word analysis.
 */
public class NewsTitleExtractor {

    /**
     * Extracts news titles from a file and determines their polarity using word analysis.
     *
     * @param filePath The path to the file containing the news titles.
     * @return A map with news titles as keys and their corresponding polarity as values.
     */
    public Map<String, String> extractNewsTitlesWithPolarity(String filePath) {
        Map<String, String> newsPolarityMap = new HashMap<>();

        // Read the positive and negative word files
        Map<String, Integer> positiveWords = readWordFile("src/positive-words.txt");
        Map<String, Integer> negativeWords = readWordFile("src/negative-words.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            String xmlContent = sb.toString();

            Pattern pattern = Pattern.compile("<TITLE>(.*?)</TITLE>");
            Matcher matcher = pattern.matcher(xmlContent);

            while (matcher.find()) {
                String title = matcher.group(1);
                Map<String, Integer> wordCountMap = updateWordCountMap(title);
                int overallScore = calculateOverallScore(wordCountMap, positiveWords, negativeWords);
                String polarity = getNewsPolarity(overallScore);
                newsPolarityMap.put(title, polarity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newsPolarityMap;
    }

    /**
     * Updates the word count map for a given title.
     *
     * @param title The news title.
     * @return A map containing word frequencies in the title.
     */
    private Map<String, Integer> updateWordCountMap(String title) {
        Map<String, Integer> wordCountMap = new HashMap<>();

        String[] words = title.split("\\s+");
        for (String word : words) {
            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
        }

        return wordCountMap;
    }


    /**
     * Reads words from a file and creates a word map.
     *
     * @param filePath The path to the file containing the words.
     * @return A map with words as keys and a default value as values (1).
     */

    private Map<String, Integer> readWordFile(String filePath) {
        Map<String, Integer> wordMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String word;
            while ((word = br.readLine()) != null) {
                wordMap.put(word, 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wordMap;
    }

    /**
     * Calculates the overall score of a news title based on word analysis.
     *
     * @param wordCountMap  A map containing word frequencies in the title.
     * @param positiveWords A map of positive words and their frequencies.
     * @param negativeWords A map of negative words and their frequencies.
     * @return The overall score of the news title.
     */
    private int calculateOverallScore(Map<String, Integer> wordCountMap, Map<String, Integer> positiveWords, Map<String, Integer> negativeWords) {
        int overallScore = 0;

        for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
            String word = entry.getKey();
            int count = entry.getValue();
            int positiveCount = positiveWords.getOrDefault(word, 0);
            int negativeCount = negativeWords.getOrDefault(word, 0);
            overallScore += (positiveCount - negativeCount) * count;
        }

        return overallScore;
    }

    /**
     * Determines the polarity (positive, negative, or neutral) of a news title based on its overall score.
     *
     * @param overallScore The overall score of the news title.
     * @return The polarity of the news title.
     */
    private String getNewsPolarity(int overallScore) {
        if (overallScore > 0) {
            return "Positive";
        } else if (overallScore < 0) {
            return "Negative";
        } else {
            return "Neutral";
        }
    }
}
