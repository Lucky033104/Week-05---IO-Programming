package pack;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;
public class ReadJSONFile {
    public static void main(String[] args) {
        try {
            String content = new String(Files.readAllBytes(Paths.get("user.json")));
            JSONObject json = new JSONObject(content);
            String name = json.getString("name");
            String email = json.getString("email");
            System.out.println("Name: " + name);
            System.out.println("Email: " + email);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
