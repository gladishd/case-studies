/**
 * Loops.java
 * Jeff Ondich, 2014-01-05
 * Modified: Anna Rafferty, 2016-08-24
 *
 * Demonstrates a few simple loops.
 *
 * Try a few things:
 * 1) Modify the while-loop so that it counts down from 10 to 0, printing the final 0.
 *
 * 2) Make a for-loop that counts out multiples of 3.
 * 
 * 3) Explore what happens if you change the value of k or j within the corresponding for
 *    loop. How does this differ from the similar loop in Python? What happens if you try
 *    to access k inside of the loop about j? Explain to yourself the rules for where you 
 *    can access the different variables.
 *
 * This is the Java half of a pair of parallel examples in Python and Java.
 * See loops.py.
 */

public class Loops {
    public static void main(String[] args) {
        System.out.println("Counting up with a while-loop");
        int m = 0;
        while (m < 5) {
            System.out.println(m);
            m++;
        }

        System.out.println("Counting up with a for-loop");
        for (int k = 0; k < 5; k++) {
            System.out.println(k);
        }

        System.out.println("Counting down with a for-loop");
        for (int j = 5; j > 0; j--) {
            System.out.println(j);
        }
    }
}
