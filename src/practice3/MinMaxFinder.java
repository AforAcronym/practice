package practice3;

/**
 * Create class MinMaxFinder that will be able to find minimum and maximum value in an array.
 * This class must contain an array of Holder objects (initialized through constructor – use
 * varargs feature here).
 * Create methods min() and max() that will determine minimum and maximum by comparing values
 * of the array elements (use getValue() method of class Holder). Note that values contained
 * in the array must implement java.lang.Comparable interface to allow this.
 */
public class MinMaxFinder<E extends Comparable<E>> {

    Holder<E>[] array;
    Holder<E> min;
    Holder<E> max;

    public MinMaxFinder(Holder<E>... elements) {
        array = elements;
    }

    private void findMin() {
        int m = 0;
        for (int i = 1; i < array.length; ++i) {
            if (array[i].getValue().compareTo(array[m].getValue()) < 0) {
                m = i;
            }
        }
        min = array[m];
    }

    private void findMax() {
        int m = 0;
        for (int i = 1; i < array.length; ++i) {
            if (array[i].getValue().compareTo(array[m].getValue()) > 0) {
                m = i;
            }
        }
        max = array[m];

    }

    public E min() {
        if (min == null) {
            findMin();
        }
        return min.getValue();
    }

    public E max() {
        if (max == null) {
            findMax();
        }
        return max.getValue();
    }

    public static void main(String[] args) {

        MinMaxFinder<Integer> mmf = new MinMaxFinder<Integer>(
                new Holder<Integer>(4),
                new Holder<Integer>(7),
                new Holder<Integer>(5),
                new Holder<Integer>(1),
                new Holder<Integer>(8),
                new Holder<Integer>(3),
                new Holder<Integer>(6),
                new Holder<Integer>(2)
        );

        System.out.println("Min: " + mmf.min());
        System.out.println("Max: " + mmf.max());
    }
}
