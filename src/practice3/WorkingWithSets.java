package practice3;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Working with Sets
 * Goal: Become familiarized with java.util.Set API.
 * Tasks:
 * 1.	In your main method:
 * a.	Create a Set instance of type HashSet with initial capacity of 10 elements
 * b.	Add 10 instances of class Holder<Integer> to your Set, with Holder.value running from 0 to 9
 * c.	Check the size of your Set
 * d.	Output your Set to see if it preserves the order or elements (it should not)
 * e.	Create another Set instance and add 10 instances of class Holder<Integer> to it,
 * where Holder.value runs from 1 to 10
 * f.	Unite the two Sets (disjunction operation)
 * g.	Check the size of the united Set and output its contents.
 * Does it match your expectation? If no, why not?
 * h.	Clear contents of the both Sets and reinitialize them, repeating steps a – e above.
 * i.	Modify Holder class so that Set can correctly do logical operations
 * j.	Repeat steps f and g
 * k.	Iterate through your resulting union Set, outputting its elements
 * l.	In a similar way make a conjunction of your two sets (check size and contents after that)
 * m.	In a similar way make a difference of your sets (check size and contents after that)
 * 2.	In your main method:
 * a.	Create a Set instance of type TreeSet (this is used to contain ordered set of elements)
 * b.	Add 10 instances of class Holder<Integer> to your Set, with Holder.value running from 0 to 9
 * c.	Modify Holder class to make TreeSet working and repeat step b
 * d.	Iterate through your Set to be sure elements are kept in correct order
 */
public class WorkingWithSets {
    public static void main(String[] args) {
        int size = 10;

        Set s1 = new HashSet<Holder<Integer>>(size);    // a
        for (int i = 0; i < size; ++i) {
            s1.add(new Holder<Integer>(i));
        }                                // b

        System.out.println("Set 1 size: " + s1.size());// c
        System.out.println("Set 1 itself: " + s1);// d

        Set s2 = new HashSet<Holder<Integer>>(size); //e
        for (int i = 0; i < size; ++i) {
            s2.add(new Holder<Integer>(i + 1));
        }
        System.out.println("Set 2 size: " + s2.size());
        System.out.println("Set 2 itself: " + s2);


        s1.addAll(s2);

        System.out.println("United set size: " + s1.size());
        System.out.println("United set itself: " + s1);

    }
}
