import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewsTitleExtractor {

    public Map<String, Map<String, Integer>> extractNewsTitles(String filePath) {
        Map<String, Map<String, Integer>> bagOfWordsMap = new HashMap<>();

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
                bagOfWordsMap.put(title, wordCountMap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bagOfWordsMap;
    }

    private Map<String, Integer> updateWordCountMap(String title) {
        Map<String, Integer> wordCountMap = new HashMap<>();

        String[] words = title.split("\\s+");
        for (String word : words) {
            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
        }

        return wordCountMap;
    }
}