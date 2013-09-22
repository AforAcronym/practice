package practice3;

public class Stack {

	private static final int INITIAL_CAPACITY = 50;
	private Object[] array = new Object[INITIAL_CAPACITY];
	private int currentSize = 0;

	/**
	 * Change capacity back and forth by an integer
	 * 
	 * @param number
	 */
	private void changeCapacityBy(int number) {
		int newSize = array.length + number;
		Object[] temparray = new Object[newSize];
		System.arraycopy(array, 0, temparray, 0, currentSize);
		array = temparray;
	}

	/**
	 * Enlarge capacity if needed
	 * 
	 * @param number
	 */
	private void checkEnoughCapacity(int number) {
		if (array.length - currentSize < number) {
			changeCapacityBy(array.length);
		} else if (currentSize < array.length / 4    &&    array.length / 2 > INITIAL_CAPACITY) {
			changeCapacityBy(-array.length / 2);
		}
	}

	/**
	 * Adds an element at the top of this Stack
	 * 
	 * @param element
	 */
	public void push(Object element) {
		checkEnoughCapacity(1);
		array[currentSize] = element;
		currentSize++;
	}

	/**
	 * if Stack is not empty, returns the topmost element of the Stack and
	 * removes it from the Stack, otherwise returns null
	 * 
	 * @return
	 */
	public Object pop() {
		if (currentSize > 0) {
			Object elem = array[currentSize-1];
			array[currentSize-1] = null;
			checkEnoughCapacity(0);
			currentSize--;
			return elem;
		}
		return null;
	}

	/**
	 * If Stack is not empty, returns the topmost element of the Stack,
	 * otherwise returns null
	 * 
	 * @return
	 */
	public Object peek() {
		if (currentSize > 0) {
			return array[currentSize-1];
		}
		return null;
	}

	@Override
	public String toString() {
		if (currentSize == 0) {
			return "[Empty Stack]";
		}
		StringBuffer sb = new StringBuffer();
		for (int i = currentSize - 1; i >= 0; --i) {
			sb.append("Stack[" + i + "] = " + array[i]);
			if (i > 0) {
				sb.append("\n");
			}
		}
		return sb.toString();
	}

}
