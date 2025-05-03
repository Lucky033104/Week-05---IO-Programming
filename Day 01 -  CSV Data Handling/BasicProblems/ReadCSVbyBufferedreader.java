package BasicProblems;

import java.io.*;

public class ReadCSVbyBufferedreader {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("employees.csv"));
        int count = 0;
        br.readLine(); 
        while (br.readLine() != null) {
            count++;
        }
        br.close();
        System.out.println("Total number of records: " + count);
    }
}

