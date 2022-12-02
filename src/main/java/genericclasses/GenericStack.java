package genericclasses;

import interfaces.IList;
import interfaces.IStack;

public class GenericStack<T> implements IStack<T> {

    private final IList<T> stackData;

    /**
     * Constructor. Initializes genericStack object through GenericArrayList object
     *
     * @param genericArrayList GenericArrayList object for stackData initialization
     */
    public GenericStack(GenericArrayList<T> genericArrayList){
        stackData = genericArrayList;
    }

    /**
     * Constructor. Initializes genericStack object through GenericLinkedList object
     *
     * @param genericLinkedList GenericLinkedList object for stackData initialization
     */
    public GenericStack(GenericLinkedList<T> genericLinkedList){
        stackData = genericLinkedList;
    }

    /**
     * Pushes an item onto the top of this stack
     *
     * @param element the element argument.
     */
    @Override
    public void push(T element) {
        stackData.add(0, element);
    }

    /**
     * Removes the object at the top of this stack and returns that
     * object as the value of this function.
     *
     * @return The object at the top of this stack
     */
    @Override
    public T pop() {
        T removedElement = stackData.get(0);
        stackData.remove(0);
        return removedElement;
    }

    /**
     * Looks at the object at the top of this stack without removing it
     * from the stack.
     *
     * @return the object at the top of this stack
     */
    @Override
    public T peek() {
        return stackData.get(0);
    }

    /**
     * Tests if this stack is empty.
     *
     * @return {@code true} if and only if this stack contains
     * no items; {@code false} otherwise.
     */
    @Override
    public boolean empty() {
        return stackData.isEmpty();
    }
}
