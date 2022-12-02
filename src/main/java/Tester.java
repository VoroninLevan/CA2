import genericclasses.GenericLinkedList;
import testerclass.Person;

import java.util.ArrayList;
import java.util.LinkedList;

public class Tester {

    public static void main(String[] args){

        GenericLinkedList<Integer> linkedList = new GenericLinkedList<>();

        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);

        linkedList.set(0, 10);
        linkedList.set(2, 10);

        for(Integer i : linkedList){
            System.out.println(i);
        }

    }


}
