import java.util.Map;

public class Main {
    public static void main(String[] args) {

        // Problem 1A call - ReutRead
        //ReutRead reutRead = new ReutRead();
        //reutRead.processData();

        // Problem 1B call - WordCounter
//        String fileName = "/Users/bhavishaoza/IdeaProjects/DataMgmt/A3/src/reut2-009.sgm";
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
            Map<String, Map<String, Integer>> bagOfWordsMap = newsTitleExtractor.extractNewsTitles(filePath);
            System.out.println("Bag-of-words for news titles from " + filePath + ":");
            for (Map.Entry<String, Map<String, Integer>> entry : bagOfWordsMap.entrySet()) {
                System.out.println("News Title: " + entry.getKey());
                Map<String, Integer> wordCountMap = entry.getValue();
                for (Map.Entry<String, Integer> wordEntry : wordCountMap.entrySet()) {
                    System.out.println(wordEntry.getKey() + " = " + wordEntry.getValue());
                }
                System.out.println();
            }
        }
    }
}