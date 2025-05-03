package AdvancedLevel;

import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface RoleAllowed {
    String value();
}

class Session {
    private String currentRole;

    public Session(String role) {
        this.currentRole = role;
    }

    public String getRole() {
        return currentRole;
    }
}

class AdminService {

    @RoleAllowed("ADMIN")
    public void deleteUserAccount() {
        System.out.println("User account deleted successfully.");
    }

    @RoleAllowed("USER")
    public void viewProfile() {
        System.out.println("Viewing user profile...");
    }

    public void generalHelp() {
        System.out.println("This is general help. Accessible to everyone.");
    }
}

public class RoleAccessControl {

    public static void invokeIfAllowed(Object obj, Method method, Session session) {
        RoleAllowed roleAllowed = method.getAnnotation(RoleAllowed.class);
        if (roleAllowed != null) {
            if (!roleAllowed.value().equalsIgnoreCase(session.getRole())) {
                System.out.println("Access Denied! Role " + session.getRole() +
                        " is not allowed to execute " + method.getName());
                return;
            }
        }
        try {
            method.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Session adminSession = new Session("ADMIN");
        Session userSession = new Session("USER");

        AdminService service = new AdminService();

        System.out.println("---- Admin Access ----");
        for (Method method : AdminService.class.getDeclaredMethods()) {
            invokeIfAllowed(service, method, adminSession);
        }

        System.out.println("\n---- User Access ----");
        for (Method method : AdminService.class.getDeclaredMethods()) {
            invokeIfAllowed(service, method, userSession);
        }
    }
}
