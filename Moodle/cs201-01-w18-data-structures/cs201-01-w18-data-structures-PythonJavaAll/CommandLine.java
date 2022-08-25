import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * CommandLine.java
 * Jeff Ondich, 2014-01-05
 * Modified: Anna Rafferty, 2016-08-24
 * Modified: Layla Oesper, 2017-08-30
 *
 * A copy of LineReader.java that takes the input file path from the command
 * line rather than from a hard-coded string. For example, I might run this like:
 * java CommandLine somefile.txt
 * "somefile.txt" is a command line argument (an argument entered in terminal)
 * and the program will read this and use it as the filename to read from.
 * Note that you've seen command line arguments in other contexts - for example,
 * you've probably typed something likd "ls Documents" to list all the files
 * in the directory named Documents.
 * 
 * Once you've tried running this program, try modifying it to so that if there
 * is more than one command line argument, it prints out the second one.
 * 
 * This is the Java half of a pair of parallel examples in Python and Java.
 * See commandline.py.
 */
public class CommandLine {
    public static void main(String[] args) {
        // START CHANGES
        // This is the only part that's different from LineReader.java
        if (args.length == 0) {
            System.err.println("Usage: java CommandLine inputFilePath");
            System.exit(1);
        }
        String inputFilePath = args[0];
        // END CHANGES

        File inputFile = new File(inputFilePath);

        Scanner scanner = null;
        try {
            scanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            System.err.println(e);
            System.exit(1);
        }

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
