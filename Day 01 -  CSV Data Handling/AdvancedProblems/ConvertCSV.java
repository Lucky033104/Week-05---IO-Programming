package AdvancedProblems;

import java.io.*;
import java.util.*;

class Student {
    private int id;
    private String name;
    private int age;
    private int marks;
    
    public Student(int id, String name, int age, int marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.marks = marks;
    }
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", marks=" + marks +
                '}';
    }
}
public class ConvertCSV {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("/Users/apple/Library/Mobile Documents/com~apple~TextEdit/Documents/Students.csv"));
        String header = br.readLine(); 
        List<Student> students = new ArrayList<>();

        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            int id = Integer.parseInt(data[0]);
            String name = data[1];
            int age = Integer.parseInt(data[2]);
            int marks = Integer.parseInt(data[3]);

            Student student = new Student(id, name, age, marks);
            students.add(student);
        }

        for (Student student : students) {
            System.out.println(student);
        }

        br.close();
    }
}

