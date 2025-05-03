package IntermediateLevel;
import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface LogExecutionTime {}

class PerformanceTester {

    @LogExecutionTime
    public void quickTask() {
        for (int i = 0; i < 100000; i++) {
            int x = i * i;
        }
    }

    @LogExecutionTime
    public void heavyTask() {
        for (int i = 0; i < 10000000; i++) {
            double x = Math.sqrt(i);
        }
    }

    public void normalTask() {
        System.out.println("This task is not annotated.");
    }
}

public class ExecutionTimeLogger {
    public static void main(String[] args) throws Exception {
        PerformanceTester tester = new PerformanceTester();
        Class<?> cls = tester.getClass();

        for (Method method : cls.getDeclaredMethods()) {
            if (method.isAnnotationPresent(LogExecutionTime.class)) {
                long start = System.nanoTime();
                method.invoke(tester);
                long end = System.nanoTime();
                long duration = (end - start) / 1_000_000; 
                System.out.println("Method: " + method.getName() + " executed in " + duration + " ms\n");
            }
        }
    }
}

