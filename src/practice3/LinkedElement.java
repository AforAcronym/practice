package practice3;

/**
 * Inner class for linked objects ;) This implementation is just for fun and
 * speed comparison.
 */
public class LinkedElement<T> {
	private LinkedElement<T> prev = null;
	private LinkedElement<T> next = null;
	private T obj = null;
	private int index;

	// Constructor
	public LinkedElement(T obj) {
		this.obj = obj;
	}

	public LinkedElement<T> getPrev() {
		return prev;
	}

	public void setPrev(LinkedElement<T> prevElem) {
		this.prev = prevElem;
	}

	public LinkedElement<T> getNext() {
		return next;
	}

	public void setNext(LinkedElement<T> nextElem) {
		this.next = nextElem;
	}

	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("LLE: ");
		sb.append(obj.toString());
		return sb.toString();
	}

	public String toInfoString() {
		StringBuffer sb = new StringBuffer();
		sb.append("LLE: ");
		sb.append(obj.toString());
		sb.append("\t\tprev: ");
		sb.append(this.prev);
		sb.append("\t\tnext: ");
		sb.append(this.next);
		return sb.toString();
	}

}
