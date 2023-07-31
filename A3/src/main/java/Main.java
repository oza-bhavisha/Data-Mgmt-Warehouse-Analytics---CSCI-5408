import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        // Problem 1A call - ReutRead
        //ReutRead reutRead = new ReutRead();
        //reutRead.processData();

        // Problem 1B call - WordCounter
//        String fileName = "src/reut2-009.sgm";
//        WordCounter wordCounter = new WordCounter();
//        wordCounter.countWordsFromFile(fileName);
//        wordCounter.printWordFrequency();
//
//        String[] leastCommonWords = wordCounter.getLeastCommonWords();
//        System.out.println("\n++++++++++++++++++++++");
//        System.out.println("Least common words:");
//        System.out.println("++++++++++++++++++++++");
//        for (String word : leastCommonWords) {
//            System.out.println(word + ": " + wordCounter.getWordFrequency().get(word));
//        }
//
//        String[] mostCommonWords = wordCounter.getMostCommonWords();
//        System.out.println("\n++++++++++++++++++++++");
//        System.out.println("Most common words:");
//        System.out.println("++++++++++++++++++++++");
//        for (String word : mostCommonWords) {
//            System.out.println(word + ": " + wordCounter.getWordFrequency().get(word));
//        }
//    }
//
//    private static void printArray(String[] array) {
//        for (String item : array) {
//            System.out.println(item);
//        }

        // Problem 2 call - NewsTitleExtractor
        NewsTitleExtractor newsTitleExtractor = new NewsTitleExtractor();

        String[] sgmFiles = {"src/reut2-009.sgm", "src/reut2-014.sgm"};

        for (String filePath : sgmFiles) {
            Map<String, String> newsPolarityMap = newsTitleExtractor.extractNewsTitlesWithPolarity(filePath);
            displayResults(filePath, newsPolarityMap);
        }
    }

    /**
     * Display the results of news title analysis in a GUI table.
     *
     * @param filePath         The path of the file containing news titles.
     * @param newsPolarityMap  A map containing news titles as keys and their polarities as values.
     */
    private static void displayResults(String filePath, Map<String, String> newsPolarityMap) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("News Title Polarity - " + filePath);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            DefaultTableModel tableModel = new DefaultTableModel(
                    new Object[]{"News Title", "Polarity"}, 0);

            for (Map.Entry<String, String> entry : newsPolarityMap.entrySet()) {
                tableModel.addRow(new Object[]{entry.getKey(), entry.getValue()});
            }

            JTable table = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(table);
            frame.add(scrollPane);

            frame.pack();
            frame.setVisible(true);
        });
    }
}