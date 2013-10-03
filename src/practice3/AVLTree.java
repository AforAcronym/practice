package practice3;

/**
 * Self-balancing Binary Search Tree using “Adelson-Velsky and Landis”
 * algorithm. Right child is always bigger than its parent and left child is
 * always smaller.
 */
public class AVLTree<E extends Comparable<E>> {

    // Number of layers in capacity of the tree
    private int capacityLayers = 2;
    private Array<E> array;
    private int currentSize = 0;

    /**
     * Constructor
     */
    public AVLTree() {
        array = new Array<E>((int) (Math.pow(2, capacityLayers) - 1));
    }

    /**
     * Constructor
     *
     * @param numOfLevels number of the tree levels as initial capacityDegree
     */
    public AVLTree(final int numOfLevels) {
        array = new Array<E>((int) (Math.pow(2, numOfLevels) - 1));
    }

    /**
     * Index of the left child for the current index in the inner array
     */
    private int getLeftChildIndex(final int index) {
        return 2 * index + 1;
    }

    /**
     * Index of the right child for the current index in the inner array
     */
    private int getRightChildIndex(final int index) {
        return 2 * index + 2;
    }

    /**
     * Index of the parent node for the current index in the inner array
     */
    private int getParentIndex(final int index) {
        if (index == 0) {
            return 0;
        }
        return (index % 2 == 0) ? (index - 2) / 2 : (index - 1) / 2;
    }

    /**
     * Index of the parent node for the current index in the inner array
     */
    private int getSiblingIndex(final int index) {
        if (index == 0) {
            return 0;
        }
        if (index % 2 == 0) {
            return getLeftChildIndex(getParentIndex(index));
        } else {
            return getRightChildIndex(getParentIndex(index));
        }
    }

    /**
     * Puts an element into the Tree, maintaining order and height conditions
     * FIXME bloated code, can be implemented via recursion
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
                currentSize++;
                break;
                // NO break ! See #3
            }

            // #2
            // Choose between left and right positions, increasing the index
            if (array.getElement(index).compareTo(element) > 0) {
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
    }

    /**
     * Add capacity layer for the tree
     */
    private void addCapacityLayer() {
        array.changeCapacityBy((int) Math.pow(2, capacityLayers));
        capacityLayers++;
    }

    /**
     * Sub-tree height
     *
     * @param index
     * @return
     */
    private int height(final int index) {

        // Left and right children indices
        int leftChildIndex = getLeftChildIndex(index);
        int rightChildIndex = getRightChildIndex(index);

        boolean childIsNull_l = (array.getElement(leftChildIndex) == null);
        boolean childIsNull_r = (array.getElement(rightChildIndex) == null);

        if (index < array.getSize() && array.getElement(index) == null) {
            // The index is valid but the node is null
            return 0;
        }

        if (array.getSize() <= 2 * index || (childIsNull_l && childIsNull_r)) {
            // The current node's children do not exist
            return 1; // or 0?
        }

        if (childIsNull_l) { // No left (lesser) child
            return 1 + height(rightChildIndex);
        }

        if (childIsNull_r) { // No right (bigger) child
            return 1 + height(leftChildIndex);
        }

        return 1 + Math.max(height(leftChildIndex), height(rightChildIndex));
    }

    /**
     * Check if the tree needs rebalancing
     */
    private boolean needRebalance() {
        return (Math.abs(height(1) - height(2)) > 1);
    }

    /**
     * Get number of the layer for passed index in the inner array
     *
     * @param index
     */
    @SuppressWarnings("unused")
    private int getLayerFromIndex(final int index) {
        int layer = 0;
        while (index > Math.pow(2, layer) - 1) {
            layer++;
        }
        return layer;
    }

    /**
     * Rebalance the AVL Tree
     */
    private void rebalance() {
        // Fictitious names for now
        int leftIndex = 0;
        int rightIndex = 0;
        int parentIndex = getParentIndex(getParentIndex(array.getLastPosition()));

        while (needRebalance()) {

            leftIndex = getLeftChildIndex(parentIndex);
            rightIndex = getRightChildIndex(parentIndex);

            if (height(leftIndex) - height(rightIndex) > 1) {
                // Left branch is two elements longer
                rotateRight(parentIndex);

            } else if (height(leftIndex) - height(rightIndex) < -1) {
                // Right branch is two elements longer
                rotateLeft(parentIndex);

            }

            if (parentIndex > 0) {
                // If the rotation was unsuccessful, go one layer higher
                parentIndex = getParentIndex(parentIndex);
            } else {
                rebalance();
            }

        }
    }

