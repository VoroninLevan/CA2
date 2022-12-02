package interfaces;

public interface IStack<T> {

    /**
     * Pushes an item onto the top of this stack
     *
     * @param element the element argument.
     */
    void push(T element);

    /**
     * Removes the object at the top of this stack and returns that
     * object as the value of this function.
     *
     * @return The object at the top of this stack
     */
    T pop();

    /**
     * Looks at the object at the top of this stack without removing it
     * from the stack.
     *
     * @return the object at the top of this stack
     */
    T peek();

    /**
     * Tests if this stack is empty.
     *
     * @return {@code true} if and only if this stack contains
     * no items; {@code false} otherwise.
     */
    boolean empty();
}
