package BasicLevel;
import java.lang.reflect.*;

class Person {
    private int age = 20;
}
public class AccessPrivateField {
    public static void main(String[] args) throws Exception {
        Person person = new Person();

        Field field = Person.class.getDeclaredField("age");
        field.setAccessible(true);

        System.out.println("Original Age: " + field.get(person));

        field.set(person, 35);

        System.out.println("Modified Age: " + field.get(person));
    }
}

