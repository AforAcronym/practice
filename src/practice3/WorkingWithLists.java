package practice3;

import java.util.*;

/**
 * Goal: Become familiarized with java.util.List API.
 * Tasks:
 * 1.	In your main method
 * a.	Create a List instance of type ArrayList with initial capacity of 10 elements.
 * Check list’s size to note that initially it is equals to zero
 * b.	Add 10 instances of class Holder<Integer> with Holder.value initialized by random number
 * within range 1-5. Output values as you add them
 * c.	Output your List and note that elements are stored in List in order they were added
 * d.	Sort your List in ascending order and output the results. This can be done with a single
 * call to one of the utility methods in class java.util.Collections
 * e.	Sort your List elements in a descending order and output the results
 * f.	Create an array of 10 Holder instances. Convert this array into List using a single call
 * to one of utility methods of Arrays class
 */
public class WorkingWithLists {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        int len = 10;
        List<Holder<Integer>> lst = new ArrayList<Holder<Integer>>(len);
        System.out.println("List size: " + lst.size());

        Random rand = new Random();
        int num;
        System.out.print("Adding:");

        for (int i = 0; i < len; ++i) {

            num = rand.nextInt(5) + 1;
            System.out.print(" " + num);

            lst.add(new Holder<Integer>(num));
        }

        System.out.println();
        System.out.println("The list: " + lst); // c

        java.util.Collections.sort(lst);
        System.out.println("The list: " + lst); // d

        java.util.Collections.sort(lst, Collections.reverseOrder());
        System.out.println("The list: " + lst); // e

//        Holder<Integer>[] arr = (Holder<Integer>[]) new Object[len];
        Holder<?>[] arr = new Holder<?>[len];
        for (int i = 0; i < len; ++i) {
            arr[i] = new Holder<Integer>(rand.nextInt(100));
        }
//        List<Holder<Integer>> arrList = Arrays.asList(arr);
        List<Holder<?>> arrList = Arrays.asList(arr);
        System.out.println(arrList);
    }
}
