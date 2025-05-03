package AdvancedProblems;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.Key;
import java.util.Base64;

public class EncryptDecryptCSV {

    private static final String ALGO = "AES";
    private static final byte[] KEY = "MySuperSecretKey".getBytes(); 
    public static String encrypt(String data) throws Exception {
        Key key = new SecretKeySpec(KEY, ALGO);
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encVal);
    }
    public static String decrypt(String encryptedData) throws Exception {
        Key key = new SecretKeySpec(KEY, ALGO);
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedValue = Base64.getDecoder().decode(encryptedData);
        byte[] decValue = c.doFinal(decodedValue);
        return new String(decValue);
    }
    public static void writeEncryptedCSV(String filePath) {
        String[][] employees = {
            {"1", "Raja", "Raja@gmail.com", "75000"},
            {"2", "Bhaskar", "bhaskar@gmail.com", "85000"},
            {"3", "Charan", "charan@gmail.com", "95000"}
        };
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("ID,Name,Email,Salary");
            bw.newLine();
            for (String[] emp : employees) {
                String encryptedEmail = encrypt(emp[2]);
                String encryptedSalary = encrypt(emp[3]);
                bw.write(emp[0] + "," + emp[1] + "," + encryptedEmail + "," + encryptedSalary);
                bw.newLine();
            }
            System.out.println("Encrypted data written to " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void readDecryptedCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // skip header
            System.out.println("ID | Name | Email | Salary");
            System.out.println("------------------------------");
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String decryptedEmail = decrypt(data[2]);
                String decryptedSalary = decrypt(data[3]);
                System.out.printf("%s | %s | %s | %s\n", data[0], data[1], decryptedEmail, decryptedSalary);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        String filePath = "encrypted_employees.csv";

        writeEncryptedCSV(filePath);
        System.out.println("\nDecrypted CSV data:");
        readDecryptedCSV(filePath);
    }
}

