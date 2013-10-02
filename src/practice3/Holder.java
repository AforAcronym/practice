package practice3;

/**
 * Create class Holder that would hold the value for any given type T.
 * The class must contain a value of type T; have a constructor that
 * takes an object of type T and a getValue method that returns value
 * of type T.
 */
public class Holder<T> {

    T value;

    public Holder(T t) {
        value = t;
    }

    public T getValue() {
        return value;
    }
}
