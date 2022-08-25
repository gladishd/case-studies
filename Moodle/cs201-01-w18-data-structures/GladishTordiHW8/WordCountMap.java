/**
 * WordCountMap.java
 * @Author Dean Gladish 27 Feb 2018
 * @Author Ben Tordi 27 Feb 2018
 */

import java.util.ArrayList;
import java.util.Collections; // We've only imported the utilities that we need.


public class WordCountMap {

    ArrayList<WordCount> newListBST = new ArrayList<WordCount>();
    /**
     * The above array list is important b/c it is used to store the words that are
     * retrieved from the tree by the getWordCountsByWord() method.
     */

    private class Node {
        private String word;
        private int count;
        private Node left;
        private Node right;

        /**
         * The following method was added by Dean.  It specifies the
         * value of the underlying word and count for each initialized node object
         * within WordCountMap.
         * @param w word
         * @param c count
         */
        public Node(String w, int c) {
            word = w;
            count = c;
            left = null;
            right = null;
        }
    }

    private Node rootNode; // This initializes a root node.

    //  This WordCountMap class implements the following three methods:

    /**
     * If the specified word is already in this WordCountMap, then its
     * count is increased by one. Otherwise, the word is added to this map
     * with a count of 1.
     */
    public void incrementCount(String word) {
        /**
         * If the binary tree is empty, don't continue to traverse but rather
         * add the word to the root of the tree.
         */
        if (rootNode == null) {
            rootNode = new Node(word, 1);
            return;
        }
        /**
         * We start looking at the root node as our current node.  However,
         * the value of our current node changes over time.
         */
        Node currentNode = rootNode;

        /**
         * The purpose of this boolean is to toggle whether the
         * while loop continues to run.
         */
        boolean isFound = false;

        /**
         * As long as the value has not been found within the tree,
         * it is essential to continue our traversal until we either
         * find the value or have to add it once we reach the end with no
         * node where we expected it to be.
         */
        while (!isFound) {
            if (word.compareTo(currentNode.word) < 0) {
                if (currentNode.left != null) {
                    /**
                     * Since the Binary Search Tree is ordered alphabetically and
                     * our value for word is less than the String contained in the
                     * current Node, we look at the sub-node, the left node, of
                     * the current Node.
                     */
                    currentNode = currentNode.left;
                } else if (currentNode.left == null) {
                    /**
                     * If we reach the end and don't find the value, we have to
                     * insert it into the binary search tree.
                     */
                    currentNode.left = new Node(word, 1);
                    // Since we have inserted it, we set the isFound boolean to true.
                    isFound = true;
                }
            } else if (word.compareTo(currentNode.word) > 0) {
                /**
                 * In this case we need to go right because the
                 * value is greater than our current node's word value.
                 * The Binary Search Tree is implemented such that it is
                 * ordered alphabetically, so this is necessary.
                 */
                if (currentNode.right != null) {
                    currentNode = currentNode.right;
                } else if (currentNode.right == null) {
                    currentNode.right = new Node(word, 1);
                    /**
                     * Again we have reached the end and, not finding the
                     * value, have inserted it into the tree.  Thus, we have
                     * found it through insertion.
                     */
                    isFound = true;
                }
            } else if (word.compareTo(currentNode.word) == 0) {
                /**
                 * If we ever find a node with the same string value, then
                 * we have found the node for which we are looking!
                 * We thus increment the node's count, since this word value
                 * appears to be already within the binary search tree.
                 */
                isFound = true;

                // Here we simply increment the count by 1.
                currentNode.count += 1;
            }

        }
    }






    /**
     * Returns an array list of WordCount objects, one per word stored in this
     * WordCountMap, sorted in decreasing order by count.
     */
    public ArrayList<WordCount> getWordCountsByCount() {
        /**
         * Because it is not a requirement for this assignment,
         * we did not go through the whole binary tree process.
         * Ben and I simply sorted the words by count using Collections.sort and the
         * reverseOrder function of that method.
         */
        ArrayList<WordCount> newListSortedByCount = new ArrayList<WordCount>();
        newListSortedByCount = getWordCountsByWordHelper(rootNode);
        Collections.sort(newListSortedByCount, Collections.reverseOrder());
        return newListSortedByCount;
    }




    /**
     * Returns a list of WordCount objects, one per word stored in this
     * WordCountMap, sorted alphabetically by word.
     * We've turned it into a recursive method.
     */
    public ArrayList<WordCount> getWordCountsByWord() {

        // We simply call the recursive helper method, passing it the root as the beginning.
        return getWordCountsByWordHelper(rootNode);
    }

    private ArrayList<WordCount> getWordCountsByWordHelper(Node node) {
        if (node.left != null) {
            /**
             * If there's a node to the left with a value, we
             * have the recursive method go left first since left is the
             * lesser value and we are trying to get the words in
             * alphabetical order.  Given that the BST is already in alphabetical order,
             * we simply need to follow this convention.
             */
            getWordCountsByWordHelper(node.left);
        }

        // The array mentioned is initialized at the beginning of the superclass.
        WordCount tempObject = new WordCount(node.word, node.count);
        newListBST.add(tempObject);

        if (node.right != null) {
            /**
             * Only once there are no more left nodes will this code run.
             * Due to the nature of recursion the algorithm will retrieve
             * all of the nodes in the proper order (from left to right, the branches
             * being repetitions of the same such that we go from the base case (one branch)
             * to the entire tree.
             */
            getWordCountsByWordHelper(node.right);
        }
       return newListBST;
    }
}