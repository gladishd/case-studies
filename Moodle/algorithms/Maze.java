import java.io.*;
import java.util.*;

/**
 * Maze.java
 * Solution to the first Maze Assignment (HW3).
 * CS 201: Data Structures - Winter 2018
 *
 * @author Eric Alexander
 * @author Dean Gladish
 * @author William Zhang
 */
public class Maze {
    private ArrayList<ArrayList<MazeSquare>> rowList;
    private int w, h;
    private int startRow, startCol, endRow, endCol;


    // I am including MazeSquare as an inner class
    // to simplify the file structure a little bit.



    private class MazeSquare {

        private int r, c;
        private boolean top, bottom, left, right,
                start, end;


        // The following method checks to see if the MazeSquare is visited.
        private boolean isVisited() {
            return visited;
        }

        // The visit method changes the visit instance variable to true.
        private void visit() {
            visited = true;
        }

        /**
         * The following method takes in a given MazeSquare and a direction.
         * It returns a reference to the MazeSquare in that direction in
         * the maze.  Right now, this is not going to catch any exceptions.
         * @param s
         * @param direction
         * @return
         */
        private MazeSquare getNeighbor(MazeSquare s, String direction) {
            int tempRow;
            int tempColumn;
            MazeSquare neighborSquare = null;
            /**
             * We have initialized a neighbor square of class MazeSquare, which is
             * currently not set to any value.
             */
            /**
             * First, we need to find where s is located within the doubly nested array rowList.
             */
            for (int row = 0; row < rowList.size(); row++) {
                for (int column = 0; column < rowList.get(0).size(); column++) {
                    /**
                     * rowList.get(0).size() is the number of columns.
                     * If a single element of rowList is equal to the maze square,
                     * save the indices of the row and column (the location of the
                     * square).
                     */
                    if (rowList.get(row).get(column) == s) {
                        tempRow = row; // Assigns integer values to tempRow and tempColumn.
                        tempColumn = column;
                        /**
                         * Now, depending on the direction in which we are to
                         * find a neighbor, we must assign different maze square values
                         * to the neighbor square that we have initialized.
                         */
                        if (direction == "left") {
                            neighborSquare = rowList.get(tempRow).get(tempColumn - 1);
                        } else if (direction == "right") {
                            neighborSquare = rowList.get(tempRow).get(tempColumn + 1);
                        } else if (direction == "top") {
                            neighborSquare = rowList.get(tempRow - 1).get(tempColumn);
                        } else if (direction == "bottom") {
                            neighborSquare = rowList.get(tempRow + 1).get(tempColumn);
                        }
                    }
                }
            }
            return neighborSquare;
        }

        /**
         * Computes and returns a solution to this maze. If there are multiple solutions,
         * only one is returned, and getSolution() makes no guarantees about which one. However,
         * the returned solution will not include visits to dead ends or any backtracks, even if
         * backtracking occurs during the solution process.
         *
         * @return a LLStack of MazeSquare objects containing the sequence of squares visited
         * to go from the start square (bottom of the stack) to the finish square (top of the stack).
         */
        public LLStack<MazeSquare> getSolution() {
            /**
             * Checks if a square is part of a possible solution.  Implements the algorithm described.
             */
            // Every square is already marked as "unvisited".
            // Create an empty stack of MazeSquare objects.
            LLStack emptyStack = new LLStack();
            // Push the start square onto the stack, and mark the start square as visited.
            emptyStack.push(rowList.get(0).get(0));

            return null;
        }

        private MazeSquare(int r, int c,
                           boolean top, boolean bottom, boolean left, boolean right,
                           boolean start, boolean end) {
            this.r = r;
            this.c = c;
            this.top = top;
            this.bottom = bottom;
            this.left = left;
            this.right = right;
            this.start = start;
            this.end = end;
            boolean visited;
            /**
             * Adds a visited instance variable to the MazeSquare class, the value of which
             * is initialized to false by the MazeSquare constructor.
             */
        }
        boolean hasTopWall() {
            return top;
        }
        boolean hasBottomWall() {
            return bottom;
        }
        boolean hasLeftWall() {
            return left;
        }
        boolean hasRightWall() {
            return right;
        }
        boolean isStart() {
            return start;
        }
        boolean isEnd() {
            return end;
        }
        int getRow() {
            return r;
        }
        int getCol() {
            return c;
        }
    }

    /**
     * Construct a new Maze
     */
    public Maze() {
        rowList = new ArrayList<ArrayList<MazeSquare>>();
    }

    /**
     * Load Maze in from given file
     *
     * @param fileName the name of the file containing the Maze structure
     */
    public void load(String fileName) {

        // Create a scanner for the given file
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            System.err.println(e);
            System.exit(1);
        }

        // First line of file is "w h"
        String[] lineParams = scanner.nextLine().split(" ");
        w = Integer.parseInt(lineParams[0]);
        h = Integer.parseInt(lineParams[1]);

        // Second line of file is "startCol startRow"
        lineParams = scanner.nextLine().split(" ");
        startCol = Integer.parseInt(lineParams[0]);
        startRow = Integer.parseInt(lineParams[1]);

