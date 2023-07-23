import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class ReutRead {
    public static void main(String[] args) {
        // MongoDB connection details
        String connectionString = "mongodb://localhost:27017";
        String dbName = "ReuterDb";
        String collectionName = "newsArticles";

        // Read the content of the two given news files
        String file1Path = "reut2-009.sgm";
        String file2Path = "reut2-014.sgm";
        String file1Content = readNewsFile(file1Path);
        String file2Content = readNewsFile(file2Path);

        // Extract news articles from the content
        List<NewsArticle> newsArticles = extractNewsArticles(file1Content, file2Content);

        // Create MongoDB client and connect to the database
        MongoClientURI uri = new MongoClientURI(connectionString);
        try (MongoClient mongoClient = new MongoClient(uri)) {
            MongoDatabase database = mongoClient.getDatabase(dbName);
            MongoCollection<Document> collection = database.getCollection(collectionName);

            // Insert news articles into MongoDB collection
            for (NewsArticle article : newsArticles) {
                Document doc = new Document("title", article.getTitle())
                        .append("text", article.getText());
                collection.insertOne(doc);
            }
            System.out.println("News articles inserted into the MongoDB collection successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String readNewsFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    private static List<NewsArticle> extractNewsArticles(String... fileContents) {
        List<NewsArticle> newsArticles = new ArrayList<>();
        String reutersPattern = "<REUTERS>(.*?)</REUTERS>";
        String titlePattern = "<TITLE>(.*?)</TITLE>";
        String textPattern = "<TEXT>(.*?)</TEXT>";
        Pattern reutersRegex = Pattern.compile(reutersPattern, Pattern.DOTALL);
        Pattern titleRegex = Pattern.compile(titlePattern, Pattern.DOTALL);
        Pattern textRegex = Pattern.compile(textPattern, Pattern.DOTALL);

        for (String content : fileContents) {
            Matcher reutersMatcher = reutersRegex.matcher(content);
            while (reutersMatcher.find()) {
                String reutersTagContent = reutersMatcher.group(1);
                Matcher titleMatcher = titleRegex.matcher(reutersTagContent);
                Matcher textMatcher = textRegex.matcher(reutersTagContent);
                if (titleMatcher.find() && textMatcher.find()) {
                    String title = titleMatcher.group(1).trim();
                    String text = textMatcher.group(1).trim();
                    NewsArticle article = new NewsArticle(title, text);
                    newsArticles.add(article);
                }
            }
        }
        return newsArticles;
    }
}