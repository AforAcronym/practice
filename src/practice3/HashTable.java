package practice3;

/**
 * Hash table class
 */
public class HashTable<K, V> {

    static private String saltString = "OloloPewPew";
    // static private int[] primes = {97, 997, 9973, 99991, 999983, 9999991, 99999989, 999999937};
    static private int[] primes = {101, 1009, 10007, 100003, 1000003, 10000019, 100000007, 1000000007};
    private double loadFactor = 0.75; // Must be > 0.5, see rescale usage in 'put' method
    private double loadCounter = 0;
    private int capacityIndex = 0;
    // Array of Arrays of KeyValueHolders
    private Array<Array<KeyValueHolder<K, V>>> buckets;
    // Exchanging variables for searchElement(K key) method usage
    private int _searchBucketIndex;
    private int _searchInnerIndex;
    private KeyValueHolder<K, V> _searchHolder;
    private int saltInteger;


    /**
     * Constructor
     */
    public HashTable() {
        saltInteger = (int) System.currentTimeMillis();
        buckets = new Array<Array<KeyValueHolder<K, V>>>(primes[capacityIndex]);
    }

    /**
     * Constructor
     */
    public HashTable(int capacity) {
        saltInteger = (int) System.currentTimeMillis();
        while (primes[capacityIndex] < capacity) {
            capacityIndex++;
        }
        buckets = new Array<Array<KeyValueHolder<K, V>>>(primes[capacityIndex]);
    }

    /**
     * Hash function
     * FIXME Only hashcode could be used. See source of HashTable and HashMap
     *
     * @return index for the inner buckets array
     */
    private int hash(final K key) {
        int hash = Math.abs(key.hashCode() + saltInteger);
        hash = hash % primes[capacityIndex];
        return hash;
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
     * Rescale
     */
    private void rescale(int capacityIndex) {

        Array<Array<KeyValueHolder<K, V>>> oldBuckets = buckets;

        // Change the hash capacity
        buckets = new Array<Array<KeyValueHolder<K, V>>>(primes[capacityIndex]);

        KeyValueHolder<K, V> holder;

        // See the old buckets
        for (int i = 0; i < oldBuckets.getSize(); ++i) {

            // See the collisions
            Array<KeyValueHolder<K, V>> subArray = oldBuckets.getElement(i);

            if (subArray != null) {
                for (int j = 0; j < subArray.getSize(); ++j) {
                    holder = subArray.getElement(j);
                    put(holder.key, holder.value);
                }
            }
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
        if (_searchHolder == null) {
            return null;
        }
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

            // Create the bucket sub-array if there is none
            if (buckets.getElement(_searchBucketIndex) == null) {
                buckets.setElement(_searchBucketIndex, new Array<KeyValueHolder<K, V>>(0));
            }

            // Add new holder with the new key-value pair
            buckets.getElement(_searchBucketIndex).append(new KeyValueHolder<K, V>(key, value));

            // Count the new entry
            ++loadCounter;

            if (loadCounter / primes[capacityIndex] >= loadFactor) {
                rescale(++capacityIndex);
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

        if (loadCounter / primes[capacityIndex] >= 1 - loadFactor && capacityIndex > 0) {
            rescale(--capacityIndex);
        }

        return value;

    }

    /**
     * String representation
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("HashTable: {");

        KeyValueHolder<K, V> holder;

        // See the old buckets
        for (int i = 0; i < buckets.getSize(); ++i) {


            // See the collisions
            Array<KeyValueHolder<K, V>> subArray = buckets.getElement(i);

            if (subArray != null) {
                for (int j = 0; j < subArray.getSize(); ++j) {
                    sb.append("\t");
                    holder = subArray.getElement(j);
                    sb.append(holder.key.toString());
                    sb.append("\t→\t");
                    sb.append(holder.value.toString());
                }
            }
        }
        sb.append("\n}");
        return sb.toString();
    }

    /**
     * A special inner class for holding key-value pairs
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
