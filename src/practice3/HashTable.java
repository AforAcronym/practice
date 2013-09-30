package practice3;

/**
 * Hash table class
 */
public class HashTable<K, V> {

    private double loadFactor = 0.75;
    private double loadCounter = 0;
    private int capacity = 50;
    // Array of Arrays of KeyValueHolders
    private Array<Array<KeyValueHolder<K, V>>> buckets = new Array<Array<KeyValueHolder<K, V>>>(capacity);
    // Exchanging variables for searchElement(K key) method usage
    private int _searchBucketIndex;
    private int _searchInnerIndex;
    private KeyValueHolder<K, V> _searchHolder;

    private int saltInteger = (int) System.currentTimeMillis();
    private String saltString = "OloloPewPew";
    private int[]

    /**
     * Hash function
     *
     * @return index for the inner buckets array
     */
    private int hash(final K key) {

        int res = 0;
        if (key instanceof String) {

        }
        // If string
        // If number
        // get length of number and nearest prime numbers to mod and to add
        return res;

    }

    /**
     * Search value associated with given key in the inner array and return its 'credentials' if it was found
     * otherwise return nulls
     *
     * @param key
     * @return
     */
    private void searchElement(K key) {

        // The search variables
        _searchHolder = null;
        _searchInnerIndex = -1;
        _searchBucketIndex = hash(key); // Here is magic

        if (buckets.getElement(_searchBucketIndex) == null) {

            _searchBucketIndex = -1;
            // The key is not associated with any value
            // There is nothing even with this key's hash
            return;

        } else {

            // We might have collision and need to find out if the key is in here

            Array<KeyValueHolder<K, V>> bucketArray = buckets.getElement(_searchBucketIndex);

            for (int i = 0; i < bucketArray.getSize(); ++i) {
                _searchHolder = bucketArray.getElement(i);

                if (_searchHolder.key == key) {

                    // The key is found. We can now get or change value in the found KeyValueHolder
                    _searchInnerIndex = i;
                    return;
                }
            }

            // The key is not associated with any value
            // And we already know required bucket index for the record. It's useful if hash function is heavy.
            _searchHolder = null;
        }
    }

    /**
     * Gets a value, associated with specified key, returns null if key is not
     * associated with any value
     *
     * @param key
     * @return
     */
    public V get(K key) {
        searchElement(key);
        return _searchHolder.value;
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

        searchElement(key);

        // Previous value associated with the key
        V previousValue = null;

        if (_searchHolder == null) {

            // Add new holder with the new key-value pair
            buckets.getElement(_searchBucketIndex).append(new KeyValueHolder<K, V>(key, value));

            // Count the new entry
            ++loadCounter;

            if (loadCounter / capacity >= loadFactor) {
                // Increase the hash capacity
                buckets.changeCapacityBy(buckets.getSize() * 2);
                capacity = buckets.getSize();
            }

        } else {

            // Change value in the found holder
            previousValue = _searchHolder.value;
            _searchHolder.value = value;
        }

        return previousValue;
    }

    /**
     * Removes association of the specified key, and returns previously
     * associated value or null if key was not associated with any value
     *
     * @param key
     * @return
     */
    public V remove(K key) {

        searchElement(key);

        if (_searchHolder == null) {
            return null;              // Nothing was found
        }

        // Found a value
        V value = _searchHolder.value;

        // Deleting the entry
        if (buckets.getElement(_searchBucketIndex).getSize() == 1) {
            // Clear the whole bucket
            buckets.setElement(_searchBucketIndex, null);
        } else {
            // Get rid of the element in the bucket array
            buckets.getElement(_searchBucketIndex).removeAt(_searchInnerIndex);
        }

        --loadCounter;
        // TODO we might need to reduce the bucket array capacity in order to save memory

        return value;

    }

    /**
     * String representation
     *
     * @return
     */
    @Override
    public String toString() {
        // TODO   toString
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
