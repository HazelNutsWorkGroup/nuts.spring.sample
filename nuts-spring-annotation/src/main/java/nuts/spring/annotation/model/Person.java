package nuts.spring.annotation.model;

/**
 * @author ach
 */
public class Person {


    public Person(){
        System.out.println("Person initialized...");
    }

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
