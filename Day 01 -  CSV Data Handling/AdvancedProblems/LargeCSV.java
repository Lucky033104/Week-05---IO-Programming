package AdvancedProblems;

import java.io.*;
import java.util.*;

public class LargeCSV {
    public static void main(String[] args) throws IOException {
        String filePath = "large_students.csv"; 

        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        int count = 0;
        int batchSize = 100;

        String header = reader.readLine();

        List<String> batch = new ArrayList<>(batchSize);

        while ((line = reader.readLine()) != null) {
            batch.add(line);
            count++;

            if (batch.size() == batchSize) {
                processBatch(batch, count);
                batch.clear();
            }
        }

        if (!batch.isEmpty()) {
            processBatch(batch, count);
        }

        reader.close();
        System.out.println("Total records processed: " + count);
    }

    public static void processBatch(List<String> batch, int totalCount) {
        System.out.println("Processed " + batch.size() + " records. Total so far: " + totalCount);
    }
}