    /**
     * Rotate left at index
     *
     * @param index index of the current root that will be moved to his right
     *              child position and replaced with his left child
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

    /**
     * Rotate left at index
     *
     * @param index index of the current root that will be moved to his left child
     *              position and replaced with his right child
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

    /**
     * Shifting a leaf down or up depending on specified root's fromIndex and
     * destIndex (made by recursion)
     *
     * @param fromIndex index from which to move
     * @param destIndex destination index
     * @param newArray  a new array reference
     */
    private void shiftLeaf(final int fromIndex, final int destIndex, Array<E> newArray) {
        if (Math.min(fromIndex, destIndex) < array.getSize()) {
            newArray.setElement(destIndex, array.getElement(fromIndex));

            // There is no point to move nulls, so the condition is cheked for
            if (!(array.getElement(getLeftChildIndex(fromIndex)) == null
                    && array.getElement(getLeftChildIndex(destIndex)) == null)) {

                shiftLeaf(getLeftChildIndex(fromIndex), getLeftChildIndex(destIndex), newArray);
            }

            if (!(array.getElement(getRightChildIndex(fromIndex)) == null
                    && array.getElement(getRightChildIndex(destIndex)) == null)) {

                shiftLeaf(getRightChildIndex(fromIndex), getRightChildIndex(destIndex), newArray);
            }
        }
    }

    /**
     * Shifting a leaf left or right depending on specified root's fromIndex and
     * destIndex (made by recursion)
     *
     * @param fromIndex index from which to move
     * @param destIndex destination index
     * @param newArray  a new array reference
     */
    private void shiftLeafAside(int fromIndex, int destIndex, Array<E> newArray, int level) {

        if (Math.max(fromIndex, destIndex) > array.getSize()) {
            return;
        }

        int sign = (fromIndex < destIndex) ? 1 : -1;

        newArray.setElement(fromIndex + sign * level, array.getElement(fromIndex));

        ++level;

        shiftLeafAside(getLeftChildIndex(fromIndex), getLeftChildIndex(destIndex), newArray, level);
        shiftLeafAside(getRightChildIndex(fromIndex), getRightChildIndex(destIndex), newArray, level);
    }

    /**
     * toString
     */
    public String toString() {

        E el;
        // Search for maximum element length
        //		int maxlen = 0;
        //		int ellen;
        //
        //		for (int i = 0; i < array.getSize(); i++) {
        //			el = array.getElement(i);
        //			if (el != null) {
        //				ellen = el.toString().length();
        //				maxlen = ellen > maxlen ? ellen : maxlen;
        //			}
        //
        //		}

        // Set spacer length
        String space = "    ";
        String nullElemHolder = "....";
        String outFormat = "[%2s]";

        int index = 0;
        int layerIndex = 1;

        StringBuffer sb = new StringBuffer();

        while (layerIndex <= capacityLayers) {
            for (int i = 1; i < Math.pow(2, capacityLayers - layerIndex); ++i) {
                sb.append(space);
            }
            for (int i = index; i <= Math.pow(2, layerIndex) - 2; ++i) {
                el = array.getElement(i);
                if (el != null) {
                    // sb.append(el);
                    sb.append(String.format(outFormat, el.toString()));
                } else {
                    sb.append(nullElemHolder);
                }
                for (int k = 1; k < Math.pow(2, capacityLayers - layerIndex + 1); ++k) {
                    sb.append(space);
                }
            }

            sb.append("\n\n");
            ++layerIndex;
            index = (int) (Math.pow(2, layerIndex - 1) - 1);
        }


        return sb.toString();
    }

    /**
     * Returns a Vector containing elements of this Tree in their search order
     *
     * @return
     */
    public Vector toVector() {
        Vector<E> vec = new Vector<E>(currentSize);
        for (int i = 0; i < currentSize; ++i) {
            if (array.getElement(i) != null) {
                vec.addElement(array.getElement(i));
            }
        }
        return vec;
    }

}
