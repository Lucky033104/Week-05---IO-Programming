package pack;
import org.json.JSONArray;
import org.json.JSONObject;
public class FilterJSON {
    public static void main(String[] args) {
        String jsonString = "[" +
                "{\"name\": \"Abhinaya\", \"age\": 30, \"email\": \"abhinaya@example.com\"}," +
                "{\"name\": \"Bharath\", \"age\": 24, \"email\": \"bharath@example.com\"}," +
                "{\"name\": \"Sara\", \"age\": 28, \"email\": \"sara@example.com\"}," +
                "{\"name\": \"Naina\", \"age\": 22, \"email\": \"naina@example.com\"}" +
                "]";
        JSONArray jsonArray = new JSONArray(jsonString);
        System.out.println("Filtered records with age > 25:");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            int age = obj.getInt("age");
            if (age > 25) {
                System.out.println(obj.toString(2)); 
            }
        }
    }
}

