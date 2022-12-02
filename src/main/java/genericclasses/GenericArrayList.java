package genericclasses;

import interfaces.IList;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <h1>GenericArrayList</h1>
 * <p>Generic array list which provides capabilities for storing range of data. Implements IList and Iterable<br>
 * interfaces.</p>
 * @see interfaces.IList
 * @see java.lang.Iterable
 * @version 1.0.0
 * @param <T> Generic data
 */
public class GenericArrayList<T> implements IList<T>, Iterable<T> {

    /**Generic array which stores elements*/
    private T[] buffer;
    /**Initial capacity of the new GenericArrayList*/
    private static final int INITIAL_CAPACITY = 2;
    /**Holds information on the next free location for a new element*/
    private int nextFreeLoc;
    /**Represents current capacity of the GenericArrayList*/
    private int currentCapacity;

    /**
     * Class constructor
     */
    public GenericArrayList(){
        currentCapacity = INITIAL_CAPACITY;
        nextFreeLoc = 0;
        buffer = (T[]) new Object[currentCapacity];
    }

    /**
     * Adds a new element in the end of the list
     *
     * @param element Generic element
     */
    @Override
    public void add(T element) {
        checkCapacity();
        buffer[nextFreeLoc++] = element;
    }

    /**
     * Adds a new element at the specific location of the list based on the index provided.<br>
     * Shifts the remaining elements (on the right) to the right
     *
     * @param index Index location to add an element
     * @param element Generic element
     */
    @Override
    public void add(int index, T element) {
        checkIndexRange(index);
        checkCapacity();

        T[] temp = Arrays.copyOfRange(buffer, index, currentCapacity - 1);
        buffer[index] = element;
        int tempCounter = 0;
        for(int i = index + 1; i < buffer.length; i++){
            buffer[i] = temp[tempCounter++];
        }
        nextFreeLoc++;
    }

    /**
     * Sets a new element at the specific location based on index provided. Replaces the existing element.
     *
     * @param index Index location to set an element
     * @param element Generic element
     * @return Replaced element
     */
    @Override
    public T set(int index, T element) {
        checkIndexRange(index);
        T replacedElement = buffer[index];
        buffer[index] = element;
        return replacedElement;
    }

    /**
     * Retrieves an existing element form the list based on index provided.
     *
     * @param index Index location to get an element
     * @return Existing element at given index
     */
    @Override
    public T get(int index) {
        checkIndexRange(index);
        return buffer[index];
    }

    /**
     * Returns the size of the list, representing amount of elements stored
     *
     * @return Amount of elements stored
     */
    @Override
    public int size() {
        return currentCapacity;
    }

    /**
     * Removes an existing element at the provided index
     *
     * @param index Index location to remove an element
     * @return Removed element
     */
    @Override
    public T remove(int index) {
        checkIndexRange(index);

        T removedElement = buffer[index];

        int newCapacity = currentCapacity - 1;

        if(index==0){
            buffer = Arrays.copyOfRange(buffer, 1, currentCapacity);
        } else {
            T[] tempStart = Arrays.copyOfRange(buffer, 0, index);
            T[] tempEnd = Arrays.copyOfRange(buffer, index + 1, currentCapacity);
            buffer = Arrays.copyOf(tempStart, newCapacity);
            int tempCounter = 0;
            for(int i = index; i < buffer.length; i++){
                buffer[i] = tempEnd[tempCounter++];
            }
        }
        currentCapacity--;
        nextFreeLoc--;
        return removedElement;
    }

    /**
     * Removes an existing element based on the value provided. Returns true if element with given value was found and<br>
     * removed. Returns false otherwise.
     *
     * @param element Element value to search and remove
     * @return True if element removed, false if was not removed
     */
    @Override
    public boolean remove(T element) {
        for(int i = 0; i < buffer.length; i++){
            if(element.equals(buffer[i])) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether list is empty
     *
     * @return True if empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return nextFreeLoc == 0;
    }

    /**
     * Checks whether list contains given element
     *
     * @param element element value to search through list
     * @return True if list contains given element, false otherwise
     */
    @Override
    public boolean contains(T element) {
        for (T t : buffer) {
            if (element.equals(t)) return true;
        }
        return false;
    }

    /**
     * Returns a generic Iterator object
     *
     * @return GenericArrayListIterator
     */
    @Override
    public Iterator<T> iterator() {
        return new GenericArrayListIterator();
    }

    /**
     * Checks whether list has enough capacity to accept a new element. Extends capacity when required
     */
    private void checkCapacity(){
        int newCapacity = nextFreeLoc + 1;
        if(currentCapacity < newCapacity) extendCapacity(newCapacity);
    }

    /**
     * Extends capacity of the list. Copies buffer array with a new capacity
     *
     * @param newCapacity New capacity for the buffer array
     */
    private void extendCapacity(int newCapacity){
        currentCapacity = newCapacity;
        buffer = Arrays.copyOf(buffer, newCapacity);
    }

    /**
     * Checks whether provided index is within a range of the list. Throws an ArrayIndexOutOfBoundsException in<br>
     * case of illegal index
     *
     * @throws ArrayIndexOutOfBoundsException In case if provided index is outside the list index range
     * @param index Index to check against list index range
     */
    private void checkIndexRange(int index){
        if(index > currentCapacity || index < 0) throw new ArrayIndexOutOfBoundsException(
                String.format("Index is out of range. Index: %d, Size: %d", index, currentCapacity));
    }

    /**
     * <h1>GenericArrayListIterator</h1>
     * <p>Inner class which provides Iterator capabilities</p>
     */
    class GenericArrayListIterator implements Iterator<T>{

        /**Keeps track against capacity*/
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor != currentCapacity;
        }

        @Override
        public T next() {
            if(cursor == nextFreeLoc){
                throw new NoSuchElementException();
            }
            return buffer[cursor++];
        }
    }
}
