import java.util.*;
import java.lang.*;

/**
 * Class for QuickSort Investigation, exploring how changing the
 * switch to insertion sort affects runtime.
 * Note that there are many private methods here for how quicksort works.
 * (We did not need to consider all of them, especially fillAndShuffle().)
 * We modified main and added some additional methods.
 * We modified the way in which the insertionSort threshold MIN_SIZE
 * is specified; now, it is a parameter.
 * 
 * @author Anna Rafferty
 * @author Layla Oesper
 * @author Eric Alexander
 * @author Dean Gladish
 * @author Ben Tordi
 */
public class QuickSort {
    // The way in which the minimum array size value has been implemented has been modified.

    /**
     * Sorts an array in increasing order using quicksort.
     * 
     * @param arr the array to be sorted
     */
    public static void quickSort(int[] arr, int MIN_SIZE) {
        quickSort(arr, 0, arr.length - 1, MIN_SIZE);
    }

    /**
     * Recursive helper method to quicksort.
     *
     * @param arr the array to be sorted
     * @param firstI the first index to be considered
     * @param lastI the last index to be considered
     */
    private static void quickSort(int[] arr, int firstI, int lastI, int MIN_SIZE) {
        if (lastI - firstI <= MIN_SIZE) {
            insertionSort(arr, firstI, lastI);
            /**
             * This implicates the threshold at which  insertionSort should be used.
             */
        } else {
            int pivotI = partition(arr, firstI, lastI);
            // Partitions the array using the partition method.
            quickSort(arr, firstI, pivotI - 1, MIN_SIZE);
            // Since MIN_SIZE was static and final originally,
            // it does not need to be changed.
            quickSort(arr, pivotI + 1, lastI, MIN_SIZE);
        }
    }

    /**
     * Partitions slice of given array between two given indices around a pivot.
     *
     * @param arr the array to be partitioned
     * @param firstI the first index to be considered in partitioning
     * @param lastI the last index to be considered in partitioning
     */
    private static int partition(int[] arr, int firstI, int lastI) {
        // Select middle item as pivot
        int pivotI = (lastI + firstI) / 2;
        // This generates the pivot's index in the array.
        int pivotV = arr[pivotI];
        // This finds the pivot's value and sets it to pivotV.
        swap(arr, firstI, pivotI);

        // March up and down closer and closer until they cross,
        // swapping values that are out of place
        int up = firstI + 1;
        int down = lastI;
        boolean done = false;
        while (!done) {
            // March up to find value greater than pivotV
            while (up < lastI && arr[up] < pivotV) {
                up++;
            }
            // March down to find value less than pivotV
            while (down > firstI && arr[down] > pivotV) {
                down--;
            }

            // If up and down passed, we're done.
            // Else, swap their values and keep going.
            if (up < down) {
                swap(arr, up, down);
                up++;
                down--;
            } else {
                done = true;
                swap(arr, firstI, down);
            }
        }

        // down will be pointing to the index of the pivot
        return down;
    }
    
    /**
     * Sorts the specified array between given indices.
     *
     * @param arr the array to be sorted
     * @param firstI the first index to be considered
     * @param lastI the last index to be considered
     */
    private static void insertionSort(int[] arr, int firstI, int lastI) {
        for (int i = firstI + 1; i <= lastI; i++) {
            int numToInsert = arr[i];
            int j = i;
            while (j > firstI && arr[j-1] > numToInsert) {
            arr[j] = arr[j-1];
            j--;
            }
            arr[j] = numToInsert;
        }
    }

    /**
     * Helper method that swaps two values in a given array.
     *
     * @param arr the array containing the values
     * @param i the index of the first value
     * @param j the index of the second value
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Helper function you may decide to use to print out a given array to the console.
     *
     * @param arr the array to print
     */
    private static void printArr(int[] arr) {
        StringJoiner sj = new StringJoiner(", ", "[", "]");
        for (int i = 0; i < arr.length; i++) {
            sj.add(Integer.toString(arr[i]));
        }
        System.out.println(sj.toString());
    }

