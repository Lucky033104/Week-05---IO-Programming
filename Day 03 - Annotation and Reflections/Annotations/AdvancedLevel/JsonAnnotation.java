package AdvancedLevel;

import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface JsonField {
    String name();
}

class User {
    @JsonField(name = "user_name")
    private String username;

    @JsonField(name = "email_address")
    private String email;

    private int age;

    public User(String username, String email, int age) {
        this.username = username;
        this.email = email;
        this.age = age;
    }
}

class JsonSerializer {

    public static String serialize(Object obj) {
        Class<?> cls = obj.getClass();
        Map<String, String> jsonMap = new LinkedHashMap<>();

        for (Field field : cls.getDeclaredFields()) {
            if (field.isAnnotationPresent(JsonField.class)) {
                JsonField annotation = field.getAnnotation(JsonField.class);
                field.setAccessible(true);
                try {
                    Object value = field.get(obj);
                    jsonMap.put(annotation.name(), value.toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        StringBuilder json = new StringBuilder("{");
        for (Map.Entry<String, String> entry : jsonMap.entrySet()) {
            json.append("\"").append(entry.getKey()).append("\": ")
                .append("\"").append(entry.getValue()).append("\", ");
        }
        if (!jsonMap.isEmpty()) {
            json.setLength(json.length() - 2); 
        }
        json.append("}");
        return json.toString();
    }
}

public class JsonAnnotation {
    public static void main(String[] args) {
        User user = new User("chinni", "chinni@example.com", 22);
        String json = JsonSerializer.serialize(user);
        System.out.println("Serialized JSON:");
        System.out.println(json);
    }
}
