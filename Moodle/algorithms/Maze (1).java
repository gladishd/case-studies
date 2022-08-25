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



    /**
     * The following method takes in a given MazeSquare and a direction.
     * It returns a reference to the MazeSquare in that direction in
     * the maze.  Right now, this is not going to catch any exceptions.
     * @param s
     * @param direction
     * @return
     */
    public MazeSquare getNeighbor(MazeSquare s, String direction) {

        /**
         * The following initializes four integers.  The first two
         * keep track of the row and column indices of maze square s,
         * and the last two keep track of the indices of the neighbor
         * square, the underlying value of which the method returns.
         */
        int tempRow;
        int tempColumn;
        int rowIndex = 0;
        int colIndex = 0;

        /**
         * First, we need to find where s is located
         * within the doubly nested array rowList.
         */
        for (int row = 0; row < rowList.size(); row++) {
            for (int column = 0; column < rowList.get(0).size(); column++) {
                /**
                 * rowList.get(0).size() is the number of columns, since
                 * each row has the same number of columns.
                 * If an element of rowList is equal to the maze square
                 * (that is, it is the maze square), save the indices
                 * of this location.
                 */
                if (rowList.get(row).get(column) == s) {
                    tempRow = row;
                    tempColumn = column;
                    /**
                     * Now, depending on the direction in which we are to
                     * find a neighbor, we must assign different coordinates
                     * for the neighbor square which we want to return.
                     */
                    if (direction == "left") {
                        rowIndex = tempRow;
                        colIndex = tempColumn - 1;
                    } else if (direction == "right") {
                        rowIndex = tempRow;
                        colIndex = tempColumn + 1;
                    } else if (direction == "top") {
                        rowIndex = tempRow - 1;
                        colIndex = tempColumn;
                    } else if (direction == "bottom") {
                        rowIndex = tempRow + 1;
                        colIndex = tempColumn;
                    }
                }
            }
        }
        return rowList.get(rowIndex).get(colIndex);
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
         * Every square already marked as unvisited, all we
         * do is implement the algorithm described.
         *
         */

        // Create an empty stack of MazeSquare objects.
        LLStack <MazeSquare> emptyStack = new LLStack();

        // Push the start square onto the stack, and mark the start square as visited.
        int startRow = 100;
        int startCol = 300;
        for (int row = 0; row < rowList.size(); row++) {
            for (int column = 0; column < rowList.get(0).size(); column++) {
               if (rowList.get(row).get(column).isStart()) {
                  startRow = row;
                  startCol = column;
               }
            }
        }
        emptyStack.push(rowList.get(startRow).get(startCol)); 
        rowList.get(startRow).get(startCol).visit();


        /**
         * Since the maze has a rectangular shape, we can use the
         * following value currRow to determine the number of columns
         * in the maze.  This value is used for the purpose of determining
         * whether or not the square that we are looking at is located
         * on the rightmost size of the maze.
         */
        ArrayList<MazeSquare> currRow;
        currRow = rowList.get(0);

        MazeSquare T;         // T keeps track of the top of the stack.
        T = (MazeSquare) emptyStack.peek();

        while (!emptyStack.isEmpty() && !emptyStack.peek().isEnd()) {
            T = (MazeSquare) emptyStack.peek();

            /**
             * This code checks whether the stack is empty, in which case
             * we are done.  It also checks whether the current square
             * is the end square, in which case we have also reached the
             * end of the maze.
             *
             * If the square is in one of the corners, along
             * one of the sides (but not in one of the four corners),
             * or somewhere in the middle of the maze, we test
             * to see if all adjacent squares are blocked by a wall or
             * have already been visited.
             *
             * If such a square exists, we look for the "first" square
             * that is adjacent to T, unvisited, and not blocked by a
             * wall.  We push this square to the empty Stack if it
             * exists.
             */

            if (T.r == rowList.size() - 1 && T.c == currRow.size() - 1) { // lower right corner
                MazeSquare n1 = getNeighbor(T, "left");
                MazeSquare n2 = getNeighbor(T, "top");
                if ((n1.hasRightWall() || n1.isVisited()) &&
                        (n2.hasBottomWall() || n2.isVisited())) {
                    emptyStack.pop();
                } else if (!(n1.hasRightWall() || n1.isVisited())) {
                    n1.visit();
                    emptyStack.push(n1);
                } else if (!(n2.hasBottomWall() || n2.isVisited())) {
                    n2.visit();
                    emptyStack.push(n2);
                }
            } else if (T.r == rowList.size() - 1 && T.c == 0) { // lower left corner
                MazeSquare n1 = getNeighbor(T, "right");
                MazeSquare n2 = getNeighbor(T, "top");
                if ((n1.hasLeftWall() || n1.isVisited())
                        && (n2.hasBottomWall() || n2.isVisited())) {
                    emptyStack.pop();
                } else if (!(n1.hasLeftWall() || n1.isVisited())) {
                    n1.visit();
                    emptyStack.push(n1);
                } else if (!(n2.hasBottomWall() || n2.isVisited())) {
                    n2.visit();
                    emptyStack.push(n2);
                }
            } else if (T.r == 0 && T.c == currRow.size() - 1) { // upper right corner
                MazeSquare n1 = getNeighbor(T, "left");
                MazeSquare n2 = getNeighbor(T, "bottom");
                if ((n1.hasRightWall() || n1.isVisited())
                        && (n2.hasTopWall() || n2.isVisited())) {
                    emptyStack.pop();
                } else if (!(n1.hasRightWall() || n1.isVisited())) {
                    n1.visit();
                    emptyStack.push(n1);
                } else if (!(n2.hasTopWall() || n2.isVisited())) {
                    n2.visit();
                    emptyStack.push(n2);
                }
            } else if (T.r == 0 && T.c == 0) { // upper left corner

                MazeSquare n1 = getNeighbor(T, "right");
                MazeSquare n2 = getNeighbor(T, "bottom");
                if ((n1.hasLeftWall() || n1.isVisited())
                        && (n2.hasTopWall() || n2.isVisited())) {
                    emptyStack.pop();
                } else if (!(n1.hasLeftWall() || n1.isVisited())) {
                    n1.visit();
                    emptyStack.push(n1);

                } else if (!(n2.hasTopWall() || n2.isVisited())) {
                    n2.visit();
                    emptyStack.push(n2);
                }
            } else

            /**
             * If the square isn't in any of the corners, we
             * check whether it is along one of the walls of the
             * maze.
             */

                if (T.r == 0) { // top of maze
                    MazeSquare n1 = getNeighbor(T, "left");

                    MazeSquare n2 = getNeighbor(T, "right");
                    MazeSquare n3 = getNeighbor(T, "bottom");
                    if ((n1.hasRightWall() || n1.isVisited()) &&
                            (n2.hasLeftWall() || n2.isVisited()) &&
                            (n3.hasTopWall() || n3.isVisited())) {
                        emptyStack.pop();
                    } else if (!n1.hasRightWall() && !n1.isVisited()) {
                        n1.visit();
                        System.out.println(n1.isVisited());
                        emptyStack.push(n1);
                    } else if (!(n2.hasLeftWall() || n2.isVisited())) {

                        n2.visit();
                        emptyStack.push(n2);
                    } else if (!(n3.hasTopWall() || n3.isVisited())) {

                        n3.visit();
                        emptyStack.push(n3);
                    }
                } else if (T.r == rowList.size() - 1) { // bottom of maze
                    MazeSquare n1 = getNeighbor(T, "left");
                    MazeSquare n2 = getNeighbor(T, "right");
                    MazeSquare n3 = getNeighbor(T, "top");
                    if ((n1.hasRightWall() || n1.isVisited()) &&
                            (n2.hasLeftWall() || n2.isVisited()) &&
                            (n3.hasBottomWall() || n3.isVisited())) {
                        emptyStack.pop();
                    } else if (!(n1.hasRightWall() || n1.isVisited())) {
                        n1.visit();
                        emptyStack.push(n1);
                    } else if (!(n2.hasLeftWall() || n2.isVisited())) {
                        n2.visit();
                        emptyStack.push(n2);
                    } else if (!(n3.hasBottomWall() || n3.isVisited())) {
                        n3.visit();
                        emptyStack.push(n3);
                    }
                } else if (T.c == 0) { // left side of maze
                    MazeSquare n1 = getNeighbor(T, "bottom");
                    MazeSquare n2 = getNeighbor(T, "right");
                    MazeSquare n3 = getNeighbor(T, "top");
                    if ((n1.hasTopWall() || n1.isVisited()) &&
                            (n2.hasLeftWall() || n2.isVisited()) &&
                            (n3.hasBottomWall() || n3.isVisited())) {
                        emptyStack.pop();
                    } else if (!(n1.hasTopWall() || n1.isVisited())) {
                        n1.visit();
                        emptyStack.push(n1);
                    } else if (!(n2.hasLeftWall() || n2.isVisited())) {
                        n2.visit();
                        emptyStack.push(n2);
                    } else if (!(n3.hasBottomWall() || n3.isVisited())) {
                        n3.visit();
                        emptyStack.push(n3);
                    }
                } else if (T.c == currRow.size() - 1) { // right side of maze
                    MazeSquare n1 = getNeighbor(T, "left");
                    MazeSquare n2 = getNeighbor(T, "bottom");
                    MazeSquare n3 = getNeighbor(T, "top");
                    if ((n1.hasRightWall() || n1.isVisited()) &&
                            (n2.hasTopWall() || n2.isVisited()) &&
                            (n3.hasBottomWall() || n3.isVisited())) {
                        emptyStack.pop();
                    } else if (!(n1.hasRightWall() || n1.isVisited())) {
                        n1.visit();
                        emptyStack.push(n1);
                    } else if (!(n2.hasTopWall() || n2.isVisited())) {
                        n2.visit();
                        emptyStack.push(n2);
                    } else if (!(n3.hasBottomWall() || n3.isVisited())) {
                        n3.visit();
                        emptyStack.push(n3);
                    }
                } else {
                    /**
                     * If the square is not along the edge of the maze
                     * but somewhere in the interior, we run this code.
                     */
                    MazeSquare n1 = getNeighbor(T, "left");
                    MazeSquare n2 = getNeighbor(T, "bottom");
                    MazeSquare n3 = getNeighbor(T, "top");
                    MazeSquare n4 = getNeighbor(T, "right");
                    boolean noViableNeighbors = ((n1.hasRightWall() || n1.isVisited()) &&
                            (n2.hasTopWall() || n2.isVisited()) &&
                            (n3.hasBottomWall() || n3.isVisited()) &&
                            (n4.hasLeftWall() || n4.isVisited()));
                    if (noViableNeighbors) {
                        emptyStack.pop();
                    } else if (!(n1.hasRightWall() || n1.isVisited())) {
                        n1.visit();
                        emptyStack.push(n1);
                    } else if (!(n2.hasTopWall() || n2.isVisited())) {
                        n2.visit();
                        emptyStack.push(n2);
                    } else if (!(n3.hasBottomWall() || n3.isVisited())) {
                        n3.visit();
                        emptyStack.push(n3);
                    } else if (!(n4.hasLeftWall() || n4.isVisited())) {
                        n4.visit();
                        emptyStack.push(n4);
                    }
                }
        }
        return emptyStack;
    }

    public class MazeSquare {


        private int r, c;
        private boolean top, bottom, left, right,
                start, end;
        private boolean visited = false;


        // The following method checks to see if the MazeSquare is visited.
        private boolean isVisited() {
            return visited;
        }

        // The visit method changes the visit instance variable to true.
        private void visit() {
            visited = true;
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

            /**
             * Adds a visited instance variable to the MazeSquare class, the value of which
             * is initialized to false by the MazeSquare constructor.
             */
            boolean visited;

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

        /**
         * The following line of code calls the getSolution method and stores
         * the linked list stack that it returns in a variable before printing.
         */
        LLStack solution = getSolution();

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

		// If currSquare is part of the solution, mark it with *.

                if (currSquare.isStart() && currSquare.isEnd()) {
                    System.out.print("SE ");
                } else if (currSquare.isStart() && !currSquare.isEnd()) {
                    System.out.print("S  ");
                } else if (!currSquare.isStart() && currSquare.isEnd()) {
                    System.out.print("E  ");
                } else {

                    if (solution.contains(currSquare)) {
                        System.out.print("*  ");
                    } else {
                        System.out.print("   ");
                    }

                    /**
                     * If the current square being referred to is part of the solution,
                     * we have java print an asterisk instead of an empty space.
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
        System.out.println("+"); // prints the last point of the maze's floor.
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
