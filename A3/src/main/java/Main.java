public class Main {
    public static void main(String[] args) {

        // Problem 1A call - ReutRead
        //ReutRead reutRead = new ReutRead();
        //reutRead.processData();

        // Problem 1B call - WordCounter
        String fileName = "/Users/bhavishaoza/IdeaProjects/DataMgmt/A3/src/reut2-009.sgm";
        WordCounter wordCounter = new WordCounter();
        wordCounter.countWordsFromFile(fileName);
        wordCounter.printWordFrequency();

        String[] leastCommonWords = wordCounter.getLeastCommonWords();
        System.out.println("\n++++++++++++++++++++++");
        System.out.println("Least common words:");
        System.out.println("++++++++++++++++++++++");
        printArray(leastCommonWords);

        String[] mostCommonWords = wordCounter.getMostCommonWords();
        System.out.println("\n++++++++++++++++++++++");
        System.out.println("Most common words:");
        System.out.println("++++++++++++++++++++++");
        printArray(mostCommonWords);
    }

    private static void printArray(String[] array) {
        for (String item : array) {
            System.out.println(item);
        }
    }
}