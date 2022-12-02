package genericclasses;

import interfaces.IList;
import interfaces.IQueue;

public class GenericQueue<T> implements IQueue<T> {

    private final IList<T> queueData;

    /**
     * Constructor. Initializes genericQueue object through GenericArrayList object
     *
     * @param genericArrayList GenericArrayList object for queueData initialization
     */
    public GenericQueue(GenericArrayList<T> genericArrayList){
        queueData = genericArrayList;
    }

    /**
     * Constructor. Initializes genericQueue object through GenericLinkedList object
     *
     * @param genericLinkedList GenericLinkedList object for queueData initialization
     */
    public GenericQueue(GenericLinkedList<T> genericLinkedList){
        queueData = genericLinkedList;
    }

    /**
     * Inserts the specified element into the queue
     *
     * @param element the element argument.
     */
    @Override
    public void enqueue(T element) {
        queueData.add(element);
    }

    /**
     * Retrieves and removes the head of this queue.
     *
     * @return the head of this queue
     */
    @Override
    public T dequeue() {
        T removedElement = queueData.get(0);
        queueData.remove(0);
        return removedElement;
    }

    /**
     * Retrieves, but does not remove, the head of this queue.
     *
     * @return the head of this queue
     */
    @Override
    public T first() {
        return queueData.get(0);
    }

    /**
     * Tests if this Queue is empty.
     * @return {@code true} if and only if this queue contains
     * no items; {@code false} otherwise.
     */
    @Override
    public boolean empty() {
        return queueData.isEmpty();
    }
}
