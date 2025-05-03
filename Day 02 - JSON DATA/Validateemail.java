package pack;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.regex.Pattern;
public class Validateemail{
    public static void main(String[] args) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(new File("data.json")); 
            JsonNode emailNode = rootNode.get("email");
            if (emailNode != null) {
                String email = emailNode.asText();
                String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
                boolean isValid = Pattern.matches(emailRegex, email);
                System.out.println("Email: " + email);
                System.out.println("Is valid: " + isValid);
            } else {
                System.out.println("Email field not found in JSON.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}