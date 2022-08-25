import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * LineReader.java
 * Jeff Ondich, 2014-01-01
 * Modified: Anna Rafferty, 2016-08-24
 * Modified: Layla Oesper, 2017-08-30
 *
 * A very brief introduction to File and Scanner, excessively commented
 * for tutorial purposes.
 *
 * This is the Java half of a pair of parallel examples in Python and Java.
 * See linereader.py.
 *
 * Try a few things:
 *
 * 1. Create a small text file called somefile.txt, put it in the same
 *    directory as LineReader.java, and then compile and run LineReader.
 *    ("javac LineReader.java" followed by "java LineReader")
 *
 * 2. What happens when you change the inputFilePath variable to "nonexistent.txt"?
 *
 * 3. What happens when you remove the "= null" from the Scanner declaration?
 *
 * 4. What happens when you remove the try/catch blocks and just do
 *    "Scanner scanner = new Scanner(inputFile)"?
 *
 * 5. Visit the java String documentation and play around with some String
 *    methods other than toUpperCase.
 */
public class LineReader {
    public static void main(String[] args) {
        // Create a File object connected to the file you want to read.
        String inputFilePath = "somefile.txt";
        File inputFile = new File(inputFilePath);

        // Create a Scanner object connected to your file. This is where
        // the JVM tries to actually open the file, and thus this is
        // where an exception can occur. That's why there's a try/catch
        // block to catch the exception. It allows the programmer to
		// build in a way for the program to recover from the error (in
		// this case, by just printing the error for the user).
        Scanner scanner = null;
        try {
            scanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            System.err.println(e);
            System.exit(1);
        }

        // Get one line at a time from the file, and print each line in upper
        // case to standard output.
        int numberOfLines = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line.toUpperCase());
            numberOfLines++;
        }
        scanner.close();
        System.out.println("\nNumber of lines in file: " + numberOfLines);

    }
}
