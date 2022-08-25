import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * Die.java
 * Jeff Ondich, 2014-01-05
 * Modified: Anna Rafferty, 2016-08-24
 *
 * A simple class representing an n-sided die (as used in various board games).
 * 
 * After running this class, make a variable that represents a 9-sided die, and
 * then print out the result of rolling it. Which lines in the file represent the 
 * constructor for the die object?
 *
 * This is the Java half of a pair of parallel examples in Python and Java.
 * See die.py.
 */
public class Die {
    // These are the instance variables (like the "self.*" stuff in Python)
    private int numberOfSides;
    private int value;
    private Random randomNumberGenerator;

    public Die(int n) {
        numberOfSides = n;
        value = 1;
        randomNumberGenerator = new Random();
    }

    public void roll() {
        value = randomNumberGenerator.nextInt(numberOfSides) + 1;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int newValue) {
        value = newValue;
    }

    // This main program is run only when Die is executed as a program
    // in its own right ("java Die"). If a different .java file imports
    // Die for its own purposes, then main won't get executed.
    public static void main(String[] args) {
        // Let's play Yahtzee
        List<Die> dice = new ArrayList<Die>();
        dice.add(new Die(6));
        dice.add(new Die(6));
        dice.add(new Die(6));
        dice.add(new Die(6));
        dice.add(new Die(6));

        for(Die die : dice) {
            die.roll();
        }

        int total = 0;
        for(Die die : dice) {
            System.out.format("%d ", die.getValue());
            total += die.getValue();
        }
        System.out.format("\nTotal: %d\n", total);

        if(dice.get(0).getValue() == dice.get(1).getValue() &&
           dice.get(1).getValue() == dice.get(2).getValue() &&
           dice.get(2).getValue() == dice.get(3).getValue() &&
           dice.get(3).getValue() == dice.get(4).getValue()) {
            System.out.println("Yahtzee!");
        }
    }
}
