/**
 * WordCount.java
 * @Author Dean Gladish 27 Feb 2018
 * @Author Ben Tordi 27 Feb 2018
 */

public class WordCount implements Comparable<WordCount>{
    public String word;
    public int count;

    /**
     * This allows us to specify the value of word and count.
     * @param w word
     * @param c count
     */
    public WordCount(String w, int c) {
        word = w;
        count = c;
    }


    @Override
    public int compareTo(WordCount o) {
        int result = 0;
        /**
         * Declared here so that we always have a value to return.
         * This avoids some concerns expressed by IntelliJ.
         */

        /**
         * The comparison is negative when our count is less than the
         * count of the WordCount object o.  The comparison is likewise positive
         * when we find our count is greater than that of o.
         *
         * A result value of 0 indicates that the counts of the two WordCount
         * objects are exactly the same.
         */
        if (count < o.count) { result = -1; }
        if (count > o.count) { result = 1;  }
        if (count == o.count) { result = 0; }
        return result;
    }
}
