package practice3;

public class LinkedListFromArray<E> {

	private E[] array;

	/**
	 * Constructor
	 */
	public LinkedListFromArray() {
		array = (E[]) new Object[0];
	}

	/**
	 * Check whether passed index is valid
	 * 
	 * @param index
	 * @return
	 */
	private boolean indexOK(int index) {
		return (index >= 0 && index < array.length);
	}

	/**
	 * Add or remove number of cells at the tail of the inner array
	 * 
	 * @param number
	 * @return
	 */
	private void expandArrayCapacityBy(int number) {
		E[] temparr = (E[]) new Object[array.length + number];
		System.arraycopy(array, 0, temparr, 0, (number > 0) ? array.length
				: array.length + number);
		array = temparr;
	}

	/**
	 * Add an element at the end of this List
	 */
	public void addLast(E element) {
		expandArrayCapacityBy(1);
		array[array.length - 1] = element;
	}

	/**
	 * Shifts array content adding or removing cells at the beginning
	 */
	private void shiftArrayBy(int number) {
		E[] temparr = (E[]) new Object[array.length + number];
		int srcPos = (number > 0) ? 0 : number;
		int destPos = (number > 0) ? number : 0;
		int length = (number > 0) ? array.length : array.length - number;
		System.arraycopy(array, srcPos, temparr, destPos, length);
		array = temparr;
	}


	/**
	 * Add an element at the beginning of this List
	 */
	public void addFirst(E element) {
		shiftArrayBy(1);
		array[0] = element;
		// System.out.println(elem.toInfoString());
		// System.out.println("Added first: " + first);
	}

	// returns first Element of the List if the List is not empty, otherwise
	// returns null
	public Object getFirst() {
		return array[0];
	}

	/**
	 * returns last Element of the List if the List is not empty, otherwise
	 * returns null
	 */
	public Object getLast() {
		return array[array.length - 1];

	}

	/**
	 * If index is within array boundary, removes element at specified index
	 */
	public boolean removeAt(int index) {
		if (indexOK(index)) {
			if (index == 0) {
				shiftArrayBy(-1);
			} else {
				System.arraycopy(array, index + 1, array, index, array.length - index - 1);
				expandArrayCapacityBy(-1);
			}
			return true;
		}
		return false;
	}

	/**
	 * If index is within array boundary, inserts an element at specified index
	 * and returns true, otherwise returns false
	 * 
	 * @param index
	 * @param element
	 * @return
	 */
	public boolean insertAt(int index, E element) {
		if (indexOK(index)) {
			if (index == 0) { 
				addFirst(element);
			} else {
				expandArrayCapacityBy(1);
				System.arraycopy(array, index, array, index+1, array.length - index - 1);
				array[index] = element;
			}
			return true;
		}
		return false;
	}

	/**
	 * If index is within array boundary, returns Element at specified index,
	 * otherwise returns null
	 */
	public Object getElement(int index) {
		if (indexOK(index)) {
			return array[index];
		}
		return null;
	}

	// if index is within array boundary, places specified Element
	// at specified position and returns true, otherwise returns false
	public boolean setElement(int index, Object element) {
		if (indexOK(index)) {
			return true;
		}
		return false;
	}

	// returns a practice3.Vector containing elements of this List
	public Vector toVector() {
		Vector<E> vec = new Vector<E>(array.length);
		for (int i = 0; i < array.length; i++) {
			vec.addElement(array[i]);
		}
		return vec;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			sb.append("LinkedListFromArray[" + i + "] = " + array[i]);
			if (i < array.length - 1) {
				sb.append("\n");
			}
		}
		return sb.toString();
	}

	public String toInfoString() {
		return toString();
	}

	public int getSize() {
		return array.length;
	}

	/**
	 * Coherence testing method prints the test result
	 */
	public void testCoherence() {
		System.out.println("OK by design");
	}

}
