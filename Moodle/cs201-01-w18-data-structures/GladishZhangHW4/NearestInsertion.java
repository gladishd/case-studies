/*************************************************************************
 *  YOU DO NOT NEED TO MODIFY THIS FILE
 *
 *  Compilation:  javac NearestInsertion.java
 *  Execution:    java NearestInsertion < file.txt
 *  Dependencies: Tour.java Point.java StdDraw.java
 *
 *  Run nearest neighbor insertion heuristic for traveling
 *  salesperson problemand plot results.
 *
 *************************************************************************/

import java.io.*;
import java.util.*;
import java.util.LinkedList;
import java.util.Iterator;

public class NearestInsertion {

    public static void main(String[] args) {
        
         // create the scanner object to read from standard input. Assume UTF-8 encoding.
        Scanner scanner = new Scanner(new BufferedInputStream(System.in), "UTF-8");
        
        // get dimensions
        
        int w = scanner.nextInt();
        int h = scanner.nextInt();
        StdDraw.setCanvasSize(w, h);
        StdDraw.setXscale(0, w);
        StdDraw.setYscale(0, h);
        
        // turn on animation mode
        StdDraw.show(0);
        
        // run nearest insertion heuristic
        Tour tour = new Tour();
        while (scanner.hasNext()) {
            
            double x = scanner.nextDouble();
            double y = scanner.nextDouble();
            Point p = new Point(x, y);
            tour.insertNearest(p);

            // uncomment the 4 lines below to animate
            // StdDraw.clear();
            // tour.draw();
            // StdDraw.text(100, 0, "" + tour.distance());
            // StdDraw.show(50);
        }

        // draw to standard draw
        tour.draw();
        StdDraw.show(0);
        
       // print tour to standard output
        System.out.println("Tour distance =  "+tour.distance());
        System.out.println("Number of points = "+tour.size());
        System.out.println(tour);
    }
}
