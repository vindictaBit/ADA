public interface InterfaceB<E> {
    /**
     * Add value to the tree. Tree can contain multiple equal values.
     *
     * @param value to add to the tree.
     * @return True if successfully added to tree.
     */
    public boolean add(E value);

    /**
     * Remove first occurrence of value in the tree.
     *
     * @param value to remove from the tree.
     * @return T value removed from tree.
     */
    public E remove(E value);

    /**
     * Clear the entire tree.
     */
    public void clear();

    /**
     * Does the tree contain the value.
     *
     * @param value to locate in the tree.
     * @return True if tree contains value.
     */
    public boolean contains(E value);

    /**
     * Get number of nodes in the tree.
     *
     * @return Number of nodes in the tree.
     */
    public int size();
}