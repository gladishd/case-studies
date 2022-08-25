import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class for ShellSort Investigation, exploring how changing the gap sequence affects run time.
 * You'll only need to look at the shellSort method. 
 * You'll modify main, and you're welcome to add any additional methods you'd like. 
 * You will likely also need to modify the way in which shellSort's multiplicative factor is specified.
 * 
 * @author Anna Rafferty
 * @author Layla Oesper
 * @author Eric Alexander
 * @author Dean Gladish
 * @author Ben Tordi
 */
public class ShellSort {
    
    /**
     * Sorts the specified array in increasing order using shell sort. Uses the gap sequence we discussed in class of dividing by 2 and adding one if even.
     * You're welcome to change this method in any way you like for your investigation, including adding parameters to the method.
     * Helper method incrementalInsertionSort handles the gap sorting of subarrays. 
     */
    public static void shellSort(int[] array, double m) {
        int gap = array.length/2; //leave this 2 alone; you will always divide in half to get first gap
        while (gap >= 1) {
            for (int start = 0; start < gap; start++) {
                //We'll "gap sort" starting at index start
                incrementalInsertionSort(array, start, gap);
            }
            double gapTemp = gap;
            gapTemp = gapTemp / m;  //  The m here indicates the multiplicative factor, which varies.
            if (gap == 1) { //  This portion allows for us to maintain the functionality of the original code.
                gap = 0;
            } else {
                gap = (int) Math.ceil(gapTemp); // Here, we cast the resulting gap to an integer after
                // the integer value has already been determined.


                if (gap == 2) {
                    gap = 1;
                }
            }

        }
    }
    
    
    
    /**
     * Helper method for shellSort. Does an insertion sort of the subarray at array[first,first+gap, first+gap*2,...]
     */
    private static void incrementalInsertionSort(int[] array, int first, int gap) {
        for (int i = first+gap; i < array.length; i += gap) {
            int nextToInsert = array[i];
            int j = i;
            while (j >= first+gap && nextToInsert < array[j-gap]) {
                array[j] = array[j-gap];
                j -= gap;
            }
            array[j] = nextToInsert;
        }
    }
    
    /**
     * Generates a pseudo-random permutation of the integers from 0 to a.length - 1.
     * See p. 139 of "Seminumerical Algorithms, 2nd edition," by Donald Knuth.
     */
    public static void fillAndShuffle(int[] a) {
        // Fill the array with the integers from 0 to a.length - 1.
        int k;
        for (k = 0; k < a.length; k++) {
            a[k] = k;
        }

        // Shuffle.
        for (k = a.length - 1; k > 0; k--) {
            int swapIndex = (int)Math.floor(Math.random() * (k + 1));
            int temp = a[k];
            a[k] = a[swapIndex];
            a[swapIndex] = temp;
        }
    }
    
  
    /**
     * Put your investigation code in main! I've included a sample, but you can delete it and replace it with your own.
     */
    public static void main(String[] args) {
        ArrayList<String> arrayOfDifferences = new ArrayList<String>();
        ArrayList<String> arrayOfMultipliers = new ArrayList<String>();


        int arraySize = 100000;  // 1000000 was the original size, but this takes too long.
        //  Based on the example, I have generated the following code:
        for (int i = 2; i < arraySize; i += 1000) {
            int[] shellSortArray = new int[arraySize];
            fillAndShuffle(shellSortArray);
            long startTime = System.currentTimeMillis();
            shellSort(shellSortArray, i); // This value is indicated by the for loop.
            long endTime = System.currentTimeMillis();
            long difference = endTime - startTime;
            arrayOfDifferences.add(difference + "");
            arrayOfMultipliers.add(i + "");
            System.out.println(arrayOfDifferences);
            System.out.println(arrayOfMultipliers);
        }
        int[] shellSortArray = new int[arraySize];
        fillAndShuffle(shellSortArray);
        long startTime = System.currentTimeMillis();
        shellSort(shellSortArray, 2.25); // This value is indicated by the for loop.
        long endTime = System.currentTimeMillis();
        long difference = endTime - startTime;
        System.out.println("The time in milliseconds for a multipler of 2.25 is " + difference);

    }
}
