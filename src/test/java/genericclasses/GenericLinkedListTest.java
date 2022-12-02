package genericclasses;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import testerclass.Person;

import static org.junit.jupiter.api.Assertions.*;

public class GenericLinkedListTest {

    private GenericLinkedList<Person> personListEmpty, genericListPrePopulated;
    private Person person, testPerson;
    int length;

    @BeforeEach
    void setUp() {
        personListEmpty = new GenericLinkedList<>();
        genericListPrePopulated = new GenericLinkedList<>();
        person = new Person(30, "Doe", "John");
        testPerson = new Person(20, "Surname", "Name");
        populateLists();
        length = 5;
    }

    @Test
    void add_AddElement_SizeIncreaseByOne() {
        int initialSize = personListEmpty.size();
        personListEmpty.add(person);

        Assertions.assertEquals(++initialSize, personListEmpty.size());
    }

    @Test
    void add_AddElement_FirstElementByIndex() {
        int initialSize = personListEmpty.size();
        personListEmpty.add(0, person);

        Assertions.assertEquals(++initialSize, personListEmpty.size());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void add_AddElementToList_AtIndex(int index) {
        genericListPrePopulated.add(index, person);

        Assertions.assertEquals("Doe", genericListPrePopulated.get(index).getSurname());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 4})
    void add_AddElementToList_IllegalIndexBoundary(int index) {
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> genericListPrePopulated.add(index, person));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void set_SetElementAtIndexInList_ElementReplaced(int index) {
        genericListPrePopulated.set(index, person);
        Assertions.assertEquals(person, genericListPrePopulated.get(index));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 4})
    void set_SetElementAtIndexInList_IllegalIndexBoundary(int index) {
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> genericListPrePopulated.set(index, person));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void get_GetElementFromList_AssertValues(int index) {
        Assertions.assertEquals("Surname" + index, genericListPrePopulated.get(index).getSurname());
        Assertions.assertEquals("Name" + index, genericListPrePopulated.get(index).getFirstname());
        Assertions.assertEquals(30 + index, genericListPrePopulated.get(index).getAge());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 4})
    void get_GetElementFromList_IllegalIndexBoundary(int index) {
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> genericListPrePopulated.get(index));
    }

    @Test
    void size_GetListSize_IncrementingList() {
        int capacity = 0;
        Assertions.assertEquals(capacity, personListEmpty.size());
        for (int i = 0; i < 5; i++){
            personListEmpty.add(testPerson);
            capacity++;
        }
        Assertions.assertEquals(capacity, personListEmpty.size());
    }

    @Test
    void remove_RemoveElement_RemoveByIndex() {
        personListEmpty.add(person);
        Assertions.assertEquals(person, personListEmpty.remove(0));
    }

    @Test
    void remove_RemoveElement_RemoveByElement() {
        genericListPrePopulated.add(person);
        Assertions.assertTrue(genericListPrePopulated.remove(person));
        Assertions.assertFalse(genericListPrePopulated.contains(person));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 4})
    void remove_RemoveElement_IllegalIndexBoundary(int index) {
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> genericListPrePopulated.get(index));
    }

    @Test
    void isEmpty() {
        Assertions.assertTrue(personListEmpty.isEmpty());
    }

    @Test
    void contains() {
        genericListPrePopulated.add(person);
        Assertions.assertTrue(genericListPrePopulated.contains(person));
    }

    @Test
    void iterator(){
        Assertions.assertInstanceOf(GenericLinkedList.GenericLinkedListIterator.class, personListEmpty.iterator());
    }

    @Test
    void printList() {
        genericListPrePopulated.printList();
    }

    private void populateLists(){
        for (int i = 0; i < 3; i++){
            genericListPrePopulated.add(new Person(30 + i, "Surname" + i, "Name" + i));
        }
    }
}