package AdvancedLevel;
import java.lang.reflect.Field;

class Person {
    private String name;
    private int age;
    private String email;

    public Person(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }
}

public class ObjectToJson {

    public static String toJson(Object obj) throws IllegalAccessException {

        Class<?> objClass = obj.getClass();

        StringBuilder jsonString = new StringBuilder("{");

        Field[] fields = objClass.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true); 
  
            String fieldName = field.getName();
            Object fieldValue = field.get(obj);

            jsonString.append("\"")
                       .append(fieldName)
                       .append("\": ");

            if (fieldValue instanceof String) {
                jsonString.append("\"").append(fieldValue).append("\"");
            } else {
                jsonString.append(fieldValue);
            }

            if (i < fields.length - 1) {
                jsonString.append(", ");
            }
        }

        jsonString.append("}");

        return jsonString.toString();
    }

    public static void main(String[] args) {
        try {
            Person person = new Person("Bhaskar", 21, "Bhaskar@gmail.com");

            String json = toJson(person);

            System.out.println(json);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
