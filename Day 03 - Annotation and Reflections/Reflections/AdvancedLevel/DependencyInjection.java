package AdvancedLevel;
import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Inject {
}

interface MessageService {
    void sendMessage(String message);
}
class EmailService implements MessageService {
    public void sendMessage(String message) {
        System.out.println("Email sent: " + message);
    }
}

class UserController {
    @Inject
    private MessageService messageService;

    public void process() {
        messageService.sendMessage("Welcome user!");
    }
}

class SimpleDIContainer {

    public static <T> T createInstance(Class<T> clazz) throws Exception {

        T instance = clazz.getDeclaredConstructor().newInstance();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Inject.class)) {
                Class<?> dependencyType = field.getType();
                Object dependency = createInstance(dependencyType); // Recursive instantiation
                field.setAccessible(true);
                field.set(instance, dependency);
            }
        }

        return instance;
    }
}

public class DependencyInjection {
    public static void main(String[] args) throws Exception {
        UserController controller = SimpleDIContainer.createInstance(UserController.class);
        controller.process(); 
    }
}

