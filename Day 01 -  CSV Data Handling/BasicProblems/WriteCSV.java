package BasicProblems;

import java.io.*;

public class WriteCSV {
    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("employees.csv"));
        writer.write("ID,Name,Department,Salary\n");
        writer.write("1,Bhaskar,HR,50000\n");
        writer.write("2,Raja,IT,65000\n");
        writer.write("3,Lucky,Finance,58000\n");
        writer.write("4,Chandrasekhar,Marketing,62000\n");
        writer.write("5,Vishnu,Operations,54000\n");
        writer.close();
        System.out.println("CSV file written successfully (Core Java).");
    }
}