    /**
     * You'll put your experiments for the investigation here. 
     * The current contents are just to give you an example.
     */
    public static void main(String[] args) {

        /**
         * What the following block of code does is run
         * the quicksort algorithm on four different thresholds.
         * Quicksort is run 30 times each for each of these
         * four thresholds, and the timing values are stored
         * in the following four arrays.  I have used a
         * smaller array for faster timing purposes.
         */
        ArrayList<String> sortTimesArrayForThreshold0 = new ArrayList<String>();
        ArrayList<String> sortTimesArrayForThreshold25Thousand = new ArrayList<String>();
        ArrayList<String> sortTimesArrayForThreshold50Thousand = new ArrayList<String>();
        ArrayList<String> sortTimesArrayForThreshold75Thousand = new ArrayList<String>();
        for (int j = 0; j < 100000; j += 25000) {
            int insertionUpperThreshold = j;
            /**
             * This is the purpose of this for loop.  It runs the quickSort for
             * different switch (to insertionSort) thresholds.
             */

            int[] quicksortArray = new int[100000];
            long sortTime = 0;
            if (j == 0) {
                for (int i = 0; i < 30; i++) {
                    // Provides a better measure (an average) of the typical sort time for a given
                    // switch threshold.
                    ShellSort.fillAndShuffle(quicksortArray);
                    // Fills the array with values from 0 to size - 1 and shuffles it.
                    long startTime = System.currentTimeMillis();

                    quickSort(quicksortArray, insertionUpperThreshold);
                    // The original test value was 10, so I use similar values.
                    long endTime = System.currentTimeMillis();
                    sortTime = endTime-startTime;

                    sortTimesArrayForThreshold0.add("" + (int) sortTime);
                    // We must cast sortTime (type long) to an integer.

                }
                System.out.println(sortTimesArrayForThreshold0);
                /**
                 * I placed the prints to the console here
                 * so that we can monitor the progress of the arrays' creation.
                 */
            } else if (j == 25000) {
                for (int i = 0; i < 30; i++) {
                    // Provides a better measure (an average) of the typical sort time for a given
                    // switch threshold.
                    ShellSort.fillAndShuffle(quicksortArray);
                    // Fills the array with values from 0 to size - 1 and shuffles it.
                    long startTime = System.currentTimeMillis();

                    quickSort(quicksortArray, insertionUpperThreshold);
                    // The original test value was 10, so I use similar values.
                    long endTime = System.currentTimeMillis();
                    sortTime = endTime-startTime;

                    sortTimesArrayForThreshold25Thousand.add("" + (int) sortTime);
                    // We must cast sortTime (type long) to an integer.

                }
                System.out.println(sortTimesArrayForThreshold25Thousand);
                /**
                 * I placed the prints to the console here
                 * so that we can monitor the progress
                 * of the arrays' creation.
                 */
            } else if (j == 50000) {
                for (int i = 0; i < 30; i++) {
                    // Provides a better measure (an average) of the typical sort time for a given
                    // switch threshold.
                    ShellSort.fillAndShuffle(quicksortArray);
                    // Fills the array with values from 0 to size - 1 and shuffles it.
                    long startTime = System.currentTimeMillis();

                    quickSort(quicksortArray, insertionUpperThreshold);
                    // The original test value was 10, so I use similar values.
                    long endTime = System.currentTimeMillis();
                    sortTime = endTime-startTime;

                    sortTimesArrayForThreshold50Thousand.add("" + (int) sortTime);
                    // We must cast sortTime (type long) to an integer.

                }
                System.out.println(sortTimesArrayForThreshold50Thousand);
                /**
                 * I placed the prints to the console here so
                 * that we can monitor the progress of the arrays' creation.
                 */
            } else if (j == 75000) {
                for (int i = 0; i < 30; i++) {
                    // Provides a better measure (an average) of the typical sort time for a given
                    // switch threshold.
                    ShellSort.fillAndShuffle(quicksortArray);
                    // Fills the array with values from 0 to size - 1 and shuffles it.
                    long startTime = System.currentTimeMillis();

                    quickSort(quicksortArray, insertionUpperThreshold);
                    // The original test value was 10, so I use similar values.
                    long endTime = System.currentTimeMillis();
                    sortTime = endTime-startTime;

                    sortTimesArrayForThreshold75Thousand.add("" + (int) sortTime);
                    // We must cast sortTime (type long) to an integer.

                }
                System.out.println(sortTimesArrayForThreshold75Thousand);
                /**
                 * I placed the prints to the console here so that we can
                 * monitor the progress of the arrays' creation.
                 */
            }
        }


        /**
         * This block of code is intended to examine the performance
         * of Quicksort based on a small array size.
         */
        ArrayList<String> thresholdsArrayForSmallerArray = new ArrayList<String>();
        ArrayList<String> sortTimesArrayForSmallerArray = new ArrayList<String>();
        for (int i = 0; i < 1000; i += 10) {
            int insertionUpperThreshold = i;
            // This sets the size below which the Quicksort method will resort to the use of insertion sort.
            int[] quicksortArray = new int[1000];
            long sortTime = 0;
            for (int j = 0; j < 30; j++) {
                /**
                 * Due to the fact that we are looking to calculate precise values for small
                 * changes in the insertionSort switch point, we must take an average
                 * of many values (30 in this case).
                 */

                ShellSort.fillAndShuffle(quicksortArray);
                // Fills the array with values from 0 to size - 1 and shuffles it.
                long startTime = System.nanoTime();
                // This ought to take the time in nanoseconds.

                quickSort(quicksortArray, insertionUpperThreshold);
                // The original test value was 10, so I use similar values.
                long endTime = System.nanoTime();
                sortTime += endTime-startTime;
            }
            sortTime = sortTime / 30;
            // Computes the average sort time for the given switch setting and array size.
            thresholdsArrayForSmallerArray.add("" + i);
            sortTimesArrayForSmallerArray.add("" + (int) sortTime);
            // We must cast sortTime (type long) to an integer.
            System.out.println(thresholdsArrayForSmallerArray);
            System.out.println(sortTimesArrayForSmallerArray);
        }

        /**
         * This block of code is intended to examine the effects of
         * Quicksort on a large array of size 1000000.
         */
        ArrayList<String> thresholdsArray = new ArrayList<String>();
        ArrayList<String> sortTimesArray = new ArrayList<String>();
        // These two arrays will be used to make a scatter plot.

        //  The example of timing has been modified to create the following set of nested for-loops.
        /**
         * These loops essentially take the arrays listed
         * above and add threshold and sorting times in milliseconds
         * to each of them respectively.
         */
        for (int j = 0; j < 1000000; j += 100000) {
            int insertionUpperThreshold = j;
            /**
             * This is the purpose of this for loop.  It runs the quickSort for
             * different switch (to insertionSort) thresholds.
             */

            int[] quicksortArray = new int[1000000];
            long sortTime = 0;
            for (int i = 0; i < 3; i++) {
                /**
                 * Provides a better measure (an average) of the
                 * typical sort time for a given switch threshold.
                 */

                ShellSort.fillAndShuffle(quicksortArray);
                // Fills the array with values from 0 to size - 1 and shuffles it.
                long startTime = System.currentTimeMillis();

                quickSort(quicksortArray, insertionUpperThreshold);
                // The original test value was 10, so I use similar values.
                long endTime = System.currentTimeMillis();
                sortTime += endTime-startTime;
            }
            sortTime = sortTime / 3;
            // Computes the average sort time for the given switch setting and array size.
            thresholdsArray.add("" + j);
            sortTimesArray.add("" + (int) sortTime);
            // We must cast sortTime (type long) to an integer.
            System.out.println(thresholdsArray);
            System.out.println(sortTimesArray);
            /**
             * I placed the prints to the console here so that
             * we can monitor the progress of the arrays' creation.
             */
        }
    }   
}
