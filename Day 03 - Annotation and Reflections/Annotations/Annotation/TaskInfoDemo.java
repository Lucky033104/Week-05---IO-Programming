package Annotation;
import java.lang.annotation.*;
import java.lang.reflect.*;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface TaskInfo {
   String priority();
   String assignedTo();
}
class TaskManager {
   @TaskInfo(priority = "High", assignedTo = "Bhaskar")
   public void completePayment() {
       System.out.println("Payment task completed.");
   }
   @TaskInfo(priority = "Medium", assignedTo = "Sanjana")
   public void generateReport() {
       System.out.println("Report task completed.");
   }
}
public class TaskInfoDemo {
   public static void main(String[] args) throws Exception {
       TaskManager taskManager = new TaskManager();
       Class<?> cls = taskManager.getClass();
       for (Method method : cls.getDeclaredMethods()) {
           if (method.isAnnotationPresent(TaskInfo.class)) {
               TaskInfo info = method.getAnnotation(TaskInfo.class);
               System.out.println("Method: " + method.getName());
               System.out.println("Priority: " + info.priority());
               System.out.println("Assigned To: " + info.assignedTo());
               method.invoke(taskManager);
               System.out.println();
           }
       }
   }
}
