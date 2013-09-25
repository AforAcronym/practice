package practice3;

/**
 * Self-balancing Binary Search Tree using “Adelson-Velsky and Landis”
 * algorithm. Right child is
 * 
 */
public class AVLTree<T extends Comparable<?>> {

	private int capacityDegree = 5;
	private Object[] array;
	private int currentSize = 0;

	/**************************************************************************
	 * Constructor
	 */
	public AVLTree() {
		array = new Object[(int) (Math.pow(2, capacityDegree) - 1)];
	}

	/**************************************************************************
	 * Constructor
	 * 
	 * @param numOfLevels
	 *            number of the tree levels as initial capacityDegree
	 */
	public AVLTree(final int numOfLevels) {
		array = new Object[(int) (Math.pow(2, numOfLevels) - 1)];
	}

	/**************************************************************************
	 * Puts an element into the Tree, maintaining order and height conditions
	 * 
	 * @param element
	 */
	public void put(T element) {

		int index = 0;
		boolean added = false;

		while (!added) {

			if (array[index] == null) {
				array[index] = element;
				added = true;
			}

<<<<<<< HEAD
			if (element.compareTo(array[index]) < 0) {
=======
			if (element.compareTo((T) array[index]) < 0) {
>>>>>>> AVLgeneric
				index = 2 * index + 1;
			} else {
				index = 2 * index + 2;
			}

<<<<<<< HEAD
			if (index > array.length) {
=======
			if (index >= array.length) {
>>>>>>> AVLgeneric
				addCapacityLayer();
			}
		}

		if (needRebalance()) {
			rebalance();
		}

		currentSize++;
	}

	/**************************************************************************
	 * Add capacityDegree layer for the tree
	 */
	private void addCapacityLayer() {
		capacityDegree++;
		Object temparray[] = new Object[(int) Math.pow(2, capacityDegree) - 1];
		System.arraycopy(array, 0, temparray, 0, array.length);
	}

	/**************************************************************************
	 * Sub-tree height
	 * 
	 * @param index
	 * @return
	 */
	private int height(final int index) {
		if (array.length <= 2 * index) {
			return 1; // or 0?
		}
		return 1 + Math.max(height(index * 2 + 1), height(index * 2 + 2));
	}

	/**************************************************************************
	 * Check if the tree needs rebalancing
	 */
	private boolean needRebalance() {
		return (Math.abs(height(1) - height(2)) > 1);
	}

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

}
