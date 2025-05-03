package AdvancedProblems;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.*;
import java.util.*;

public class JSONtoCSV{
    public static class Student {
        private int id;
        private String name;
        private int age;

        public Student() {}
        public Student(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }
        public int getId() { return id; }
        public String getName() { return name; }
        public int getAge() { return age; }
        public void setId(int id) { this.id = id; }
        public void setName(String name) { this.name = name; }
        public void setAge(int age) { this.age = age; }
        @Override
        public String toString() {
            return id + "," + name + "," + age;
        }
    }
    public static void jsonToCsv(String jsonFile, String csvFile) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<Student> students = mapper.readValue(new File(jsonFile), new TypeReference<List<Student>>() {});

        CSVWriter writer = new CSVWriter(new FileWriter(csvFile));
        writer.writeNext(new String[]{"ID", "Name", "Age"});
        for (Student s : students) {
            writer.writeNext(new String[]{
                String.valueOf(s.getId()),
                s.getName(),
                String.valueOf(s.getAge())
            });
        }
        writer.close();
        System.out.println("JSON converted to CSV: " + csvFile);
    }
    public static void csvToJson(String csvFile, String jsonFile) throws Exception {
        CSVReader reader = new CSVReader(new FileReader(csvFile));
        List<String[]> rows = reader.readAll();
        List<Student> students = new ArrayList<>();
        for (int i = 1; i < rows.size(); i++) {
            String[] row = rows.get(i);
            Student s = new Student(
                Integer.parseInt(row[0]),
                row[1],
                Integer.parseInt(row[2])
            );
            students.add(s);
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(jsonFile), students);
        System.out.println("CSV converted to JSON: " + jsonFile);
    }
    public static void main(String[] args) throws Exception {
        String jsonInput = "students.json";
        String csvOutput = "students.csv";
        String jsonOutput = "students_output.json";
        jsonToCsv(jsonInput, csvOutput);
        csvToJson(csvOutput, jsonOutput);
    }
}

