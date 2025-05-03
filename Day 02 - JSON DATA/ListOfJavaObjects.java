package pack;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.*;
class Student {
    private String name;
    private int age;
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("name", name);
        obj.put("age", age);
        return obj;
    }
}
public class ListOfJavaObjects {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Sasanka", 20));
        students.add(new Student("Bharath", 22));
        students.add(new Student("Abhinaya", 21));
        JSONArray jsonArray = new JSONArray();
        for (Student s : students) {
            jsonArray.put(s.toJSON());
        }
        System.out.println(jsonArray.toString(4)); 
    }
}
