package practice3;

/**
 * Created with IntelliJ IDEA. Continued with Eclipse. 
 * by E. Shevchenko 
 * 
 */
public class Array {

	private Object[] array;

	/**
	 * Constructor
	 * 
	 * @param size
	 */
	public Array(int size) {
		array = new Object[size];
	}

	/**
	 * Returns size
	 * 
	 * @return
	 */
	public int getSize() {
		return array.length;
	}

	/**
	 * Check whether the index is within array boundaries
	 * 
	 * @param index
	 * @return
	 */
	private boolean indexOK(int index) {
		return index < array.length && index >= 0;
	}

	/**
	 * If index is within array boundary, returns Element at specified index,
	 * otherwise returns null
	 * 
	 * @param index
	 * @return
	 */
	public Object getElement(int index) {
		if (indexOK(index)) {
			return array[index];
		}
		return null;
	}

	/**
	 * If index is within array boundary, places specified Element at specified
	 * position and returns true, otherwise returns false
	 * 
	 * @param index
	 * @param element
	 * @return
	 */
	public boolean setElement(int index, Object element) {
		if (indexOK(index)) {
			array[index] = element;
			return true;
		}
		return false;
	}

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
