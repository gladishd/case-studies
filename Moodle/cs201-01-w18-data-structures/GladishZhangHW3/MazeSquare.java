import java.util.ArrayList;

/**
 * MazeSquare.java
 * A helper class for HW1 maze solving assignment.
 * Represents a single square within a rectangular maze.
 *
 * @author Dean Gladish and William Zhang
 */
public class MazeSquare {
    
    private ArrayList<String> row1;
    private ArrayList<String> row2;
    private ArrayList<String> row3;
    private ArrayList<String> row4;


    public MazeSquare(int Columns, int Rows, int xCoord, int yCoord, int xCoordStart, int yCoordStart, int xCoordFinish, int yCoordFinish, char directionParameter) {
        // YOUR CODE HERE
        row1 = new ArrayList<String>();
        row2 = new ArrayList<String>();
        row3 = new ArrayList<String>();
        row4 = new ArrayList<String>();


        // if it is the mazesquare is on the top left
        if (xCoord == 0 && yCoord == 0) {        
            row1.add("|     ");
            // if the Mazesquare contains the starting point
            if (xCoord == xCoordStart && yCoord == yCoordStart) {
                row2.add("|  S  ");
            } 
            // if it contains the final point
            else if (xCoord == xCoordFinish && yCoord == yCoordFinish) {
                row2.add("|  F  ");
            } 
            // if it contains nothing
            else {
                row2.add("|     ");
            }
            
            row3.add("|     ");
            // if the MazeSquare has bottomline
            if (directionParameter == "L".charAt(0) || directionParameter == "_".charAt(0)) {
                row4.add("+-----");
            } 
            // of the MazeSqaure has no bottomline
            else {
                row4.add("+     ");
            }

        } 
        //if the Mazesquare is on the top row, but is neither the first nor the last of the first row
        else if (xCoord != 0 && xCoord != Columns - 1 && yCoord == 0) { 
            // if it has a bottom and a left wall(I will call it process L for the simplicity)
            if (directionParameter == "L".charAt(0)) {
                //for the first roll
                row1.add("|     ");
                //if it has starting point
                if (xCoord == xCoordStart && yCoord == yCoordStart) {
                    row2.add("|  S  ");
                } 
                //if it has final point
                else if (xCoord == xCoordFinish && yCoord == yCoordFinish) {
                    row2.add("|  F  ");
                } 
                // if it has nothing
                else {
                    row2.add("|     ");
                }
                //for the row3 and row4
                row3.add("|     ");
                row4.add("+-----");
            } 
            // if it has a left wall(I will call it process I for the simplicity)
            else if (directionParameter == "|".charAt(0)) {
                //for row1
                row1.add("|     ");
                //if it has a starting point
                if (xCoord == xCoordStart && yCoord == yCoordStart) {
                    row2.add("|  S  ");
                //if it has a final point
                } else if (xCoord == xCoordFinish && yCoord == yCoordFinish) {
                    row2.add("|  F  ");
                } 
                //if it has nothing
                else {
                    row2.add("|     ");
                }
                //for row3 and row4
                row3.add("|     ");
                row4.add("+     ");
            } 
            // if it has a bottom wall(I will call it process _ for the simplicity)
            else if (directionParameter == "_".charAt(0)) {
                row1.add("      ");
                if (xCoord == xCoordStart && yCoord == yCoordStart) {
                    row2.add("   S  ");
                } else if (xCoord == xCoordFinish && yCoord == yCoordFinish) {
                    row2.add("   F  ");
                } else {
                    row2.add("      ");
                }
                row3.add("      ");
                row4.add("+-----");
            } 
            // if it has no bottom and left wall(I will call it process 0 for the simplicity)
            else {
                row1.add("      ");
                if (xCoord == xCoordStart && yCoord == yCoordStart) {
                    row2.add("   S  ");
                } else if (xCoord == xCoordFinish && yCoord == yCoordFinish) {
                    row2.add("   F  ");
                } else {
                    row2.add("      ");
                }
                row3.add("      ");
                row4.add("+     ");
            }

        } 
        
        // if it is a Mazesquare in the upper right of the maze
        else if (xCoord == Columns - 1 && yCoord == 0) { 
            //run process L
            if (directionParameter == "L".charAt(0)) {
                //it is different than other MazeSquare because it has a right wall of the maze
                row1.add("|     |");
                if (xCoord == xCoordStart && yCoord == yCoordStart) {
                    row2.add("|  S  |");
                } else if (xCoord == xCoordFinish && yCoord == yCoordFinish) {
                    row2.add("|  F  |");
                } else {
                    row2.add("|     |");
                }
                // it is different because it has a right waqll of the maze
                row3.add("|     |");
                row4.add("+-----+");
            } 
            //run process I
            else if (directionParameter == "|".charAt(0)) {
                row1.add("|     |");
                if (xCoord == xCoordStart && yCoord == yCoordStart) {
                    row2.add("|  S  |");
                } else if (xCoord == xCoordFinish && yCoord == yCoordFinish) {
                    row2.add("|  F  |");
                } else {
                    row2.add("|     |");
                }
                row3.add("|     |");
                row4.add("+     +");
            } 
            // run process _
            else if (directionParameter == "_".charAt(0)) {
                row1.add("      |");
                if (xCoord == xCoordStart && yCoord == yCoordStart) {
                    row2.add("   S  |");
                } else if (xCoord == xCoordFinish && yCoord == yCoordFinish) {
                    row2.add("   F  |");
                } else {
                    row2.add("      |");
                }
                row3.add("      |");
                row4.add("+-----+");
            } 
            //run process 0
            else {
                row1.add("      |");
                if (xCoord == xCoordStart && yCoord == yCoordStart) {
                    row2.add("   S  |");
                } else if (xCoord == xCoordFinish && yCoord == yCoordFinish) {
                    row2.add("   F  |");
                } else {
                    row2.add("      |");
                }
                row3.add("      |");
                row4.add("+     +");
            }


        } 
        // if the mazesquare is on the first column but not the the toppest one
        else if (xCoord == 0 && yCoord != 0 && yCoord != Rows - 1) { 
            //for all row1, do the following
            row1.add("|     ");
            //check if it contains the starting or the final point or nothing
            if (xCoord == xCoordStart && yCoord == yCoordStart) {
                row2.add("|  S  ");
            } else if (xCoord == xCoordFinish && yCoord == yCoordFinish) {
                row2.add("|  F  ");
            } else {
                row2.add("|     ");
            }
            // for all row3, do the following
            row3.add("|     ");
            // if it has a bottom wall, do the following
            if (directionParameter == "L".charAt(0) || directionParameter == "_".charAt(0)) {
                row4.add("+-----");
            } 
            // if has no bottom wall
            else {
                row4.add("+     ");
            }

        } 
        //if the Mazesquare is not the first roll, or the last roll, or the first column, or the last column
        else if (xCoord != 0 && xCoord != Columns - 1 && yCoord != 0 && yCoord != Rows - 1) {
            //run process L
            if (directionParameter == "L".charAt(0)) {
                row1.add("|     ");
                if (xCoord == xCoordStart && yCoord == yCoordStart) {
                    row2.add("|  S  ");
                } else if (xCoord == xCoordFinish && yCoord == yCoordFinish) {
                    row2.add("|  F  ");
                } else {
                    row2.add("|     ");
                }
                row3.add("|     ");
                row4.add("+-----");
            } 
            //run process I
            else if (directionParameter == "|".charAt(0)) {
                row1.add("|     ");
                if (xCoord == xCoordStart && yCoord == yCoordStart) {
                    row2.add("|  S  ");
                } else if (xCoord == xCoordFinish && yCoord == yCoordFinish) {
                    row2.add("|  F  ");
                } else {
                    row2.add("|     ");
                }
                row3.add("|     ");
                row4.add("+     ");
            } 
            // run process _
            else if (directionParameter == "_".charAt(0)) {
                row1.add("      ");
                if (xCoord == xCoordStart && yCoord == yCoordStart) {
                    row2.add("   S  ");
                } else if (xCoord == xCoordFinish && yCoord == yCoordFinish) {
                    row2.add("   F  ");
                } else {
                    row2.add("      ");
                }
                row3.add("      ");
                row4.add("+-----");
            } 
            // run process 0
            else {
                row1.add("      ");
                if (xCoord == xCoordStart && yCoord == yCoordStart) {
                    row2.add("   S  ");
                } else if (xCoord == xCoordFinish && yCoord == yCoordFinish) {
                    row2.add("   F  ");
                } else {
                    row2.add("      ");
                }
                row3.add("      ");
                row4.add("+     ");
            }
        } 
        //if it is on the right column and it is not on the first ot the last row
        else if (xCoord == Columns - 1 && yCoord != 0 && yCoord != Rows - 1) { 
            // run process L
            if (directionParameter == "L".charAt(0)) {
                row1.add("|     |");
                if (xCoord == xCoordStart && yCoord == yCoordStart) {
                    row2.add("|  S  |");
                } else if (xCoord == xCoordFinish && yCoord == yCoordFinish) {
                    row2.add("|  F  |");
                } else {
                    row2.add("|     |");
                }
                row3.add("|     |");
                row4.add("+-----+");
            } 
            // run process I
            else if (directionParameter == "|".charAt(0)) {
                row1.add("|     |");
                if (xCoord == xCoordStart && yCoord == yCoordStart) {
                    row2.add("|  S  |");
                } else if (xCoord == xCoordFinish && yCoord == yCoordFinish) {
                    row2.add("|  F  |");
                } else {
                    row2.add("|     |");
                }
                row3.add("|     |");
                row4.add("+     +");
            } 
            // run process _
            else if (directionParameter == "_".charAt(0)) {
                row1.add("      |");
                if (xCoord == xCoordStart && yCoord == yCoordStart) {
                    row2.add("   S  |");
                } else if (xCoord == xCoordFinish && yCoord == yCoordFinish) {
                    row2.add("   F  |");
                } else {
                    row2.add("      |");
                }
                row3.add("      |");
                row4.add("+-----+");
            } 
            // run process 0
            else {
                row1.add("      |");
                if (xCoord == xCoordStart && yCoord == yCoordStart) {
                    row2.add("   S  |");
                } else if (xCoord == xCoordFinish && yCoord == yCoordFinish) {
                    row2.add("   F  |");
                } else {
                    row2.add("      |");
                }
                row3.add("      |");
                row4.add("+     |");
            }


        } 
        // if it is the lowest one on the first colomn
        else if (xCoord == 0 && yCoord == Rows - 1) { 

            row1.add("|     ");
            if (xCoord == xCoordStart && yCoord == yCoordStart) {
                row2.add("|  S  ");
            } else if (xCoord == xCoordFinish && yCoord == yCoordFinish) {
                row2.add("|  F  ");
            } else {
                row2.add("|     ");
            }
            row3.add("|     ");
            row4.add("+-----");
        } 
        // if it is on the lowest row but not the first and not the last of that row
        else if (xCoord != 0 && xCoord != Columns - 1 && yCoord != 0 && yCoord == Rows - 1) { // Bottom
            //run process L
            if (directionParameter == "L".charAt(0) || directionParameter == "|".charAt(0)) {
                row1.add("|     ");
                if (xCoord == xCoordStart && yCoord == yCoordStart) {
                    row2.add("|  S  ");
                } else if (xCoord == xCoordFinish && yCoord == yCoordFinish) {
                    row2.add("|  F  ");
                } else {
                    row2.add("|     ");
                }
                row3.add("|     ");
                row4.add("+-----");
            } 
            //run process 0
            else {
                row1.add("      ");
                if (xCoord == xCoordStart && yCoord == yCoordStart) {
                    row2.add("   S  ");
                } else if (xCoord == xCoordFinish && yCoord == yCoordFinish) {
                    row2.add("   F  ");
                } else {
                    row2.add("      ");
                }
                row3.add("      ");
                row4.add("+-----");
            }
        } 
        //if it is the lowest one on the right column
        else if (xCoord != 0 && xCoord == Columns - 1 && yCoord != 0 && yCoord == Rows - 1) { // Lower right
            // run process L
            if (directionParameter == "L".charAt(0) || directionParameter == "|".charAt(0)) {
                row1.add("|     |");
                if (xCoord == xCoordStart && yCoord == yCoordStart) {
                    row2.add("|  S  |");
                } else if (xCoord == xCoordFinish && yCoord == yCoordFinish) {
                    row2.add("|  F  |");
                } else {
                    row2.add("|     |");
                }
                row3.add("|     |");
                row4.add("+-----+");
            } 
            // run Process 0
            else {
                row1.add("      |");
                if (xCoord == xCoordStart && yCoord == yCoordStart) {
                    row2.add("   S  |");
                } else if (xCoord == xCoordFinish && yCoord == yCoordFinish) {
                    row2.add("   F  |");
                } else {
                    row2.add("      |");
                }
                row3.add("      |");
                row4.add("+-----+");
            }
        }



    }

    public ArrayList<String> getrow1(){
        return row1;
    }
    public ArrayList<String> getrow2(){
        return row2;
    }
    public ArrayList<String> getrow3(){
        return row3;
    }
    public ArrayList<String> getrow4(){
        return row4;
    }


   
    public boolean hasTopWall() {
        
        return false;
    }

}