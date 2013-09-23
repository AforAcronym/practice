package practice3;

/**
 * Hash table class Here is no hash function
 */
public class HashtableNaive {

	private static final int INITIAL_CAPACITY = 50;
	private Object[] keys = new Object[INITIAL_CAPACITY];
	private Object[] values = new Object[INITIAL_CAPACITY];
	private int currentSize;
	private int currentIndex = 0;

	/**
	 * Change capacity back and forth by an integer
	 * 
	 * @param number
	 */
	private void changeCapacityBy(Object[] array, int number) {
		int newCapacity = array.length + number;
		Object[] temparray = new Object[newCapacity];
		System.arraycopy(array, 0, temparray, 0, currentSize);
		array = temparray;
	}

	/**
	 * Enlarge capacity if needed
	 * 
	 * @param number
	 */
	private void checkEnoughCapacity(int number) {
		int len = keys.length;
		if (len - currentSize < number) {
			// Make inner arrays two times longer
			changeCapacityBy(keys, len);
			changeCapacityBy(values, len);
		} else if (currentSize < len / 4 && len / 2 > INITIAL_CAPACITY) {
			// Make inner arrays two times shorter if only quarter is used
			changeCapacityBy(keys, -len / 2);
			changeCapacityBy(values, -len / 2);
		}
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
		Object oldvalue = get(key);
		if (oldvalue != null) {
			values[currentIndex] = value;
			return oldvalue;
		}
		checkEnoughCapacity(1);
		keys[currentSize] = key;
		values[currentSize] = value;
		currentSize++;
		return null;
	}

	/**
	 * Gets a value, associated with specified key, returns null if key is not
	 * associated with any value
	 * 
	 * @param key
	 * @return
	 */
	public Object get(Object key) {
		for (int i = 0; i < currentSize; ++i) {
			if (key == keys[i]) {
				currentIndex = i;
				return values[i];
			}
		}
		return null;
	}

	/**
	 * Shifts array content adding or removing cells at the beginning
	 */
	private void shiftArrayBy(Object[] array, int number) {
		Object[] temparr = new Object[array.length + number];
		
		int srcPos  = (number > 0) ? 0 				: number;
		int destPos = (number > 0) ? number 		: 0;
		int length  = (number > 0) ? array.length 	: array.length - number;
		
		System.arraycopy(array, srcPos, temparr, destPos, length);
		array = temparr;
	}

	/**
	 * If index is within array boundary, removes element at specified index
	 */
	private void removeAt(Object[] array, int index) {
		if (currentSize == 0) {
			shiftArrayBy(array, -1);
		} else {
			System.arraycopy(array, index + 1, array, index, array.length - index - 1);
			changeCapacityBy(array, -1);
		}
	}
	
	private void exchangeWithLast(Object[] array, final int index) {
		if (currentSize > 1) {
			// Bubble
			Object kt = array[index];
			array[index] = array[currentSize-1];
			array[currentSize-1] = kt;
		}
		checkEnoughCapacity(0);
	}

	/**
	 * Removes association of the specified key, and returns previously
	 * associated value or null if key was not associated with any value
	 * 
	 * @param key
	 * @return
	 */
	public Object remove(Object key) {
		Object val = get(key);
		
		if ( val == null ) {
			return null;
		}
		
		// removeAt(keys, currentIndex);
		// removeAt(values, currentIndex);
		
		checkEnoughCapacity(1);
		// Switch the chosen element and the last one
		exchangeWithLast(keys, currentIndex);
		exchangeWithLast(values, currentIndex);
		// Remove links to the chosen element
		keys[currentSize-1] = null;
		values[currentSize-1] = null;
		
		currentSize--;
		return val;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("HashtableNaive {\n");
		for (int i = 0; i < currentSize; i++) {
			sb.append("  " + keys[i] + " => " + values[i] + "\n");
		}
		sb.append("}");
		return sb.toString();
	}
}
