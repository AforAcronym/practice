package practice3;

/**
 * Hash table class
 */
public class HashTable<K extends Comparable<K>, V> {

    private double loadFactor = 0.75;
    private double loadCounter = 0;
    private int capacity = 50;
    private int subArrayCapacity = 5;
    // Array of Arrays of KeyValueHolders
    private Array<Array<KeyValueHolder<K, V>>> buckets = new Array<Array<KeyValueHolder<K, V>>>(capacity);

    /**
     * Hash function   TODO
     */
    private int hash(final K key) {
        int res = 0;
        // If string
        // If number
        // get length of number and nearest prime numbers to mod and to add
        return res;

    }

    /**
     * Gets a value, associated with specified key, returns null if key is not
     * associated with any value
     *
     * @param key
     * @return
     */
    public V get(K key) {
        int bucketIndex = hash(key);
        // TODO searchValue method?
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
    public V put(K key, V value) {

        // Here is magic
        int bucketIndex = hash(key);

        // Previous value associated with the key
        V prevVal = get(key);

        // TODO searchValue method?
        if (buckets.getElement(bucketIndex) == null) {
            // There is nothing even with this key's hash

            buckets.setElement(bucketIndex, new Array<KeyValueHolder<K, V>>(subArrayCapacity));
            buckets.getElement(bucketIndex).append(new KeyValueHolder<K, V>(key, value));

        } else {
            // We have collision and need to find out if the key is in here

            Array<KeyValueHolder<K, V>> subArray = buckets.getElement(bucketIndex);

            for (int i = 0; i < subArray.getSize(); ++i) {
                KeyValueHolder<K, V> holder = subArray.getElement(i);
                // The key is found, wee need to change it's value
                if (holder.key == key) {
                    prevVal = holder.value;
                    subArray.setElement(i, new KeyValueHolder<K, V>(key, value));
                }
            }

            // The key is not associated with any value, so new key-value pair holder created
            if (prevVal == null) {
                buckets.getElement(bucketIndex).append(new KeyValueHolder<K, V>(key, value));
            }
        }

        ++loadCounter;

        if (loadCounter / capacity >= loadFactor) {
            // Increase the hash capacity
            buckets.changeCapacityBy(buckets.getSize() * 2);
        }
        return prevVal;
    }

    /**
     * Removes association of the specified key, and returns previously
     * associated value or null if key was not associated with any value
     *
     * @param key
     * @return
     */
    public V remove(K key) {
        // TODO
        return null;

    }

    /**
     * String representation
     *
     * @return
     */
    @Override
    public String toString() {
        // TODO
        return "";
    }

    /**
     * A special inner class for holding key-pair pairs
     *
     * @param <K> key
     * @param <V> value
     */
    private class KeyValueHolder<K, V> {
        private K key;
        private V value;

        public KeyValueHolder(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

}
