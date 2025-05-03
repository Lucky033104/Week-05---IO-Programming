package pack;
import org.json.CDL;
import org.json.JSONArray;
public class CSVtoJSON{
    public static void main(String[] args) {
        String csvData = """
            name,age,email
            Abhinaya,21,abhinaya@example.com
            Sasanka,20,sasanka@example.com
        """;
        JSONArray jsonArray = CDL.toJSONArray(csvData);
        System.out.println(jsonArray.toString(2));
    }
}