package practice3;

/**
 * Self-balancing Binary Search Tree using “Adelson-Velsky and Landis”
 * algorithm. Right child is always bigger than its parent and left child is
 * always smaller.
 * 
 */
public class AVLTree<E extends Comparable<E>> {

	// Number of layers in capacity of the tree
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
	 * Index of the left child for the current index in the inner array
	 */
	private int getLeftChildIndex(final int index) {
		return 2 * index + 1;
	}

	/**************************************************************************
	 * Index of the right child for the current index in the inner array
	 */
	private int getRightChildIndex(final int index) {
		return 2 * index + 2;
	}

	/**************************************************************************
	 * Index of the parent node for the current index in the inner array
	 */
	private int getParentIndex(final int index) {
		return (index % 2 == 0) ? (index - 2) / 2 : (index - 1) / 2;
	}

	/**************************************************************************
	 * Puts an element into the Tree, maintaining order and height conditions
	 * 
	 * @param element
	 */
	public void put(final E element) {

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
				index = getLeftChildIndex(index);
			} else {
				index = getRightChildIndex(index);
			}

			// #3
			// If there is no break in #1 the capacity is increased for the next
			// layer due to #2 and new element can be safely added
			if (index >= array.getSize()) {
				addCapacityLayer();
			}
		}

		rebalance();
		currentSize++;
	}

	/**************************************************************************
	 * Add capacity layer for the tree
	 */
	private void addCapacityLayer() {
		array.changeCapacityBy((int) Math.pow(2, capacityLayers));
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
		int childIndex_l = getLeftChildIndex(index);
		int childIndex_r = getRightChildIndex(index);

		boolean childIsNull_l = (array.getElement(childIndex_l) == null);
		boolean childIsNull_r = (array.getElement(childIndex_r) == null);

		if (index < array.getSize() && array.getElement(index) == null) {
			// The index is valid but the node is null
			return 0;
		}

		if (array.getSize() <= 2 * index || (childIsNull_l && childIsNull_r)) {
			// The current node's children do not exist
			return 1; // or 0?
		}

		if (childIsNull_l) { // No left (lesser) child
			return 1 + height(childIndex_r);
		}

		if (childIsNull_r) { // No right (bigger) child
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
	 * Get number of the layer for passed index in the inner array
	 * 
	 * @param index
	 */
	private int getLayerFromIndex(final int index) {
		int layer = 0;
		while (index > Math.pow(2, layer) - 1) {
			layer++;
		}
		return layer + 1;
	}

	/**************************************************************************
	 * Rebalance the AVL Tree
	 */
	private void rebalance() {
		// Fictitious names for now
		int leftIndex = 0;
		int rightIndex = 0;
		int seconParentFromTheEnd = getParentIndex(getParentIndex(array.getLastPosition()));
		
		while (needRebalance()) {
			
			seconParentFromTheEnd = getParentIndex(getParentIndex(array.getLastPosition()));
			leftIndex = getLeftChildIndex( seconParentFromTheEnd);
			rightIndex = getRightChildIndex( seconParentFromTheEnd);
			
			if (height(leftIndex) - height(rightIndex) < -1) {
				// Left sub-tree is shorter
				if (array.getElement(rightIndex) == null) {
					rotateRight(getParentIndex(leftIndex));
				}
			} else if (height(leftIndex) - height(rightIndex) > 1) {
				// Left sub-tree is longer
				if (array.getElement(leftIndex) == null) {
					rotateLeft(getParentIndex(rightIndex));
				}
			}
		}
	}

	/**************************************************************************
	 * Rotate left at index
	 * 
	 * @param index
	 *            index of the current root that will be moved to his right
	 *            child position and replaced with his left child
	 */
	private void rotateRight(final int index) {

		Array<E> newArray = array.getCopy();

		// Old Root is at the passed index
		E oldRoot = array.getElement(index);

		// New root is the left child of Old Root
		E newRoot = array.getElement(getLeftChildIndex(index));

		// Move right child of Old Root down left
		shiftLeaf(getRightChildIndex(index), getRightChildIndex(getRightChildIndex(index)), newArray);

		// Move Old Root down right
		newArray.setElement(getRightChildIndex(index), oldRoot);

		// Move New Root to the root place (at index)
		newArray.setElement(index, newRoot);

		// Move New Root's left leaf up to him
		shiftLeaf(getLeftChildIndex(getLeftChildIndex(index)), getLeftChildIndex(index), newArray);

		// Move New Root's right leaf to Old Root's leftleaf position
		shiftLeafAside(getRightChildIndex(getLeftChildIndex(index)), getLeftChildIndex(getRightChildIndex(index)), newArray, 1);

		array = newArray;

	}

	/**************************************************************************
	 * Rotate left at index
	 * 
	 * @param index
	 *            index of the current root that will be moved to his left child
	 *            position and replaced with his right child
	 */
	private void rotateLeft(final int index) {
		Array<E> newArray = array.getCopy();

		// Old Root is at the passed index
		E oldRoot = array.getElement(index);

		// New root is the right child of Old Root
		E newRoot = array.getElement(getRightChildIndex(index));

		// Move left child of Old Root down left
		shiftLeaf(getLeftChildIndex(index), getLeftChildIndex(getLeftChildIndex(index)), newArray);

		// Move Old Root down left
		newArray.setElement(getLeftChildIndex(index), oldRoot);

		// Move New Root to the root place (at index)
		newArray.setElement(index, newRoot);

		// Move New Root's right leaf up to him
		shiftLeaf(getRightChildIndex(getRightChildIndex(index)), getRightChildIndex(index), newArray);

		// Move New Root's left leaf to Old Root's right leaf position
		shiftLeafAside(getLeftChildIndex(getRightChildIndex(index)), getRightChildIndex(getLeftChildIndex(index)), newArray, 1);

		array = newArray;
	}

	/**************************************************************************
	 * Shifting a leaf down or up depending on specified root's fromIndex and
	 * destIndex (made by recursion)
	 * 
	 * @param fromIndex
	 *            index from which to move
	 * @param destIndex
	 *            destination index
	 * @param newArray
	 *            a new array reference
	 */
	private void shiftLeaf(final int fromIndex, final int destIndex, Array<E> newArray) {
		if (Math.min(fromIndex, destIndex) < array.getSize()) {
			newArray.setElement(destIndex, array.getElement(fromIndex));
			
			if (	!(	array.getElement(getLeftChildIndex(fromIndex)) == null 
					&&  array.getElement(getLeftChildIndex(destIndex)) == null)) {
				shiftLeaf(getLeftChildIndex(fromIndex), getLeftChildIndex(destIndex), newArray);
			}
			
			if (	!(	array.getElement(getRightChildIndex(fromIndex)) == null 
					&&  array.getElement(getRightChildIndex(destIndex)) == null)) {
				shiftLeaf(getRightChildIndex(fromIndex), getRightChildIndex(destIndex), newArray);
			}
		}
	}

	/**************************************************************************
	 * Shifting a leaf left or right depending on specified root's fromIndex and
	 * destIndex (made by recursion)
	 * 
	 * @param fromIndex
	 *            index from which to move
	 * @param destIndex
	 *            destination index
	 * @param newArray
	 *            a new array reference
	 */
	private void shiftLeafAside(int fromIndex, int destIndex, Array<E> newArray, int level) {
		int sign = (fromIndex < destIndex) ? 1 : -1;
		newArray.setElement(fromIndex + sign * level, array.getElement(fromIndex));
		++level;
		shiftLeafAside(getLeftChildIndex(fromIndex), getLeftChildIndex(destIndex), newArray, level);
		shiftLeafAside(getRightChildIndex(fromIndex), getRightChildIndex(destIndex), newArray, level);
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
