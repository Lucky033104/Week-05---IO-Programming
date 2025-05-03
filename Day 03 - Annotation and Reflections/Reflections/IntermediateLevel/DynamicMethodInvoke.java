package IntermediateLevel;

import java.lang.reflect.*;
import java.util.Scanner;

class MathOperations {
    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }
}

public class DynamicMethodInvoke {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        MathOperations obj = new MathOperations();

        System.out.print("Enter method name (add/subtract/multiply): ");
        String methodName = scanner.nextLine();

        System.out.print("Enter first number: ");
        int x = scanner.nextInt();

        System.out.print("Enter second number: ");
        int y = scanner.nextInt();

        Method method = MathOperations.class.getMethod(methodName, int.class, int.class);

        Object result = method.invoke(obj, x, y);

        System.out.println("Result: " + result);
    }
}
