package AdvancedLevel;

import java.lang.reflect.Method;

class TaskProcessor {
 public void quickTask() {
     System.out.println("Quick task running...");
 }

 public void slowTask() throws InterruptedException {
     Thread.sleep(500);
     System.out.println("Slow task completed.");
 }
}

public class MethodExecutionTimer {

 public static void main(String[] args) {
     try {
         Class<?> clazz = TaskProcessor.class;
         Object instance = clazz.getDeclaredConstructor().newInstance();

         for (Method method : clazz.getDeclaredMethods()) {
             if (method.getParameterCount() == 0) {
                 System.out.println("Invoking: " + method.getName());

                 long startTime = System.nanoTime();

                 method.invoke(instance);

                 long endTime = System.nanoTime();
                 long duration = endTime - startTime;

                 System.out.println("Execution Time (nanoseconds): " + duration);
                 System.out.println("-----------------------------");
             }
         }

     } catch (Exception e) {
         e.printStackTrace();
     }
 }
}

