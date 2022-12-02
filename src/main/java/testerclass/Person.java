package testerclass;

public class Person implements Comparable<Person>{

    private final String firstname;
    private final String surname;
    private final int age;

    public Person(int age, String surname, String firstname) {
        this.age = age;
        this.surname = surname;
        this.firstname = firstname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    /**
     * The natural ordering will be determined by age in this example
     * @param o Object of type Person
     * @return
     */
    @Override
    public int compareTo(Person o) {
        return Integer.compare(age, o.age);
    }

    @Override
    public String toString(){
        return "Person{" +
                "firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                '}';
    }
}