        // Third line of file is "endCol endRow"
        lineParams = scanner.nextLine().split(" ");
        endCol = Integer.parseInt(lineParams[0]);
        endRow = Integer.parseInt(lineParams[1]);

        // Read the rest of the lines (L or | or _ or -)
        String line;
        int rowNum = 0;
        boolean top, bottom, left, right;
        boolean start, end;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            rowList.add(new ArrayList<MazeSquare>());

            // Loop through each cell, creating MazeSquares
            for (int i = 0; i < line.length(); i++) {
                // For top, check row above, if there is one
                if (rowNum > 0) {
                    top = rowList.get(rowNum-1).get(i).hasBottomWall();
                } else {
                    top = true;
                }

                // For right, check cell to the right, if there is one
                if (i < line.length() - 1 ) {
                    char nextCell = line.charAt(i+1);
                    if (nextCell == 'L' || nextCell == '|') {
                        right = true;
                    } else {
                        right = false;
                    }
                } else {
                    right = true;
                }

                // For left and bottom, switch on the current character
                switch (line.charAt(i)) {
                    case 'L':
                        left = true;
                        bottom = true;
                        break;
                    case '_':
                        left = false;
                        bottom = true;
                        break;
                    case '|':
                        left = true;
                        bottom = false;
                        break;
                    case '-':
                        left = false;
                        bottom = false;
                        break;
                    default:
                        left = false;
                        bottom = false;
                }

                // Check to see if this is the start or end spot
                start = startCol == i && startRow == rowNum;
                end = endCol == i && endRow == rowNum;

                // Add a new MazeSquare
                rowList.get(rowNum).add(new MazeSquare(rowNum, i, top, bottom, left, right, start, end));
            }

            rowNum++;
        }
    }

    /**
     * Print the Maze to the Console
     */
    public void print() {
	
	// YOUR CODE WILL GO HERE:
        // Trying to figure out what the code does:
        System.out.println(rowList);
        System.out.println(rowList.get(0));
        System.out.println(rowList.get(1)); // rowList is a String[] with subarrays that symbolize each row.

        System.out.println(rowList.get(1).indexOf(rowList.get(1).get(1))); // testing the .indexOf method for arrays.
        System.out.println(rowList.size()); // this prints out the number of rows.
        System.out.println(rowList.get(0).get(0).isVisited());
        System.out.println(rowList.get(3).get(2).isVisited());
	// Before printing, use your getSolution() method
	//  to get the solution to the Maze.
	
        ArrayList<MazeSquare> currRow;  // This code keeps track of the current row.
        MazeSquare currSquare;          // currSquare keeps track of the current square.

        // Print each row of text based on top and left
        for (int r = 0; r < rowList.size(); r++) {
            currRow = rowList.get(r);

            // First line of text: top wall
            for (int c = 0; c < currRow.size(); c++) {
                System.out.print("+");                // prints first point on the maze's ceiling.
                if (currRow.get(c).hasTopWall()) {
                    System.out.print("-----");
                } else {
                    System.out.print("     ");
                }
            }
            System.out.println("+");                 // prints last point on the maze's ceiling.

            // Second line of text: left wall then space
            for (int c = 0; c < currRow.size(); c++) {
                if (currRow.get(c).hasLeftWall()) {
                    System.out.print("|");
                } else {
                    System.out.print(" ");
                }
                System.out.print("     ");
            }
            System.out.println("|");

            // Third line of text: left wall, then space, then start/end/sol, then space
            for (int c = 0; c < currRow.size(); c++) {
                currSquare = currRow.get(c);

                if (currSquare.hasLeftWall()) {
                    System.out.print("|");
                } else {
                    System.out.print(" ");
                }

                System.out.print("  ");

		// YOU WILL ADD CODE HERE
		// If currSquare is part of the solution, mark it with *
                if (currSquare.isStart() && currSquare.isEnd()) {
                    System.out.print("SE ");
                } else if (currSquare.isStart() && !currSquare.isEnd()) {
                    System.out.print("S  ");
                } else if (!currSquare.isStart() && currSquare.isEnd()) {
                    System.out.print("E  ");
                } else {
                    System.out.print("   ");   // This line should be the location at which to add an asterisk.
                    /**
                     * If the current square being referred to is part of the solution, we should have java print
                     * an asterisk, *  , instead of empty spaces.
                     *
                     */
                }
            }
            System.out.println("|");

            // Fourth line of text: same as second
            for (int c = 0; c < currRow.size(); c++) {
                if (currRow.get(c).hasLeftWall()) {
                    System.out.print("|");
                } else {
                    System.out.print(" ");
                }
                System.out.print("     ");
            }
            System.out.println("|");
        }

        // Print last row of text as straight wall
        for (int c = 0; c < rowList.get(0).size(); c++) {
            System.out.print("+-----");
        }
        System.out.println("+");                  // prints the last point of the maze's floor.
    }



    // This main program acts as a simple unit test for the
    // load-from-file and print-to-System.out Maze capabilities.
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Maze mazeFile");
            System.exit(1);
        }

        Maze maze = new Maze();
        maze.load(args[0]);
        maze.print();
    }
}
