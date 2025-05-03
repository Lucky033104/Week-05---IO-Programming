package IntermediateLevel;
import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
}

class User {

    @MaxLength(10)
    private String username;

    public User(String username) {
        this.username = username;
        validateLength();
    }

    private void validateLength() {
        Class<?> cls = this.getClass();
        for (Field field : cls.getDeclaredFields()) {
            if (field.isAnnotationPresent(MaxLength.class)) {
                MaxLength max = field.getAnnotation(MaxLength.class);
                field.setAccessible(true);
                try {
                    Object value = field.get(this);
                    if (value instanceof String && ((String) value).length() > max.value()) {
                        throw new IllegalArgumentException("Field '" + field.getName()
                                + "' exceeds max length of " + max.value());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void display() {
        System.out.println("Username: " + username);
    }
}

public class MaxLengthValidator {
    public static void main(String[] args) {
        try {
            User user1 = new User("Chinni");
            user1.display();

            User user2 = new User("VeryLongUsername"); 
            user2.display();

        } catch (IllegalArgumentException e) {
            System.out.println("Validation Error: " + e.getMessage());
        }
    }
}

