package interfaces;

import java.util.Iterator;

public interface IList<T> {

    /**
     * Appends given element in the end of the list
     *
     * @param element Element to add
     */
    void add(T element);

    /**
     * Inserts given element at the specified index, shifting rest of the elements to the right
     *
     * @param index Index location to add
     * @param element Element to add
     */
    void add(int index, T element);

    /**
     * Replaces element at specified index with given element
     *
     * @param index Index location to set
     * @param element Element to set
     * @return
     */
    T set(int index, T element);

    /**
     * Returns element which exists at the specified index
     *
     * @param index Index location to get
     * @return Existing element
     */
    T get(int index);

    /**
     * Returns size of the list
     *
     * @return Size of the list
     */
    int size();

    /**
     * Removes element at the specified index, shifting rest of the elements to the left
     *
     * @param index Index location to remove
     * @return Removed element
     */
    T remove(int index);

    /**
     * Remove element, if it exists in the list, shifting rest of the elements to the left
     *
     * @param element Element to remove
     * @return true if element was found and removed, false otherwise
     */
    boolean remove(T element);

    /**
     * Checks whether list is empty
     *
     * @return true if list is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Checks whether given element exists in the list
     *
     * @param element Element to be checked in list
     * @return true if element was found, false otherwise
     */
    boolean contains(T element);

    /**
     * Iterator
     *
     * @return Iterator object
     */
    Iterator<T> iterator();

}
