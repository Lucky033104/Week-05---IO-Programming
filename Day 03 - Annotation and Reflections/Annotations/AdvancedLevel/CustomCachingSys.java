package AdvancedLevel;

import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface CacheResult {}

interface MathOperations {
    int expensiveComputation(int input);
}

class MathOperationsImpl implements MathOperations {

    @CacheResult
    public int expensiveComputation(int input) {
        System.out.println("Computing for input: " + input);
        try {
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return input * input;
    }
}

class CacheHandler implements InvocationHandler {
    private final Object target;
    private final Map<String, Object> cache = new HashMap<>();

    public CacheHandler(Object target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(CacheResult.class)) {
            String key = method.getName() + Arrays.toString(args);
            if (cache.containsKey(key)) {
                System.out.println("Returning cached result for: " + Arrays.toString(args));
                return cache.get(key);
            } else {
                Object result = method.invoke(target, args);
                cache.put(key, result);
                return result;
            }
        } else {
            return method.invoke(target, args);
        }
    }
}

public class CustomCachingSys {
    public static void main(String[] args) {
        MathOperations original = new MathOperationsImpl();

        MathOperations proxy = (MathOperations) Proxy.newProxyInstance(
                MathOperations.class.getClassLoader(),
                new Class[]{MathOperations.class},
                new CacheHandler(original)
        );

        System.out.println("Result: " + proxy.expensiveComputation(5)); 
        System.out.println("Result: " + proxy.expensiveComputation(5)); 
        System.out.println("Result: " + proxy.expensiveComputation(10)); 
        System.out.println("Result: " + proxy.expensiveComputation(10)); 
    }
}
