package BeginnerLevel;
import java.lang.annotation.*;
import java.lang.reflect.*;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface ImportantMethod {
   String level() default "HIGH";
}
class Service {
   @ImportantMethod(level = "CRITICAL")
   public void initializeSystem() {
       System.out.println("System initialization logic.");
   }
   @ImportantMethod
   public void processRequest() {
       System.out.println("Processing user request.");
   }
   public void helperMethod() {
       System.out.println("Helper method with no annotation.");
   }
}
public class ImportantMethods {
   public static void main(String[] args) throws Exception {
       Service service = new Service();
       Class<?> cls = service.getClass();
       for (Method method : cls.getDeclaredMethods()) {
           if (method.isAnnotationPresent(ImportantMethod.class)) {
               ImportantMethod annotation = method.getAnnotation(ImportantMethod.class);
               System.out.println("Important Method: " + method.getName());
               System.out.println("Level: " + annotation.level());
               method.invoke(service);
               System.out.println();
           }
       }
   }
}
