package practice3;

/**
 * 
 */
public class LinkedList<E> {

	private LinkedElement<E> first = null;
	private LinkedElement<E> last = null;
	private int total;

	/**
	 * Constructor
	 */
	public LinkedList() {
		first = null;
		last = null;
		total = 0;
	}

	/**
	 * Check whether passed index is valid
	 * 
	 * @param index
	 * @return
	 */
	private boolean indexOK(int index) {
		return (index >= 0 && index < total);
	}

	/**
	 * Add an element at the end of this List
	 */
	public void addLast(E element) {

		LinkedElement<E> elem = new LinkedElement<E>(element);

		elem.setPrev(last);
		elem.setNext(null);

		if (total == 0) {
			first = elem;
		}
		if (total > 0) {
			last.setNext(elem);
		}
		last = elem;
		total++;
	}

	/**
	 * Add an element at the beginning of this List
	 */
	public void addFirst(E element) {
		LinkedElement<E> elem = new LinkedElement<E>(element);
		elem.setPrev(null);
		elem.setNext(first);
		if (total == 0) {
			last = elem;
		}
		if (total > 0) {
			first.setPrev(elem);
		}
		first = elem;
		total++;
		// System.out.println(elem.toInfoString());
		// System.out.println("Added first: " + first);
	}

	/**
	 * Returns first Element of the List if the List is not empty, otherwise
	 * returns null
	 * 
	 * @return
	 */
	public Object getFirst() {
		return first.getObj();
	}

	/**
	 * Returns last Element of the List if the List is not empty, otherwise
	 * returns null
	 */
	public Object getLast() {
		return last.getObj();

	}

	/**
	 * Get element with passed index
	 * 
	 * @param index
	 * @return
	 */
	private LinkedElement<E> findElement(int index) {
		if (indexOK(index)) {

			LinkedElement<E> elem = first;
			int counter = 0;

			// Choose a way to find
			boolean wayAhead = (total / 2 > index);

			if (wayAhead) { // way forward
				while (counter < index) {
					elem = elem.getNext();
					counter++;
				}
			} else { // way backwards
				counter = total - 1;
				elem = last;
				while (counter > index) {
					elem = elem.getPrev();
					counter--;
				}
			}
			return elem;
		}
		return null;
	}

	// If index is within array boundary, removes element at specified index
	public boolean removeAt(int index) {

		if (indexOK(index)) {

			LinkedElement<E> elem = findElement(index);

			LinkedElement<E> prev = elem.getPrev();
			LinkedElement<E> next = elem.getNext();

			if (prev != null) {
				prev.setNext(next);
				// If the last element is being removed
				if (index == total - 1) {
					last = prev;
				}
			}
			if (next != null) {
				next.setPrev(prev);
				// If the first element is being removed
				if (index == 0) {
					first = next;
				}
			}
			total--;
			return true;
		}
		return false;
	}

	// if index is within array boundary, inserts an element at
	// specified index and returns true, otherwise returns false
	public boolean insertAt(int index, E element) {
		
		LinkedElement<E> oldElem = findElement(index);
		
		if (index == total) {
			addLast(element); // increments total
			return true;
		}

		if (index == 0) {
			addFirst(element); // increments total
			return true;
		}

		if (oldElem != null) {
			LinkedElement<E> newElem = new LinkedElement<E>(element);
			LinkedElement<E> prev = oldElem.getPrev();

			newElem.setPrev(prev);
			prev.setNext(newElem);

			newElem.setNext(oldElem);
			oldElem.setPrev(newElem);

			total++;
			return true;
		}
		return false;
	}

	// If index is within array boundary, returns Element
	// at specified index, otherwise returns null
	public Object getElement(int index) {
		LinkedElement<E> elem = findElement(index);
		if (elem != null) {
			return elem.getObj();
		}
		return null;

	}

	// if index is within array boundary, places specified Element
	// at specified position and returns true, otherwise returns false
	public boolean setElement(int index, E element) {
		LinkedElement<E> elem = findElement(index);
		if (elem != null) {
			elem.setObj(element);
			return true;
		}
		return false;
	}

	// returns a practice3.Vector containing elements of this List
	public Vector toVector() {
		Vector vec = new Vector(last.getIndex() + 1);
		LinkedElement<E> elem = first;
		for (int i = 0; i <= last.getIndex(); i++) {
			vec.addElement(elem);
			elem = elem.getNext();
		}
		return vec;
	}

	public String toString() {
		int endIndex = 0;
		LinkedElement<?> elem = first;
		StringBuffer sb = new StringBuffer();
		for (int j = 0; j < total; j++) {
			for (int i = 0; i < endIndex; i++) {
				elem = elem.getNext();
			}
			sb.append("LinkedList[" + j + "] = " + elem + "\t(finder: " + findElement(j) + ")");
			if (j < total - 1) {
				sb.append("\n");
			}
			elem = first;
			endIndex++;
		}
		return sb.toString();
	}

	public String toInfoString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < total; i++) {
			sb.append("LinkedList[" + i + "] = " + findElement(i).toInfoString());
			if (i < total - 1) {
				sb.append("\n");
			}
		}
		return sb.toString();
	}

	public int getSize() {
		return total;
	}

	/**
	 * Coherence testing method prints the test result
	 */
	public void testCoherence() {
		LinkedElement<E> elemf = first;
		LinkedElement<E> elemb = last;
		for (int i = 1; i < total; i++) {
			System.out.println(elemf + "\t|  " + elemb);
			elemf = elemf.getNext();
			elemb = elemb.getPrev();

		}
		System.out.println(elemf + "\t| " + elemb);
		System.out.println("LL Coherence:\tforward: " + (elemf == last) + "\tbackward: " + (elemb == first));

	}

}
