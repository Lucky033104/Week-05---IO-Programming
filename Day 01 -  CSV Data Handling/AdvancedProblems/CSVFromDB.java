package AdvancedProblems;

import java.sql.*;
import java.io.*;

public class CSVFromDB {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/databaseforCSV";
        String user = "username";
        String pass = "password";
        String fileName = "employee_report.csv";
        String query = "SELECT emp_id, name, department, salary FROM employee";
        try (
            Connection conn = DriverManager.getConnection(url, user, pass);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))
        ) {
            writer.write("Employee ID,Name,Department,Salary");
            writer.newLine();
            while (rs.next()) {
                int id = rs.getInt("emp_id");
                String name = rs.getString("name");
                String dept = rs.getString("department");
                double salary = rs.getDouble("salary");
                writer.write(id + "," + name + "," + dept + "," + salary);
                writer.newLine();
            }
            System.out.println("CSV Report generated successfully: " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

