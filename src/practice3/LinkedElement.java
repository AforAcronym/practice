package practice3;

/**
 * Inner class for linked objects ;) This implementation is just for fun and
 * speed comparison.
 */
public class LinkedElement {
	private LinkedElement prev = null;
	private LinkedElement next = null;
	private Object obj = null;
	private int index;

	// Constructor
	public LinkedElement(Object obj) {
		this.obj = obj;
	}

	public LinkedElement getPrev() {
		return prev;
	}

	public void setPrev(LinkedElement prevElem) {
		this.prev = prevElem;
	}

	public LinkedElement getNext() {
		return next;
	}

	public void setNext(LinkedElement nextElem) {
		this.next = nextElem;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
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
