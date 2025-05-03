package pack;
import org.json.JSONObject;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONTokener;
public class ReadJSONFile2 {
    public static void main(String[] args) {
        try {
            File file = new File("data.json"); 
            FileReader reader = new FileReader(file);
            JSONTokener tokener = new JSONTokener(reader);
            JSONObject jsonObject = new JSONObject(tokener);
            jsonObject.keySet().forEach(key -> System.out.println(key + ": " + jsonObject.get(key)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
