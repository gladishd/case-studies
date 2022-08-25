 /**
 * This class creates a Tour of Points using a 
 * Linked List implementation.  The points can
 * be inserted into the list using two heuristics.
 *
 * @author Dean Gladish, modified code 01-28-2018
 * @author William Zhang, modified code 01-28-2018
 * @author Eric Alexander, modified code 01-12-2018
 * @author Layla Oesper, modified code 09-22-2017
 * Also, the files should be run as: java NearestInsertion < textFile.txt.
 */

import java.io.*;
import java.util.*;


 public class Tour {

    /** 
     * A helper class that defines a single node for use in a tour.
     * A node consists of a Point, representing the location of that
     * city in the tour, and a pointer to the next Node in the tour.
     */
    public double totalDistance; //create a double variable to keep track of the total distance.
    private class Node {
        private Point p;
        private Node next;
        public double minDist; /** a double variable that keeps track of the minimum distance                                         *found thus far between Point p and the nodes in the linked list. 
                                *between point p and the current node we are trying to compare with.
                                */
        
        public double distNext;/** a double variable to keep track of the distance of
                                *point p to the current node we are trying to compare with
                                */
	
        /** 
         * Constructor creates a new Node at the given Point newP
         * with an initial next value of null.
         * 
         * @param newP the point to associate with the Node.
         */
        public Node(Point newP) {
            p = newP;
            next = null;
        }

        /** 
         * Constructor creates a new Node at the given Point newP
         * with the specified next node.
         *
         * @param newP the point to associate with the Node.
         * @param nextNode the nextNode this node should point to.
         */
        public Node(Point newP, Node nextNode) {
            p = newP;
            next = nextNode;
        }
          
    } // End Node class
    

    // Tour class Instance variables
    private Node head;
    private int size; 
    //number of nodes
    
    /**
     * Constructor for the Tour class.  By default sets head to null.
     */
    public Tour() {
        head = null;
        size = 0; // Sets the initial size of the tour to 0.  
        totalDistance = 0.0; // sets the initial total distance of the tour to 0.0
    }

    public void insertSmallest(Point p) {
        Double minDist = 0.0;  // Set the initial value of minDist to 0.0.
        Double distNext = 1.0; // Set the initial value of distNext to 1.0.
        Node temp = head;      /**temp is a node that is used to keep track of the current node we are
                                * comparing with the point p.
                                */
        Node minValue = null;  /**minValue is a node to keep track of the node on the left side of the gap
                                * in which inserting temp would result in the least distance increase.
                                */
        if (head == null) {    // If the point p is the first point, then assign point p to the head.
            head = new Node(p);
            size++;
        } else if (head.next == null) { /**if there is only one node in the list,
                                         * set minDist to the distance from p to head.
                                         */
            temp.next = new Node(p);
            size++;
            minDist = 0.0;              // reset minDist
        }
        else { // run this code only if there is more than two nodes in the linked list.
            /** Set the minimum distance equal to the distance from head.p to p to head.next.p minus
             *  the distance from head.p to head.next.p.
             */
            minDist = Math.abs(p.distanceTo(head.p)) +
                    Math.abs(p.distanceTo(head.next.p)) -
                    Math.abs(head.p.distanceTo(head.next.p));
            while (temp.next!= null) {     /** while there are still PAIRS of nodes in the tour
                                            *  that have not been compared with p
                                            */
                /** Set distNext to the distance from temp.p to p to temp.next.p minus
                 *  the distance from temp.p to temp.next.p.
                 */
                distNext = Math.abs(p.distanceTo(temp.p)) +
                        Math.abs(p.distanceTo(temp.next.p)) -
                        Math.abs(temp.p.distanceTo(temp.next.p));
                /** If the distance from temp.p to p to temp.next.p minus
                 *  the distance from temp.p to temp.next.p is less than minDist
                 */
                if (distNext <= minDist) {
                    minDist = distNext;   // Make that distance the new minDist
                    minValue = temp;        // and save a reference to the current node.
                    /**  That is, save a reference to the left node of the current pair
                     *   being examined.
                     */
                }
                temp = temp.next; // set temp to the next node and repeat the comparing process.
            }
            /** after comparing all nodes, insert p in between
             *  two nodes.
             */
            minValue.next = new Node(p, minValue.next);
            size++;
        }
    }
    public void insertNearest(Point p) {
        if (head == null) {        // If the point p is the first point, then assign point p to the head. 
            head = new Node(p);
            size++;
        }
        
        else {
            Double minDist = 0.0;  // Set the initial value of minDist to 0.0.
            Double distNext = 0.0; // Set the initial value of distNext to 0.0.
            Node temp = head;      /** temp is a node that is used to keep track of the current node we are
                                    *  comparing with the point p.
                                    */
            Node minValue = null;   /** minValue is a node to keep track of the node that has the minimum                              *  distance with point p.
                                     */
            if (head.next == null) { // if there is only one node in the list

                minDist = p.distanceTo(temp.p); //set minDist to the distance from p to head
                temp.next = new Node(p);        // insert p as a new node after head
                size++;
                minDist = 0.0;                  // reset minDist
            }
            
            else { // run only if there is more than two nodes in the current linked list
                
                minDist = p.distanceTo(head.p);
                while (temp != null) { /**while there are still nodes in the tour that have not been compared 
                                        * with point p
                                        */
                    
                    distNext = p.distanceTo(temp.p); //set distNext to the distance between p and current node
                    
                    if (distNext <= minDist) { // If distance between p and current node is smaller than minDist
                        minDist = distNext;            // Make that distance the new minDist.
                        minValue = temp;               // and save a reference to the current node.     
                    }
                    temp=temp.next; //set temp to the next node and repeat the comparing process
                }

                if (minValue.next == null) { // after comparing all nodes,if p is inserted at the end of the list
                    minValue.next = new Node(p); 
                    
                }
                else {
                    minValue.next = new Node(p, minValue.next);  // otherwise, insert p in between two nodes
                }
                size++;
                
   
            }
        }
    }


     public String toString() {
         /** This method creates a node temp and assigns it to point to head.
          *  As long as the next node of the current node we are referring to is not null,
          *  it adds its underlying point value to a StringJoiner and then
          *  moves to the next node.  It then converts the StringJoiner to a string
          *  and separates all the points out individually, at which point it
          *  combines them again into a new string that contains line breaks.
          */

        Node temp = head;
        StringJoiner string = new StringJoiner(", ", "", "");
        string.add(temp.p.toString());
        while (temp.next != null) {
            string.add(temp.next.p.toString());
            temp = temp.next;

        }
        String tourString = string.toString();
        String[] splitString = tourString.split(", ", size*2);
        String lineBreaksString = "";
        for (int i = 0; i < size*2; i++) {

            lineBreaksString += splitString[i] + ", " + splitString[i+1] + "\n";
            i++;
        }
        return lineBreaksString;
         // return tourString; // uncomment this to print the tour in a single line
    }

    
    public void draw() {
        /**What this method does is call the draw (draws a point)
         * and drawTo (draws lines between points) methods from the
         * Point class.  It uses a while loop in order to draw the point underlying
         * the node after the node that we are referring to, draw a line between
         * this node and the following node, and move along the tour.  Outside of this
         * while loop the method draws a line from the last node to the head node.
         */
        Node temp = head;
        temp.p.draw();
        while (temp.next != null) {
            temp.next.p.draw();
            temp.p.drawTo(temp.next.p);
            temp = temp.next;
        }
        temp.p.drawTo(head.p);



    }
    
    public int size() {
        return size; /** returns size (an integer), a value that is calculated
                      *  during the creation of the tour so as to decrease the
                      *  processing requirement on the computer's part.
                      */
    }
    
    public double distance() { 
        Node temp = head; // 
        while(temp.next != null) { // while the next node of the current node we are referring to is not null
            totalDistance += temp.p.distanceTo(temp.next.p); /**calculate the distance of two adjacent nodes, and
                                                              * add it to the totalDistance
                                                              */
            temp = temp.next;
        }
        if (temp.next == null) { // if the current node is the last one
            totalDistance += temp.p.distanceTo(head.p); // calculate the distance of the last one to the first node
        }
        return totalDistance;
    }
   
    public static void main(String[] args) {
        /* Everything seems great with this assignment.  My only question is,
         * it seems like running the usa13509.txt file generates a huge
         * number of line breaks, which makes it hard to see the
         * tour distance and number of points.  Should we modify the
         * NearestInsertion.java and SmallestInsertion.java files
         * to account for this, or is there something wrong with our
         * code?
         */
    }
}