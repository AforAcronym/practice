package practice3;

public class AVLTreeLL {

	private static final int INITIAL_CAPACITY_DEGREE = 5;
//	private Node[] array;
	private int currentSize = 0;
	private Node root;

	/**************************************************************************
	 * Constructor
	 */
	public AVLTreeLL() {
//		array = new Node[(int) (Math.pow(2, INITIAL_CAPACITY_DEGREE) - 1)];
	}

	/**************************************************************************
	 * Constructor
	 * 
	 * @param numOfLevels
	 *            number of the tree levels as initial capacity
	 */
	public AVLTreeLL(final int numOfLevels) {
//		array = new Node[(int) (Math.pow(2, numOfLevels) - 1)];
	}

	/**************************************************************************
	 * Puts an element into the Tree, maintaining order and height conditions
	 * 
	 * @param element
	 */
	public void put(Object element) {
		if (currentSize == 0) {
			root = new Node (element );
		} else {
			
		}
		currentSize++;
	}

	/**************************************************************************
	 * Sub-tree height
	 * 
	 * @param index
	 * @return
	 */
//	private int height(final int index) {
//		if (array.length <= 2 * index) {
//			return 1; // or 0?
//		}
//		return 1 + Math.max(height(index * 2 + 1), height(index * 2 + 2));
//	}

	/**************************************************************************
	 * Rebalance the AVL Tree
	 */
	private void rebalance() {

	}

	/**************************************************************************
	 * Returns a Vector containing elements of this Tree in their search order
	 * 
	 * @return
	 */
	public Vector toVector() {
		return new Vector(0);
	}

	/**************************************************************************
	 * The AVL tree node class
	 * 
	 */
	private class Node {

		private Node parent;
		private Node leftChild;
		private Node rightChild;

		private int subTreeSize = 0;
		private Object obj;

		public Node(Object _obj) {
			obj = _obj;
		}

		public int getSubTreeSize() {
			return subTreeSize;
		}

		public void setSubTreeSize(int subTreeSize) {
			this.subTreeSize = subTreeSize;
		}

		public Object getObj() {
			return obj;
		}

		public void setObj(Object obj) {
			this.obj = obj;
		}

	}

}
