/**
 * WordCloudMaker.java
 * Sherri Goings, 12 Feb 2013
 * Modified by Jeff Ondich, 14 Feb 2014
 * Modified by Layla Oesper, 31 Oct 2017
 * Modified by Eric Alexander 18 Feb 2018
 * Viewed by Dean Gladish 27 Feb 2018
 * Viewed by Ben Tordi 27 Feb 2018
 */

import java.util.*;
import java.io.*;
import java.lang.*;

public class WordCloudMaker {
    private static final String[] HTML_COLORS = new String[] {/*"edf8b1",*/ "c7e9b4", "7fcdbb", "41b6c4",
                                                              "1d91c0", "225ea8", "253494", "081d58"};

    private static final String HTML_TEMPLATE = "<!DOCTYPE html>\n"
                                                + "<head>\n<title>TITLE</title>\n</head>\n"
                                                + "<body>\n<h1>TITLE</h1>\n"
                                                + "<div style=\"\n width: 800px;\n background-color: rgb(250,250,250);\n"
                                                + " border: 5px grey solid;\ntext-align: center\">\n"
                                                + "WORDS\n"
                                                + "</div>\n"
                                                + "</body>\n</html>\n";

    /**
     * Creates a word cloud based on the (word, frequency) pairs in wordCountList.
     * Words are sized (small to large) and colored (light to dark) proportionately to
     * frequency (least frequent to most). Word positions are randomized.
     *
     * NOTE: This method reorders wordCountList.
     *
     * @param title the desired title for the word cloud.
     * @param wordCountList the list of (word, frequency) pairs for which a word cloud is desired.
     * @param htmlName the filename in which to store the HTML
     */
    public static void createWordCloudHTML(String title, List<WordCount> wordCountList, String htmlName) {
        String htmlStr = getWordCloudHTML(title, wordCountList);
        try {
            PrintStream outstream = new PrintStream(new FileOutputStream(htmlName));
            outstream.print(htmlStr);
        } catch (FileNotFoundException e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    /**
     * Creates a word cloud based on the (word, frequency) pairs in wordCountList.
     * Words are sized (small to large) and colored (light to dark) proportionately to
     * frequency (least frequent to most). Word positions are randomized.
     *
     * NOTE: This method reorders wordCountList.
     *
     * @return a string consisting of HTML that will draw the word cloud.
     * @param title the desired title for the word cloud.
     * @param wordCountList the list of (word, frequency) pairs for which a word cloud is desired.
     */
    public static String getWordCloudHTML(String title, List<WordCount> wordCountList) {
        // Get the maximum and minimum frequencies for the words in the wordCountList.
        int maximumFrequency = 0;
        int minimumFrequency = Integer.MAX_VALUE;
        for (WordCount wordCount : wordCountList) {
            if (wordCount.count > maximumFrequency) {
                maximumFrequency = wordCount.count;
            }
            if (wordCount.count < minimumFrequency) {
                minimumFrequency = wordCount.count;
            }
        }

        // Shuffle wordCountList.
        Random randomGenerator = new Random();
        for (int k = wordCountList.size(); k > 1; k--) {
            int indexOfItemToSwap = randomGenerator.nextInt(k);
            WordCount tempWordCount = wordCountList.get(k - 1);
            wordCountList.set(k - 1, wordCountList.get(indexOfItemToSwap));
            wordCountList.set(indexOfItemToSwap, tempWordCount);
        }

        // Create a String(Joiner) consisting of HTML encoding of the words in wordCountList,
        // sized and colored appropriately for their frequencies.
        StringJoiner wordsInHTML = new StringJoiner("");
        for (WordCount wordCount : wordCountList) {
            wordsInHTML.add(makeHTMLWord(wordCount, maximumFrequency, minimumFrequency));
        }

        // Add the HTML code to surround the words with a box
        String document = HTML_TEMPLATE.replaceAll("TITLE", title);
        //System.out.println(document);
        document = document.replaceAll("WORDS", wordsInHTML.toString());

        return document;
    }

    /**
     * Returns an HTML version of the specified WordCount.
     */
    private static String makeHTMLWord(WordCount wordCount, int maximumFrequency, int minimumFrequency) {
        final int maximumFontSize = 96;
        final int minimumFontSize = 14;
        double ratio = (double)(wordCount.count - minimumFrequency)/(double)(maximumFrequency - minimumFrequency);
        int fontSize = (int)(maximumFontSize * ratio + (1 - ratio) * minimumFontSize);
        String color = HTML_COLORS[(int)((ratio / 1.01) * HTML_COLORS.length)];
        String wordString = "<span style=\"color:#" + color + ";font-size:";
        wordString += fontSize + "px;\">&nbsp";
        wordString += wordCount.word + "&nbsp</span>\n";
        return wordString;

    }

    /**
     * In order for these methods to work, we need to implement the WordCount method.
     * This method should have the sub-property, the instance variable called "word."
     *
     */
}