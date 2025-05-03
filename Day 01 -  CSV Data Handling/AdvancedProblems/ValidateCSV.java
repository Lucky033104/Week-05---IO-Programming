package AdvancedProblems;
import java.io.*;
import java.util.regex.*;

public class ValidateCSV{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("/Users/apple/Library/Mobile Documents/com~apple~TextEdit/Documents/contacts.csv"));
        String header = br.readLine(); 
        System.out.println(header);

        Pattern emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
        Pattern phonePattern = Pattern.compile("^\\d{10}$");

        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");

            if (data.length != 4) {
                System.out.println("Invalid row format: " + line);
                continue;
            }

            String email = data[2];
            String phone = data[3];

            boolean validEmail = emailPattern.matcher(email).matches();
            boolean validPhone = phonePattern.matcher(phone).matches();

            if (!validEmail || !validPhone) {
                System.out.println("Invalid row: " + line);
                if (!validEmail) System.out.println("  → Invalid Email: " + email);
                if (!validPhone) System.out.println("  → Invalid Phone: " + phone);
            }
        }
        br.close();
    }
}

