package AdvancedProblems;

import java.io.*;
import java.util.*;

public class DetectDuplicate {
    public static void main(String[] args) throws IOException {
        String filePath = "employees.csv"; 
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line = reader.readLine(); 

        Set<String> seenIds = new HashSet<>();
        List<String> duplicates = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");
            String id = fields[0].trim();

            if (!seenIds.add(id)) {
                duplicates.add(line);
            }
        }

        reader.close();

        System.out.println("Duplicate Records:");
        for (String dup : duplicates) {
            System.out.println(dup);
        }

        if (duplicates.isEmpty()) {
            System.out.println("No duplicates found.");
        }
    }
}

