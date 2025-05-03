package pack;
import org.json.JSONArray;
import org.json.JSONObject;
public class FilterJSONdata {
    public static void main(String[] args) {
        String jsonString = "[" +
                "{\"name\": \"Abhinav\", \"age\": 30}," +
                "{\"name\": \"Bharani\", \"age\": 24}," +
                "{\"name\": \"Bhaskar\", \"age\": 28}" +
                "]";
        JSONArray jsonArray = new JSONArray(jsonString);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject user = jsonArray.getJSONObject(i);
            int age = user.getInt("age");
            if (age > 25) {
                System.out.println(user.toString(2));
            }
        }
    }
}
