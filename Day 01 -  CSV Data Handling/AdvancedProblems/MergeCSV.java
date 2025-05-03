package AdvancedProblems;

import java.io.*;
import java.util.*;

public class MergeCSV {
    public static void main(String[] args) throws IOException {
        
        String students1File = "students1.csv";
        String students2File = "students2.csv";
        String outputFile = "merged_students.csv";

        Map<Integer, String[]> students1Data = new HashMap<>();
        Map<Integer, String[]> students2Data = new HashMap<>();

        BufferedReader br1 = new BufferedReader(new FileReader(students1File));
        String line1;
        br1.readLine(); 
        while ((line1 = br1.readLine()) != null) {
            String[] data = line1.split(",");
            int id = Integer.parseInt(data[0]);
            students1Data.put(id, data); 
        }
        br1.close();

        BufferedReader br2 = new BufferedReader(new FileReader(students2File));
        String line2;
        br2.readLine(); 
        while ((line2 = br2.readLine()) != null) {
            String[] data = line2.split(",");
            int id = Integer.parseInt(data[0]);
            students2Data.put(id, data); 
        }
        br2.close();

        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        writer.write("ID,Name,Age,Marks,Grade\n"); 

        for (Integer id : students1Data.keySet()) {
            if (students2Data.containsKey(id)) {
                String[] student1 = students1Data.get(id);
                String[] student2 = students2Data.get(id);

                writer.write(id + "," + student1[1] + "," + student1[2] + "," + student2[1] + "," + student2[2] + "\n");
            }
        }

        writer.close();
        System.out.println("Merged CSV file created: " + outputFile);
    }
}

