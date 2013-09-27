package practice3;

/**
 * Hash table class
 */
public class HashTable<K extends Comparable<K>, V> {

    private double loadFactor = 0.75;
    private int INITIAL_CAPACITY = 50;
    private V[] buckets = (V[]) new Object[INITIAL_CAPACITY];

    /**
     * Hash    TODO
     */
    private int hash(final K key) {
        int res = 0;

        return res;
    }

    /**
     * Gets a value, associated with specified key, returns null if key is not
     * associated with any value
     *
     * @param key
     * @return
     */
    public Object get(Object key) {
        return null;
    }

    /**
     * Associates a specified value with specified key, if the key was
     * previously associated with another value, returns it, otherwise returns
     * null
     *
     * @param key
     * @param value
     * @return
     */
    public Object put(Object key, Object value) {
        return null;
    }

    /**
     * Removes association of the specified key, and returns previously
     * associated value or null if key was not associated with any value
     *
     * @param key
     * @return
     */
    public Object remove(Object key) {
        return null;

    }

}
