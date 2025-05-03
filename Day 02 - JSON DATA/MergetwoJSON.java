package pack;
import org.json.JSONObject;
public class MergetwoJSON {
    public static void main(String[] args) {
        JSONObject json1 = new JSONObject();
        json1.put("name", "Alice");
        json1.put("age", 25);
        JSONObject json2 = new JSONObject();
        json2.put("email", "alice@example.com");
        json2.put("city", "New York");
        for (String key : json2.keySet()) {
            json1.put(key, json2.get(key));
        }
        System.out.println("Merged JSON:");
        System.out.println(json1.toString(4)); 
    }
}
