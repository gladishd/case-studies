import sun.misc.IOUtils;

import java.io.FileInputStream;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;


/**
 * Maze.java
 * A class for loading and printing mazes from files.
 *
 * @author Dean Gladish and William Zhang
 */
public class Maze {
    private ArrayList<ArrayList<MazeSquare>> rowList;
    private int Columns;
    private int Rows;
	 private int xCoordStart;
	 private int yCoordStart;
	 private int xCoordFinish;
	 private int yCoordFinish;
	 private int xCoord;
	 private int yCoord;
	 private boolean start;
	 private boolean end;
	 public ArrayList<String> directions = new ArrayList<String>();
	    
       
    public Maze() {
        // create an arraylist of an arraylist of MazeSquares
        rowList = new ArrayList<ArrayList<MazeSquare>>();
    }
    
    /**
     * Load in a Maze from a given file
     *
     * @param fileName the name of the file containing the maze
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

        //begin to read the first line of input.
        String[] lineParams = scanner.nextLine().split(" ");
        //the first digit would be the number of columns of the maze
        Columns = Integer.parseInt(lineParams[0]);
        //the next one is the number of rows of the maze
        Rows = Integer.parseInt(lineParams[1]);
		String[] lineTwoParams = scanner.nextLine().split(" ");
      //x and y coordinates of the starting point "s"
		xCoordStart = Integer.parseInt(lineTwoParams[0]);
		yCoordStart = Integer.parseInt(lineTwoParams[1]);
		
		String[] lineThreeParams = scanner.nextLine().split(" ");
      //x and y coordinates of the ending point "f"
		xCoordFinish = Integer.parseInt(lineThreeParams[0]);
		yCoordFinish = Integer.parseInt(lineThreeParams[1]);


      //the rest of the lines are to inform whether or not there is a left or bottom line of each Mazesquare.
        int numberOfLines = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            // use an arraylist called direction to add all the symbols.
            directions.add(line);
            numberOfLines++;
        }
        scanner.close();




    }


    public void print() {
        //print the top wall of the maze
        System.out.println(String.join("", Collections.nCopies(Columns, "+-----")) + "+");

        for (int r = 0; r < Rows; r++) {
            ArrayList<String> row1 = new ArrayList<String>();
            ArrayList<String> row2 = new ArrayList<String>();
            ArrayList<String> row3 = new ArrayList<String>();
            ArrayList<String> row4 = new ArrayList<String>();

            for (int c = 0; c < Columns; c++) {
                char directionParameter = directions.get(r).charAt(c);
                MazeSquare square = new MazeSquare(Columns, Rows, c, r, xCoordStart, yCoordStart, xCoordFinish, yCoordFinish, directionParameter);
                row1.add(Arrays.toString(square.getrow1().toArray()).replace("[", "").replace("]", ""));
                row2.add(Arrays.toString(square.getrow2().toArray()).replace("[", "").replace("]", ""));
                row3.add(Arrays.toString(square.getrow3().toArray()).replace("[", "").replace("]", ""));
                row4.add(Arrays.toString(square.getrow4().toArray()).replace("[", "").replace("]", ""));
            }



            StringBuilder sBrow1 = new StringBuilder();
            StringBuilder sBrow2 = new StringBuilder();
            StringBuilder sBrow3 = new StringBuilder();
            StringBuilder sBrow4 = new StringBuilder();

            for (String string : row1)
            {
                sBrow1.append(string);
            }
            for (String string : row2)
            {
                sBrow2.append(string);
            }
            for (String string : row3)
            {
                sBrow3.append(string);
            }
            for (String string : row4)
            {
                sBrow4.append(string);
            }

            System.out.println(sBrow1);
            System.out.println(sBrow2);
            System.out.println(sBrow3);
            System.out.println(sBrow4);
        }

    }


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