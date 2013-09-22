package practice3;

public class Queue {

	LinkedList ll = new LinkedList();

	/**
	 * Puts an element at the end of this Queue
	 * 
	 * @param element
	 */
	public void put(Object element) {
		ll.addLast(element);
	}

	/**
	 * If the Queue is not empty, gets the first element from this Queue and
	 * removes it, otherwise returns null
	 * 
	 * @return
	 */
	public Object get() {
		Object elem = ll.getFirst();
		ll.removeAt(0);
		return elem;

	}

	/**
	 * If the Queue is not empty, gets the first element from this Queue,
	 * otherwise returns null
	 * 
	 * @return
	 */
	public Object peek() {
		return ll.getFirst();
	}

}
