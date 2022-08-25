/**
 * An implementation of the List ADT using
 * a linked list.  Specifically, this implementation
 * only allows a List to contain Comparable items.
 *
 * @author Layla Oesper 
 * @author Eric Alexander
 * @author Dean Gladish
*/

/* Note <E extends Comparable<E> means this container
 * can only hold objects of type E that are Comparable.
 */
public class RecursiveLinkedList<E extends Comparable<E>>{

    // Instance variable for recursive Max function.
    public String s = "";

    /* Internal Node class used for creating linked objects.
    */
    private class Node<E> {
        private E data;
        private Node<E> next;
	
        private Node(E dataItem) {
            data = dataItem;
            next = null;
        }
        
        private Node(E dataItem, Node<E> nextNode) {
            data = dataItem;
            next = nextNode;
        }

    } // End Node class
    
    //Instance variables for RecursiveLinkedList
    private Node<E> head;
    private int numItems;
    
    /**
     * Creates an empty RecursiveLinkedList
     */
    public RecursiveLinkedList() {
        head = null;
        numItems = 0;
    }
    
    /**
     * Returns the data stored at positon index.
     * @param index
     * @return The data stored at position index. 
     */
    public E get(int index) {
        if (index < 0 || index >= numItems) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        } 
        Node<E> node = getNode(index);
        return node.data;
    }
    
    /*
     * Helper method that retrieves the Node<E> stored at 
     * the specified index.
     */
    private Node<E> getNode(int index) {
        Node<E> node = head;
        for (int i = 0; i < index && node != null; i++) {
            node = node.next;
        }
        return node;
    }
    
    /**
     * Removes and returns the data stored at the specified index.
     * @param index The position of the data to remove.
     * @return The data previously stored at index position.
     */
    public E remove(int index) {
        if (index < 0 || index >= numItems) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        
        if (index == 0){
            return removeFirst();
        } else {
            Node<E> before = getNode(index - 1);
            return removeAfter(before);
        }
    }
    
    /*
     * Helper method that removes the Node<E> after the
     * specified Node<E>. Returns the data that was
     * stored in the removed node.
     */
    private E removeAfter(Node<E> node) {
        Node<E> temp = node.next;
        if (temp != null) {
            node.next = temp.next;
            numItems--;
            return temp.data;
        } else {
            return null;
        }
    }
    
    /*
     * Helper method that removes the first Node<E> in
     * the Linked List.  Returns the data that was
     * stored in the removed node.
     */
    private E removeFirst() {
        Node<E> temp = head;
        if (head != null) {
            head = head.next;
        }
        
        if (temp != null) {
            numItems--;
            return temp.data;
        } else {
            return null;
        }
    }
    
    /**
     * Adds the data to the list at the specified index.
     * @param index The position to add the data.
     * @param anEntry The particular data to add to the list.
     */
    public void add(int index, E anEntry) {
        if (index < 0 || index > numItems) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        if (index == 0) {
            addFirst(anEntry);
        } else {
            Node<E> node = getNode(index - 1);
            addAfter(node, anEntry);
        }
    }
    
    /*
     * Helper method that adds anEntry to the first
     * position in the list.
     */
    private void addFirst(E anEntry) {
        head = new Node<>(anEntry, head);
        numItems++;
    }
    
    /*
     * Helper method that adds anEntry after the
     * specified Node<E> in the linked list.
     */
    private void addAfter(Node<E> before, E anEntry) {
        before.next = new Node<>(anEntry, before.next);
        numItems++;
    }
    
    /**
     * Add the specified data to the end of the list.
     * @param anEntry The data to add to this list.
     */
    public boolean add(E anEntry) {
        add(numItems, anEntry);
        return true;
    }
    
    /**
     * Returns the size of the list in terms of items stored.
     * @returns the number of items in the list.
     */
    public int size() {
        return numItems;
    }
    
    /**
     * Modifies the list so the specified index now 
     * contains newValue (overwriting the old data).
     * @param index The position in the list to add data.
     * @param newValue The data to place in the list.
     * @return The previous data value stored at index.
     */
    public E set(int index, E newValue) {
        if (index < 0 || index >= numItems) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        Node<E> node = getNode(index);
        E result = node.data;
        node.data = newValue;
        return result;
    }
    
    /**
     * A string representation of the List.
     * @returns A string representation of the list.
     */
    public String toString() {
        String s = "[";
        Node<E> temp = head;
        for (int i = 0; i < numItems; i++) {
            s = s + temp.data.toString();
            if (i < numItems - 1) {
                s = s + ", ";
            }
            temp = temp.next;
        }
        s = s + "]";
        return s;
    }
    
    /**
     * Return the maximum element in the list using
     * compareTo() method of Comparable.
     *
     * @return maximum element of the list
     **/

    public E max() {
        // Implements the Comparable interface using a helper method.
//        return max(1);
        Node<E> temp = head;
        return max(temp);
    }

    private E max(Node<E> temp) { // This is the helper method for the original max function.
        if (temp != null) {
            /**If we haven't reached the end of the list, compare the current node's data with s.
             * Reassign the value of s if it is less than the value of the current node.
             */
            if (s.compareTo(temp.data.toString()) < 0) { s = "" + temp.data.toString(); }
            temp = temp.next;
            // Given that we haven't reached the end, check the next node's value via recursion.
            return max(temp);
        }
        // Return the maximum value.
        return (E) s;
    }

    /**
     * Remove all elements that match element using the 
     * equals() operator to determine a match. 
     * (Don't use ==).
     *
     * @param element The element that should be removed
     **/

    public void removeAll(E element){
        removeAll(element, head);
    }

    /**
     * Helper method for the removeAll method.
     * @param element is the element to be removed
     * @param node is the node before a node, the value of which I compare to the element.
     * @return This should return removeAll unless the method has served its purpose.
     *
     * Much of the structure of this code was recycled
     * from the removeFirst and removeAfter methods
     * that were already provided by Professor Eric Alexander.
     *
     */
    private E removeAll(E element, Node<E> node) {
        String s = "" + element;


        /**
         * The following code tests to see if the head contains the element.
         * If so, the head is removed from the recursive linked list.
         */
        if (s.compareTo(head.data.toString()) == 0) {
            Node<E> temp = head;
            if (head != null) {
                head = head.next;
            }
            if (temp != null) {
                numItems--;
                return removeAll(element, node);
            } else {
                return removeAll(element, node);
            }
        }

        /**
         * The rest of this code will iterate through the rest of the list and
         * remove all nodes for which the underlying data is equal
         * to the element as determined by the compareTo method.
         */
        Node<E> temp = node.next;
        if (temp != null && (s.compareTo(node.next.data.toString()) == 0)) { // Comparison
            node.next = temp.next;
            numItems--; // Reassign node.next and then decrement to remove the end of the list.
            if (node.next != null) { // If the end has not been reached, examine the next node.
                return removeAll(element, node.next);
            } else { // Else, we are done.
                return null;
            }
        } else {
            if (node.next != null) { // If the end has not been reached, examine the next node.
                return removeAll(element, node.next);
            } else {

                /**
                 * The following block off code essentially makes sure
                 * that the list does not contain any remaining elements
                 * not addressed by the recursive function.
                 */
                boolean containsElement = false;
                Node<E> end = head;
                for (int i = 0; i < numItems; i++) {
                    if (end.data == element) {
                        containsElement = true;
                    }
                    end = end.next;
                }
                if (containsElement) {
                    removeAll(element);
                }

                    return null;
            }
        }
    }

    /**
     * Duplicate each element of the list
     *
     * For example, the list [ 0 1 2 ] duplicated becomes 
     * [ 0 0 1 1 2 2 ]
     **/
    public void duplicate(){
        duplicate(head);
    }

    /**
     * This is the helper method.
     * @param node This is the only parameter.  Initially this is the head of the linked list.
     * @return Returns null once the function reaches the list's end.
     */
    private E duplicate(Node<E> node) {
        node.next = new Node<>(node.data, node.next); // Creates a new node after the node with the same data.
        numItems++; // Increments the list size.
        if (node.next.next != null) { // Run this code only if the current pair of nodes is not the last one.
            return duplicate(node.next.next);
        } else {
            return null;
        }
    }

 
    /**
     * Here are a couple short tests. You should 
     * should make sure to thoroughly test your code.
     */
    public static void main(String[] args) {
        RecursiveLinkedList<String> l = new RecursiveLinkedList<String>();
        /**
         * This is a test of the three recursive methods.
         */
        l.add("hello");
        l.add("world");
        l.add("1");
        l.add("2");
        l.add("hello");
        System.out.println("Current list: " + l);
        l.duplicate();
        System.out.println("Duplicated: " + l);
        System.out.println("Maximum: " + l.max());
        l.removeAll("hello");
        System.out.println("After removing hello: " + l);
        l.removeAll("1");
        System.out.println("Removed 1: " + l);
        l.removeAll("2");
        System.out.println("Removed 2: " + l);
        l.removeAll("world");
        System.out.println("Removed world: " + l);
    }
}
