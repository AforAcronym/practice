package practice3;

/**
 * Created with IntelliJ IDEA. Continued with Eclipse. by E. Shevchenko
 * 
 */
public class Array<E> {

	private E[] array;

	/**************************************************************************
	 * Constructor
	 * 
	 * @param size
	 */
	@SuppressWarnings("unchecked")
	public Array(int size) {
		array = (E[]) new Object[size];
	}

	/**************************************************************************
	 * Returns size
	 * 
	 * @return
	 */
	public int getSize() {
		return array.length;
	}

	/**************************************************************************
	 * Check whether the index is within array boundaries
	 * 
	 * @param index
	 * @return
	 */
	private boolean indexOK(int index) {
		return index < array.length && index >= 0;
	}

	/**************************************************************************
	 * If index is within array boundary, returns Element at specified index,
	 * otherwise returns null
	 * 
	 * @param index
	 * @return
	 */
	public E getElement(int index) {
		if (indexOK(index)) {
			return (E) array[index];
		}
		return null;
	}

	/**************************************************************************
	 * If index is within array boundary, places specified element at specified
	 * position and returns true, otherwise returns false
	 * 
	 * @param index
	 * @param element
	 * @return
	 */
	public boolean setElement(int index, E element) {
		if (indexOK(index)) {
			array[index] = element;
			return true;
		}
		return false;
	}

	/**************************************************************************
	 * Change capacity by the passed number (may be negative)
	 * 
	 * @param number
	 */
	@SuppressWarnings("unchecked")
	public void changeCapacityBy(int number) {
		int newSize = array.length + number;
		E[] temparray = (E[]) new Object[newSize];
		System.arraycopy(array, 0, temparray, 0, array.length > newSize ? newSize : array.length);
		array = temparray;
	}
	
	/**************************************************************************
	 * Change capacity by the passed number (may be negative)
	 * 
	 * @param number
	 */
	public Array<E> getCopy() {
		Array<E> newArr = new Array<E>(array.length);
		System.arraycopy(array, 0, newArr.array, 0, array.length);
		return newArr;
	}

	/**************************************************************************
	 * Returns String
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			sb.append("practice3.Array[" + i + "] = " + array[i]);
			if (i < array.length - 1) {
				sb.append("\n");
			}
		}
		return sb.toString();
	}

}
