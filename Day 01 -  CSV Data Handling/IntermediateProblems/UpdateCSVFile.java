package IntermediateProblems;

import java.io.*;

public class UpdateCSVFile {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("employees.csv"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("updated_employees.csv"));
        String line = br.readLine(); 
        bw.write(line); 
        bw.newLine();
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            if (data[2].equalsIgnoreCase("IT")) {
                double salary = Double.parseDouble(data[3]);
                salary *= 1.10; 
                data[3] = String.format("%.2f", salary);
            }
            bw.write(String.join(",", data));
            bw.newLine();
        }

        br.close();
        bw.close();
        System.out.println("Updated records saved to 'updated_employees.csv'");
    }
}
