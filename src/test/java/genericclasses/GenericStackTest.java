package genericclasses;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testerclass.Person;

public class GenericStackTest {

    private GenericStack<Person> arrayGenericStack, arrayGenericStackEmpty, linkedGenericStack, linkedGenericStackEmpty;
    private Person person;

    @BeforeEach
    void setUp() {
        arrayGenericStack = new GenericStack<>(createGenericArrayList());
        arrayGenericStackEmpty = new GenericStack<>(new GenericArrayList<>());

        linkedGenericStack = new GenericStack<>(createGenericLinkedList());
        linkedGenericStackEmpty = new GenericStack<>(new GenericLinkedList<>());

        person = new Person(30, "Doe", "John");
    }

    @Test
    void push_InsertElement_InsertSpecifiedElementArrayBased() {
        arrayGenericStackEmpty.push(person);
        Assertions.assertEquals(person, arrayGenericStackEmpty.peek());
    }

    @Test
    void push_InsertElement_InsertSpecifiedElementLinkedBased() {
        linkedGenericStackEmpty.push(person);
        Assertions.assertEquals(person, linkedGenericStackEmpty.peek());
    }

    @Test
    void pop_RemoveElement_RemoveFirstElementArrayBased() {
        Person dequeuedArrayBased = arrayGenericStack.pop();
        Assertions.assertNotEquals(30, arrayGenericStack.peek().getAge());
        Assertions.assertEquals(30, dequeuedArrayBased.getAge());
    }

    @Test
    void pop_RemoveElement_RemoveFirstElementLinkedBased() {
        Person dequeuedLinkedBased = linkedGenericStack.pop();
        Assertions.assertNotEquals(30, linkedGenericStack.peek().getAge());
        Assertions.assertEquals(30, dequeuedLinkedBased.getAge());
    }

    @Test
    void peek_ReturnElement_ReturnFirstElementArrayBased() {
        Person firstElementArrayBased = arrayGenericStack.pop();
        Assertions.assertEquals(30, firstElementArrayBased.getAge());
        Assertions.assertEquals("Surname0", firstElementArrayBased.getSurname());
        Assertions.assertEquals("Name0", firstElementArrayBased.getFirstname());
    }

    @Test
    void peek_ReturnElement_ReturnFirstElementLinkedBased() {
        Person firstElementLinkedBased = linkedGenericStack.pop();
        Assertions.assertEquals(30, firstElementLinkedBased.getAge());
        Assertions.assertEquals("Surname0", firstElementLinkedBased.getSurname());
        Assertions.assertEquals("Name0", firstElementLinkedBased.getFirstname());
    }

    @Test
    void empty_CheckEmpty_CheckIfStackEmptyArrayBased() {
        Assertions.assertTrue(arrayGenericStackEmpty.empty());
    }

    @Test
    void empty_CheckEmpty_CheckIfStackEmptyLinkedBased() {
        Assertions.assertTrue(linkedGenericStackEmpty.empty());
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