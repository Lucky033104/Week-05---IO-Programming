import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface BugReports {
    BugReport[] value();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(BugReports.class)
@interface BugReport {
    String description();
}

class BugTracker {

    @BugReport(description = "NullPointerException on line 23")
    @BugReport(description = "Incorrect calculation when input is negative")
    public void processTransaction() {
        System.out.println("Processing transaction...");
    }

    public void cleanUp() {
        System.out.println("Cleanup completed.");
    }
}

public class BugReportDemo {
    public static void main(String[] args) throws Exception {
        BugTracker tracker = new BugTracker();
        Class<?> cls = tracker.getClass();

        for (Method method : cls.getDeclaredMethods()) {
            if (method.isAnnotationPresent(BugReport.class) || method.isAnnotationPresent(BugReports.class)) {
                BugReport[] reports = method.getAnnotationsByType(BugReport.class);
                System.out.println("Method: " + method.getName());
                for (BugReport report : reports) {
                    System.out.println("- Bug: " + report.description());
                }
                System.out.println();
            }
        }
    }
}
