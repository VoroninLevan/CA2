package genericclasses;

import interfaces.IList;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <h1>GenericLinkedList</h1>
 * <p>Generic linked list which provides capabilities for storing range of data. Implements IList and Iterable
 * interfaces.</p>
 * @see interfaces.IList
 * @see java.lang.Iterable
 * @version 1.0.0
 * @param <T> Generic data
 */
public class GenericLinkedList<T> implements IList<T>, Iterable<T> {

    /**Head node tracks the top of the list*/
    Node<T> head;
    /**Tail node tracks the last element in the list*/
    Node<T> tail;
    /**Represents size of the list*/
    private int listSize;

    /**
     * Adds new node to the list
     *
     * @param element Element to add
     */
    @Override
    public void add(T element) {
        if(head==null) {
            linkFirstNode(element);
        } else {
            linkNode(element);
        }
        listSize++;
    }

    /**
     * Adds new node to the list at specified index location
     *
     * @param index Index location to add
     * @param element Element to add
     */
    @Override
    public void add(int index, T element) {
        checkIndexRange(index);

        if(head==null){
            linkFirstNode(element);
        } else if(index == listSize){
            linkNode(element);
        } else {
            linkNodeAndShift(index, element);
        }
        listSize++;
    }

    /**
     * Replaces existing node with given new node based on index provided
     *
     * @param index Index location to set
     * @param element Element to set
     * @return Element of the node
     */
    @Override
    public T set(int index, T element) {
        checkIndexRange(index);

        return replaceNode(index, element);
    }

    /**
     * Returns element of the existing node based on index provided
     *
     * @param index Index location to get
     * @return Element of the node
     */
    @Override
    public T get(int index) {
        checkIndexRange(index);

        return findNodeByIndex(index).element;
    }

    /**
     * Returns size of the linked list
     *
     * @return Size of the linked list
     */
    @Override
    public int size() {
        return listSize;
    }

    /**
     * Removes existing node based on index provided
     *
     * @param index Index location to remove
     * @return Element of the removed node
     */
    @Override
    public T remove(int index) {
        checkIndexRange(index);
        Node<T> retrievedNode = findNodeByIndex(index);
        if(listSize == 1){
            unlinkLastNode(retrievedNode);
        } else if(retrievedNode == head){
            unlinkHeadNode(retrievedNode);
        } else if(retrievedNode == tail){
            unlinkTailNode(retrievedNode);
        } else {
            unlinkNode(retrievedNode);
        }
        listSize--;
        return retrievedNode.element;
    }

    /**
     * Removes existing node based on comparison between stored element and element provided
     *
     * @param element Element to remove
     * @return true if node was found and removed, false otherwise
     */
    @Override
    public boolean remove(T element) {
        Node<T> retrievedNode = findNodeByElement(element);
        if(retrievedNode == null) return false;
        if(listSize == 1){
            unlinkLastNode(retrievedNode);
        } else if(retrievedNode == head){
            unlinkHeadNode(retrievedNode);
        } else if(retrievedNode == tail){
            unlinkTailNode(retrievedNode);
        } else {
            unlinkNode(retrievedNode);
        }
        listSize--;
        return true;
    }

    /**
     * Checks whether list is empty
     *
     * @return true if list is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return listSize == 0;
    }

    /**
     * Checks whether list contains a given element
     *
     * @param element Element to be checked in list
     * @return true if element exists in one of the nodes, false otherwise
     */
    @Override
    public boolean contains(T element) {
        Node<T> retrievedNode = findNodeByElement(element);
        return retrievedNode != null;
    }

    /**
     * Returns an iterator object
     *
     * @return GenericLinkedList object
     */
    @Override
    public Iterator<T> iterator() {
        return new GenericLinkedListIterator();
    }

    /**
     * Links very first node of the list
     *
     * @param element Element of the node
     */
    private void linkFirstNode(T element){
        head = tail = new Node<>(null, element, null);

    }

    /**
     * Links new node to the end of the list
     *
     * @param element Element of the node
     */
    private void linkNode(T element){
        Node<T> newNode = new Node<>(tail, element, null);
        tail.nextNode = newNode;
        tail = newNode;
    }

    /**
     * Links new node at specified index location and shifts the rest nodes on the right of it<br>
     * to the right
     *
     * @param index Desired index location
     * @param element Element of the node
     */
    private void linkNodeAndShift(int index, T element){
        Node<T> retrievedNode = findNodeByIndex(index);
        Node<T> prevNode = retrievedNode.prevNode;
        Node<T> newNode = new Node<>(retrievedNode.prevNode, element, retrievedNode);
        retrievedNode.prevNode = newNode;
        if(retrievedNode == head) {
            head = newNode;
        } else {
            prevNode.nextNode = newNode;
        }
    }

