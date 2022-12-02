package genericclasses;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testerclass.Person;

public class GenericQueueTest {

    private GenericQueue<Person> arrayGenericQueue, arrayGenericQueueEmpty, linkedGenericQueue, linkedGenericQueueEmpty;
    private Person person;

    @BeforeEach
    void setUp() {
        arrayGenericQueue = new GenericQueue<>(createGenericArrayList());
        arrayGenericQueueEmpty = new GenericQueue<>(new GenericArrayList<>());

        linkedGenericQueue = new GenericQueue<>(createGenericLinkedList());
        linkedGenericQueueEmpty = new GenericQueue<>(new GenericLinkedList<>());

        person = new Person(30, "Doe", "John");
    }

    @Test
    void enqueue_InsertElement_InsertSpecifiedElementArrayBased() {
        arrayGenericQueueEmpty.enqueue(person);
        Assertions.assertEquals(person, arrayGenericQueueEmpty.first());
    }

    @Test
    void enqueue_InsertElement_InsertSpecifiedElementLinkedBased() {
        linkedGenericQueueEmpty.enqueue(person);
        Assertions.assertEquals(person, linkedGenericQueueEmpty.first());
    }

    @Test
    void dequeue_DequeueElement_DequeueFirstElementArrayBased() {
        Person dequeuedArrayBased = arrayGenericQueue.dequeue();
        Assertions.assertNotEquals(30, arrayGenericQueue.first().getAge());
        Assertions.assertEquals(30, dequeuedArrayBased.getAge());
    }

    @Test
    void dequeue_DequeueElement_DequeueFirstElementLinkedBased() {
        Person dequeuedLinkedBased = linkedGenericQueue.dequeue();
        Assertions.assertNotEquals(30, linkedGenericQueue.first().getAge());
        Assertions.assertEquals(30, dequeuedLinkedBased.getAge());
    }

    @Test
    void first_ReturnElement_ReturnFirstElementArrayBased() {
        Person firstElementArrayBased = arrayGenericQueue.dequeue();
        Assertions.assertEquals(30, firstElementArrayBased.getAge());
        Assertions.assertEquals("Surname0", firstElementArrayBased.getSurname());
        Assertions.assertEquals("Name0", firstElementArrayBased.getFirstname());
    }

    @Test
    void first_ReturnElement_ReturnFirstElementLinkedBased() {
        Person firstElementLinkedBased = linkedGenericQueue.dequeue();
        Assertions.assertEquals(30, firstElementLinkedBased.getAge());
        Assertions.assertEquals("Surname0", firstElementLinkedBased.getSurname());
        Assertions.assertEquals("Name0", firstElementLinkedBased.getFirstname());
    }

    @Test
    void empty_CheckEmpty_CheckIfQueueEmptyArrayBased() {
        Assertions.assertTrue(arrayGenericQueueEmpty.empty());
    }

    @Test
    void empty_CheckEmpty_CheckIfQueueEmptyLinkedBased() {
        Assertions.assertTrue(linkedGenericQueueEmpty.empty());
    }

    private GenericArrayList<Person> createGenericArrayList(){
        GenericArrayList<Person> genericArrayList = new GenericArrayList<>();
        for (int i = 0; i < 3; i++){
            genericArrayList.add(new Person(30 + i, "Surname" + i, "Name" + i));
        }
        return genericArrayList;
    }

    private GenericLinkedList<Person> createGenericLinkedList(){
        GenericLinkedList<Person> genericArrayList = new GenericLinkedList<>();
        for (int i = 0; i < 3; i++){
            genericArrayList.add(new Person(30 + i, "Surname" + i, "Name" + i));
        }
        return genericArrayList;
    }
}