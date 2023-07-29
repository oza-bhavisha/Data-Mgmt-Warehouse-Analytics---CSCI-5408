import com.mongodb.client.*;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReutRead {

    public void processData() {
        String fileName1 = "/Users/bhavishaoza/IdeaProjects/DataMgmt/A3/src/reut2-009.sgm";
        String fileName2 = "/Users/bhavishaoza/IdeaProjects/DataMgmt/A3/src/reut2-014.sgm";

        // Step 1: Create MongoDB connection and database
        String uri = "mongodb+srv://erbhavisha:admin@cluster-lab-6.pqo1uvf.mongodb.net/";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("A3DB");
            MongoCollection<Document> collection = database.getCollection("News");

            // Step 2: Read the content
            String part1 = readFromFile(fileName1);
            String part2 = readFromFile(fileName2);
            String mergedPart = part1 + "\n" + part2;

            // Step 3: Extract the text between <REUTERS> tags
            Pattern reutersPattern = Pattern.compile("<REUTERS[^>]*>(.*?)<\\/REUTERS>", Pattern.DOTALL);
            Matcher reutersMatcher = reutersPattern.matcher(mergedPart);

            // Step 4: Extract text between <TITLE> and <TEXT> tags
            Pattern titlePattern = Pattern.compile("<TITLE[^>]*>(.*?)<\\/TITLE>", Pattern.DOTALL);
            Pattern textPattern = Pattern.compile("<TEXT[^>]*>(.*?)<\\/TEXT>", Pattern.DOTALL);

            // Step 5: Insert each article as a document in the "A3DB" database
            while (reutersMatcher.find()) {
                String reutersArticle = reutersMatcher.group(1);

                Matcher titleMatcher = titlePattern.matcher(reutersArticle);
                Matcher textMatcher = textPattern.matcher(reutersArticle);

                if (titleMatcher.find() && textMatcher.find()) {
                    String title = titleMatcher.group(1);
                    String text = textMatcher.group(1);

                    // Create a document and insert into MongoDB
                    Document document = new Document();
                    document.put("title", title);
                    document.put("text", text);
                    collection.insertOne(document);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String readFromFile(String fileName) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}