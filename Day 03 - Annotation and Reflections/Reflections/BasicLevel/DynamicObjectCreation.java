package BasicLevel;

import java.lang.reflect.*;

class Student {
    public Student() {
        System.out.println("Student object created!");
    }
}

public class DynamicObjectCreation {
    public static void main(String[] args) throws Exception {
        Class<?> cls = Class.forName("Student");
        Constructor<?> constructor = cls.getConstructor();
        Object obj = constructor.newInstance();
    }
}
