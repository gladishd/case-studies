/**
 * WordCounter.java
 * @Author Dean Gladish 27 Feb 2018
 * @Author Ben Tordi 27 Feb 2018
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class WordCounter {
    public static void main(String[] args) throws FileNotFoundException {

        // This stores the stop words eventually, using a scanner.
        ArrayList<String> stopWordsList = new ArrayList<String>();

        WordCountMap newMap = new WordCountMap();

        Scanner scannerStopWords = new Scanner(new File("StopWords.txt"));
        while (scannerStopWords.hasNext()) {
            stopWordsList.add(scannerStopWords.next());
        } // This scanner reads the stop words file.  It populates the array list.

        Scanner scanner = new Scanner(new File(args[1]));
        // args[1] denotes the file name.
        while (scanner.hasNext()) {
            String word = scanner.next();
            word = word.replaceAll("[^a-zA-Z]","");
            // This makes sure that we don't include any extraneous (non-alphabetical) characters.

            /**
             * We use the matches method due to a peculiarity of the scanner wherein
             * it multiplies the replacement value (in this case "") by the number of lines
             * in the text file, thereby making a conventional if statement non-viable.
             */
            if (!word.matches("") && !stopWordsList.contains(word.toLowerCase())) {

                /**
                 * Because the StopWords.txt file only accounts for lower-case words,
                 * we have to convert the word to lower case parenthetically before it is
                 * passed to the contains method.
                 */

                /**
                 * Again, the scanner problem necessitates that we make sure that the word does not
                 * contain any possible number of empty spaces. If it does, we do not put the word in
                 * the binary search tree.
                 */

                newMap.incrementCount(word.toLowerCase());
                /**
                 * We have to increment the count of (and possibly add) the word to the
                 * newMap.  This requires that it is converted to lower case again.
                 */
            }
        }

        // This handles one of the three arguments we can pass to this method in the console.
        if (args[0].equals("alphabetical")) {

            //  Here we initialize arrays.
            ArrayList<WordCount> wordCountsByWord = newMap.getWordCountsByWord();
            ArrayList<String> tempWordCountString = new ArrayList<String>();
            ArrayList<String> tempWordCountInt = new ArrayList<String>();


            /**
             * We have to convert the list of WordCount objects into a list of
             * their underlying Strings so that we can read
             * the word values from the console.
             */
            while (!wordCountsByWord.isEmpty()) {
                tempWordCountString.add(wordCountsByWord.remove(0).word);
            }

            /**
             * We also have to convert the list of WordCount objects into
             * a list of their underlying counts for the purpose of this
             * assignment.
             */
            ArrayList<WordCount> wordCountsByWord2 = newMap.getWordCountsByWord();
            while (!wordCountsByWord2.isEmpty()) {
                tempWordCountInt.add(wordCountsByWord2.remove(0).count + "");
            }

            /**
             * This for-loop prints to the console.  It
             * combines the results of both while loops.
             */
            for (int i = 0; i < tempWordCountInt.size(); i++) {
                System.out.println(tempWordCountString.get(i) + ": " + tempWordCountInt.get(i));
            }
        }

        // This handles the second argument we should pass.
        if (args[0].equals("frequency")) {

            // This creates a temporary array of the word values.
            ArrayList<String> tempWordCountByCount = new ArrayList<String>();
            ArrayList<WordCount> wordCountsByCount = newMap.getWordCountsByCount();
            while (!wordCountsByCount.isEmpty()) {
                tempWordCountByCount.add(wordCountsByCount.remove(0).word + "");
            }

            // This creates a temporary array of the count values.
            ArrayList<String> tempWordCountByCountCounts = new ArrayList<String>();
            ArrayList<WordCount> wordCountsByCountCounts = newMap.getWordCountsByCount();
            while (!wordCountsByCountCounts.isEmpty()) {
                tempWordCountByCountCounts.add(wordCountsByCountCounts.remove(0).count + "");
            }

            /**
             * Since we use the getWordCountsByCount method,
             * every array  within this sub-class is already ordered by count.
             */
            for (int i = 0; i < tempWordCountByCountCounts.size(); i++) {
                System.out.println(tempWordCountByCount.get(i) + ": " + tempWordCountByCountCounts.get(i));
            }
        }

        if (args[0].equals("cloud")) { // This part generates the word cloud.
            ArrayList<WordCount> wordCountsByCount = newMap.getWordCountsByCount();

            /**
             * We truncate the WordCount array using this for-loop.
             * We set its size to include the largest n values where
             * n is specified in the arguments.
             */
            ArrayList<WordCount> wordCountsByCountTruncated = new ArrayList<WordCount>();
            for (int i = 0; i < Integer.parseInt(args[2]); i++) {
                wordCountsByCountTruncated.add(wordCountsByCount.get(i));
            }

            /**
             * We use substring here to remove the .txt part of the argument
             * in order to create a cleaner .html file name.
             */
            WordCloudMaker newCloud = new WordCloudMaker();
            newCloud.createWordCloudHTML(args[1].substring(0, args[1].length() - 4) + " Word Cloud"
                    , wordCountsByCountTruncated,
                    args[1].substring(0, args[1].length() - 4) + ".html");
        }
    }
}
