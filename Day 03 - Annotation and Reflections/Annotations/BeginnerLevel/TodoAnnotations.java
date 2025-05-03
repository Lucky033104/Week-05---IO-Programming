package BeginnerLevel;
import java.lang.annotation.*;
import java.lang.reflect.*;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Todo {
   String task();
   String assignedTo();
   String priority() default "MEDIUM";
}
class Project {
   @Todo(task = "Implement login validation", assignedTo = "Lucky", priority = "HIGH")
   public void login() {
       System.out.println("Login logic...");
   }
   @Todo(task = "Add logout feature", assignedTo = "")
   public void logout() {
       System.out.println("Logout logic...");
   }
   public void dashboard() {
       System.out.println("Dashboard loaded.");
   }
}
public class TodoAnnotations {
   public static void main(String[] args) throws Exception {
       Project project = new Project();
       Class<?> cls = project.getClass();
       for (Method method : cls.getDeclaredMethods()) {
           if (method.isAnnotationPresent(Todo.class)) {
               Todo todo = method.getAnnotation(Todo.class);
               System.out.println("Pending Method: " + method.getName());
               System.out.println("Task: " + todo.task());
               System.out.println("Assigned To: " + todo.assignedTo());
               System.out.println("Priority: " + todo.priority());
               System.out.println();
           }
       }
   }
}
