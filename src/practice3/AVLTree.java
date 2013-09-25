package practice3;

/**
 * Self-balancing Binary Search Tree using “Adelson-Velsky and Landis”
 * algorithm. Right child is always bigger than its parent and left child is
 * always smaller.
 * 
 */
public class AVLTree<E extends Comparable<E>> {

	private int capacityLayers = 5;
	private Array<E> array;
	private int currentSize = 0;

	/**************************************************************************
	 * Constructor
	 */
	public AVLTree() {
		array = new Array<E>((int) (Math.pow(2, capacityLayers) - 1));
	}

	/**************************************************************************
	 * Constructor
	 * 
	 * @param numOfLevels
	 *            number of the tree levels as initial capacityDegree
	 */
	public AVLTree(final int numOfLevels) {
		array = new Array<E>((int) (Math.pow(2, numOfLevels) - 1));
	}

	/**************************************************************************
	 * Puts an element into the Tree, maintaining order and height conditions
	 * 
	 * @param element
	 */
	public void put(E element) {

		int index = 0;
		boolean added = false;

		while (!added) {
			
			// #1
			// To set or not to set...
			if (array.getElement(index) == null) {
				array.setElement(index, element);
				added = true;
				// NO break ! See #3
			}
			
			// #2
			// Choose between left and right positions, increasing the index
			if (array.getElement(index).compareTo(element) < 0) {
				index = 2 * index + 1;
			} else {
				index = 2 * index + 2;
			}

			// #3
			// If there is no break in #1 the capacity is increased for the next
			// layer due to #2 and new element can be safely added
			if (index >= array.getSize()) {
				addCapacityLayer();
			}
		}

		if (needRebalance()) {
			rebalance();
		}

		currentSize++;
	}

	/**************************************************************************
	 * Add capacity layer for the tree
	 */
	private void addCapacityLayer() {
		array.changeCapacityBy( (int) Math.pow(2, capacityLayers) );
		capacityLayers++;
	}

	/**************************************************************************
	 * Sub-tree height
	 * 
	 * @param index
	 * @return
	 */
	private int height(final int index) {
		
		// Left and right children indices
		int childIndex_l = index * 2 + 1;
		int childIndex_r = index * 2 + 2;

		boolean childIsNull_l = (array.getElement(index * 2 + 1) == null);
		boolean childIsNull_r = (array.getElement(index * 2 + 2) == null);
		
		// The index is valid but the node is null
		if (index < array.getSize() && array.getElement(index) == null) {
			return 0;
		}
		
		// The current node's children do not exist
		if (array.getSize() <= 2 * index || (childIsNull_l && childIsNull_r) ) {
			return 1; // or 0?
		}
		
		// No left (lesser) child
		if (childIsNull_l) {
			return 1 + height(childIndex_r);
		}
		
		// No right (bigger) child		
		if (childIsNull_r) {
			return 1 + height(childIndex_l);
		}
		
		return 1 + Math.max(height(childIndex_l), height(childIndex_r));
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
		// TODO
		// private void rotateRight
		// private void rotateLeft
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
