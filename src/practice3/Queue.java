package practice3;

public class Queue<E> {

	LinkedList<E> ll = new LinkedList<E>();

	/**
	 * Puts an element at the end of this Queue
	 * 
	 * @param element
	 */
	public void put(E element) {
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
