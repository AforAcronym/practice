package practice3;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Topic 5: Working with Maps
 * Goal: Become familiarized with java.util.Map API.
 * Tasks:
 * 1.	In your main method
 * g.	Create a Map instance of type HashMap with initial capacity of 10 elements
 * h.	Associate 10 instances of class Holder (with Holder.value initialized by random
 * number within range 1-20) using squared value of Holder.value as Map’s entry value
 * i.	Iterate through numbers 1 to 20 and check existence of corresponding Map entry
 * using both get(Object key) and containsKey(Object key)
 */
public class WorkingWithMaps {
    public static void main(String[] args) {
        int len = 10;
        Random rand = new Random();
        int val;
        int max = 20;
        Map<Holder<Integer>, Integer> m = new HashMap<Holder<Integer>, Integer>(len);
        for (int i = 0; i < len; ++i) {
            val = rand.nextInt(max) + 1;
            m.put(new Holder<Integer>(Integer.valueOf(val)), val * val);
        }

        System.out.println(m);

        Holder<Integer> h;
        for (int i = 0; i < max; ++i) {
            h = new Holder<Integer>(Integer.valueOf(i + 1));
            System.out.println("" + i + ": " + m.containsKey(h) + "    " + m.get(h));
        }
    }
}
