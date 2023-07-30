public class Main {
    public static void main(String[] args) {

        // Problem 1A call - ReutRead
        //ReutRead reutRead = new ReutRead();
        //reutRead.processData();

        // Problem 1B call - WOrdCounter
        String fileName = "/Users/bhavishaoza/IdeaProjects/DataMgmt/A3/src/reut2-009.sgm";
        WordCounter wordCounter = new WordCounter();
        wordCounter.countWordsFromFile(fileName);
        wordCounter.printWordFrequency();

        String[] leastCommonWords = wordCounter.getLeastCommonWords();
        System.out.println("\nLeast common words:");
        printArray(leastCommonWords);

        String[] mostCommonWords = wordCounter.getMostCommonWords();
        System.out.println("\nMost common words:");
        printArray(mostCommonWords);
    }

    private static void printArray(String[] array) {
        for (String item : array) {
            System.out.println(item);
        }
    }
}