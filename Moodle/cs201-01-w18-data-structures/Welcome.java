import java.util.Scanner;
import java.lang.StringBuilder;
import java.util.StringJoiner;

/**
 * Welcome.java
 * Dean Gladish, Carleton College, 2018-01-08
 * Homework 2 for C.S. 201.01
 * Professor Eric Alexander
 * A Welcome to Data Structures app that asks the user for a name and an integer.  
 */
 
public class Welcome {
    public static void main(String[] args) {                             // This is the main method.  
        int integer = 0;                                                 // This initializes an integer and sets its value to zero.  
        System.out.println("Welcome to CS 201: Data Structures!");
        Scanner scanner = new Scanner(System.in);                        // Here a new scanner is created.  
        System.out.print("What is your name: ");
        String name = scanner.next();                                    // Sets the value of name to the user input

        boolean inputIsInteger;                                          // Initializes a boolean
        do {
            inputIsInteger = true;
            try {                                                        // Ask the user for an integer and see if the input throws an exception.
                scanner = new Scanner(System.in);
                System.out.print("Enter an integer: ");
                integer = scanner.nextInt();
            }
            catch (Exception e) {
                inputIsInteger = false;                                  // If the user returns something of a different type, then an exception
            }                                                            // is thrown and the input is not an integer.  
        } while (inputIsInteger == false);                               // Continue prompting the user until they return an integer.  

        System.out.println("");
        System.out.println("Welcome " + name);
        System.out.println("Your name backwards is " + new StringBuilder(name).reverse().toString());  // Uses StringBuilder to reverse the name
        if (integer > 0) {                                               // If the integer is positive, 
            for (int i = 1; i <= integer; i++) {                         // add n iterations of each integer n (that is less than or equal  
                StringJoiner newString = new StringJoiner(",");          // to the integer) to the string using StringJoiner.
                for (int k = 1; k <= i; k++) {
                    newString.add(String.valueOf(i));
                }
                System.out.println(newString);
            }
        } else if (integer < 0) {                                        // If the integer is not positive, do the same thing essentially 
            for (int i = -1; i >= integer; i--) {                        // except add n iterations of each integer n that is greater than or 
                StringJoiner newString = new StringJoiner (",");         // equal to said integer.  
                for (int k = -1; k >= i; k--) {
                    newString.add(String.valueOf(i));
                }
                System.out.println(newString);
            }
        } else if (integer == 0) {                                       // Tells the user that a triangle of height 0 cannot be printed
            System.out.println("Cannot print a triangle of height 0");
        }

    }
}