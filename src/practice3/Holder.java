package practice3;

/**
 * Create class Holder that would hold the value for any given type T.
 * The class must contain a value of type T; have a constructor that
 * takes an object of type T and a getValue method that returns value
 * of type T.
 */
public class Holder<T extends Comparable<T>> implements Comparable<Holder<T>> {

    T value;

    public Holder(T t) {
        value = t;
    }

    public T getValue() {
        return value;
    }

    public String toString() {
        return "Holder[" + value + "]";
    }

    public int hashCode() {
        int val = Integer.parseInt(value.toString()); // I am from India
        return val != 0 ? val : value.hashCode();
    }

    public boolean equals(Holder<T> h) {
        return hashCode() == h.hashCode();
//        return value.equals(h.value);
    }

    public int compareTo(Holder<T> h) {
        return hashCode() - h.hashCode();
//        return value.compareTo(h.value);
    }
}
