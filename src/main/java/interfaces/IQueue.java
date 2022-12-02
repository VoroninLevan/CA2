package interfaces;

public interface IQueue<T> {

    /**
     * Inserts the specified element into the queue
     *
     * @param element the element argument.
     */
    void enqueue(T element);

    /**
     * Retrieves and removes the head of this queue.
     *
     * @return the head of this queue
     */
    T dequeue();

    /**
     * Retrieves, but does not remove, the head of this queue.
     *
     * @return the head of this queue
     */
    T first();

    /**
     * Tests if this Queue is empty.
     * @return {@code true} if and only if this queue contains
     * no items; {@code false} otherwise.
     */
    boolean empty();
}
