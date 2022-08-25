import java.util.List;
import java.util.ArrayList;

/**
 * Lists.java
 * Jeff Ondich, 2014-01-05
 * Modified: Anna Rafferty, 2016-08-24
 * Modified: Layla Oesper, 2017-08-30
 *
 * Demonstrates a few operations with Java List interface and ArrayList objects.
 *
 * This is the Java half of a pair of parallel examples in Python and Java.
 * See lists.py.
 */
public class Lists {
    public static void main(String[] args) {
        // Instantiate an empty list.
        List<String> animalList = new ArrayList<String>();
        
        // Add some items to the list.
        animalList.add("goat");
        animalList.add("moose");
        animalList.add("emu");
        animalList.add("kudu");
        animalList.add("coatimundi");
        
        // Insert an item at index 2 (*third* position, after indices 0 and 1).
        animalList.add(2, "gnat");
        
        // Iterate over the items in the list.
        System.out.println("Animals iterated using a for (:) loop");
        for (String animal : animalList) {
            System.out.println(animal);
        }
        
        System.out.println("");
        System.out.println("Animals iterated using an index");
        for (int index = 0; index < animalList.size(); index++) {
            System.out.println(animalList.get(index));
        }
    }
}
