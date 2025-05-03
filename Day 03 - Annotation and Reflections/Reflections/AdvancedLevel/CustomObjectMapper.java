package AdvancedLevel;

import java.lang.reflect.*;
import java.util.Map;

class User {
    private String name;
    private int age;
    private String email;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{name='" + name + "', age=" + age + ", email='" + email + "'}";
    }
}

public class CustomObjectMapper {

    public static <T> T toObject(Class<T> clazz, Map<String, Object> properties) throws Exception {
 
        T obj = clazz.getDeclaredConstructor().newInstance();

        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            String fieldName = entry.getKey();
            Object fieldValue = entry.getValue();

            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);

                if (field.getType() == fieldValue.getClass()) {
                    field.set(obj, fieldValue);
                }
            } catch (NoSuchFieldException e) {
                System.out.println("Field " + fieldName + " not found in class " + clazz.getName());
            }
        }

        return obj;
    }

    public static void main(String[] args) {
        try {
            Map<String, Object> properties = Map.of(
                "name", "Bhaskar Raja",
                "age", 30,
                "email", "bhaskar@example.com"
            );

            User user = toObject(User.class, properties);

            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

