package practice3;

/**
 * Vector
 */
public class Vector<E> {

	private E[] array;
	private int currentSize;

	/**
	 * Creates a Vector with specified initial capacity
	 * 
	 * @param initialCapacity
	 */
	public Vector(int initialCapacity) {
		currentSize = 0;
		array = (E[]) new Object[initialCapacity];
	}

	/**
	 * Returns current number of elements in this Vector
	 * 
	 * @return
	 */
	public int getCurrentSize() {
		return currentSize;
	}

	/**
	 * Adds an element to the end of this Vector, increasing Vector's capacity
	 * if needed
	 * 
	 * @param element
	 */
	public void addElement(E element) {
		if (array.length > currentSize) {
			array[currentSize] = element;
			currentSize++;
		} else {
			checkEnoughCapacity(1);
			array[currentSize] = element;
			currentSize++;
		}

	}

	/**
	 * Change capacity back and forth by an integer
	 * 
	 * @param number
	 */
	private void changeVectorCapacityBy(int number) {
		int newSize = array.length + number;
		E[] temparray = (E[]) new Object[newSize];
		System.arraycopy(array, 0, temparray, 0, currentSize);
		array = temparray;
	}

	/**
	 * Enlarge capacity if needed
	 * 
	 * @param number
	 */
	private void checkEnoughCapacity(int number) {
		// if (array.length - currentSize < number) {
		// expandVectorCapacityBy(number - array.length + currentSize);
		// }
		if (array.length - currentSize < number) {
			changeVectorCapacityBy(array.length);
		} else if (currentSize < array.length / 4) {
			changeVectorCapacityBy(-array.length / 2);
		}
	}

	/**
	 * Check whether the index is within array boundaries
	 * 
	 * @param index
	 * @return
	 */
	private boolean indexWithinCapacity(int index) {
		return index < array.length && index >= 0;
	}

	/**
	 * Check whether the index is within the practice3.Vector capacity
	 * 
	 * @param index
	 * @return
	 */
	private boolean indexWithinCurrentSize(int index) {
		return index < currentSize && index >= 0;
	}

	/**
	 * If index is within array boundary, removes element at specified index
	 * 
	 * @param index
	 * @return
	 */
	public boolean removeAt(int index) {
		if (indexWithinCurrentSize(index)) {
			System.arraycopy(array, index + 1, array, index, array.length - index - 1);
			array[array.length - 1] = null; // last element
			currentSize--;
			if (index == currentSize) {
				int i = index - 1;
				while (array[i] == null && i >= 0) {
					currentSize--;
					i--;
				}
			}

			return true;
		}
		return false;
	}

	// if index is within array boundary, inserts an element at
	// specified index and returns true, otherwise returns false
	public boolean insertAt(int index, E element) {
		if (indexWithinCurrentSize(index)) {
			checkEnoughCapacity(1);
			System.arraycopy(array, index, array, index + 1, currentSize
					- index);
			array[index] = element;
			currentSize++;
			return true;
		} else if (indexWithinCapacity(index)) {
			array[index] = element;
			currentSize = index + 1;
			return true;
		}
		return false;
	}

	// if index is within array boundary,
	// returns Element at specified index, otherwise returns null
	public Object getElement(int index) {
		if (indexWithinCapacity(index)) {
			return array[index];
		}
		return null;
	}

	// if index is within array boundary, places specified
	// Element at specified position and returns true,
	// otherwise returns false
	public boolean setElement(int index, E element) {
		if (indexWithinCapacity(index)) {
			array[index] = element;
			return true;
		}
		return false;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			sb.append("Vector[" + i + "] = " + array[i]);
			if (i < array.length - 1) {
				sb.append("\n");
			}
		}
		return sb.toString();
	}

}