    /**
     * Replaces existent node with a new node re-links nodes to a new one
     *
     * @param index Desired index location
     * @param element Element of the node
     * @return Element of the node
     */
    private T replaceNode(int index, T element){
        Node<T> retrievedNode = findNodeByIndex(index);
        T replacedElement = retrievedNode.element;
        Node<T> prevNode = retrievedNode.prevNode;
        Node<T> nextNode = retrievedNode.nextNode;
        Node<T> newNode = new Node<>(prevNode, element, nextNode);
        retrievedNode.prevNode = newNode;
        if(retrievedNode == head) {
            head = newNode;
            nextNode.prevNode = newNode;
        } else if(retrievedNode == tail){
            tail = newNode;
            prevNode.nextNode = newNode;
        } else {
            nextNode.prevNode = newNode;
            prevNode.nextNode = newNode;
        }
        return replacedElement;
    }

    /**
     * Unlinks head (top) node from the list and re-links related nodes
     *
     * @param nodeToUnlink Node to unlink
     */
    private void unlinkHeadNode(Node<T> nodeToUnlink){
        Node<T> nextNode = nodeToUnlink.nextNode;
        nextNode.prevNode = null;
        head = nextNode;
    }

    /**
     * Unlinks tail (last) node from the list and re-links related nodes
     *
     * @param nodeToUnlink Node to unlink
     */
    private void unlinkTailNode(Node<T> nodeToUnlink){
        Node<T> prevNode = nodeToUnlink.prevNode;
        prevNode.nextNode = null;
        tail = prevNode;
    }

    /**
     * Unlinks last remaining node from the list and re-links related nodes
     *
     * @param nodeToUnlink Node to unlink
     */
    private void unlinkLastNode(Node<T> nodeToUnlink){
        System.out.println(nodeToUnlink.nextNode + ", " + nodeToUnlink.prevNode);
        head = null;
        tail = null;
    }

    /**
     * Unlinks node from the list and re-links related nodes
     *
     * @param nodeToUnlink Node to unlink
     */
    private void unlinkNode(Node<T> nodeToUnlink){
        Node<T> prevNode = nodeToUnlink.prevNode;
        Node<T> nextNode = nodeToUnlink.nextNode;
        prevNode.nextNode = nextNode;
        nextNode.prevNode = prevNode;
    }

    /**
     * Finds node by index provided
     *
     * @param index Desired index location
     * @return Existing node
     */
    private Node<T> findNodeByIndex(int index){
        Node<T> nodeToReturn = null;
        Node<T> currentNode = head;
        int indexCounter = 0;
        while (currentNode != null){
            if(index == indexCounter){
                nodeToReturn = currentNode;
                break;
            }
            indexCounter++;
            currentNode = currentNode.nextNode;
        }
        return nodeToReturn;
    }

    /**
     * Finds node by containing element and element provided
     *
     * @param element Element of the node
     * @return Existing node
     */
    private Node<T> findNodeByElement(T element){
        Node<T> nodeToReturn = null;
        Node<T> currentNode = head;
        while (currentNode != null){
            if (currentNode.element.equals(element)){
                nodeToReturn = currentNode;
                break;
            }
            currentNode = currentNode.nextNode;
        }
        return nodeToReturn;
    }

    /**
     * Checks index range against index provided
     *
     * @param index Desired index location
     */
    private void checkIndexRange(int index){
        if (index < 0 || index > listSize)
            throw new ArrayIndexOutOfBoundsException(
                String.format("Index is out of range. Index: %d, Size: %d", index, listSize));
    }

    /**
     * Test method, prints element to the out
     */
    public void printList()
    {
        //us this to "walk" or traverse the list
        Node<T> current = head;
        while(current != null){
            System.out.print(current.element + ", ");
            current = current.nextNode;
        }
    }

    /**
     * <h1>GenericLinkedListIterator</h1>
     * <p>Inner class which provides Iterator capabilities</p>
     */
    class GenericLinkedListIterator implements Iterator<T>{

        private Node<T> currentNode = head;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public T next() {
            if(currentNode == null){
                throw new NoSuchElementException();
            }
            T element = currentNode.element;
            currentNode = currentNode.nextNode;
            return element;
        }
    }

    /** Linked list Node*/
    private static class Node<T> {

        T element;
        Node<T> nextNode;
        Node<T> prevNode;

        // Constructor to create a new node
        Node(Node<T> prevNode, T element, Node<T> nextNode) {
            this.prevNode = prevNode;
            this.element = element;
            this.nextNode = nextNode;
        }
    }
}
