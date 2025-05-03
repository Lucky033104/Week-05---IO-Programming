package pack;
import org.json.JSONObject;
import org.json.XML;
public class JSONtoXML{
    public static void main(String[] args) {
        String jsonString = """
            {
                "student": {
                    "name": "Abhinaya",
                    "age": 21,
                    "email": "abhinaya@example.com"
                }
            }
        """;
        JSONObject jsonObject = new JSONObject(jsonString);
        String xml = XML.toString(jsonObject);
        System.out.println("Converted XML:\n" + xml);
    }
}