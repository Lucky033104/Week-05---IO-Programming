package IntermediateProblems;

import java.io.*;
import java.util.Scanner;

public class SearchEmployee {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("employees.csv"));
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter employee name to search: ");
        String target = sc.nextLine();
        String line;
        br.readLine(); 
        boolean found = false;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            if (data[1].equalsIgnoreCase(target)) {
                System.out.println("Department: " + data[2]);
                System.out.println("Salary: " + data[3]);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Employee not found.");
        }
        br.close();
        sc.close();
    }
}

