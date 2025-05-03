package pack;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
public class JSONValidate{
    public static class Person {
        public String name;
        public int age;
        public String email;
    }

    @SuppressWarnings("hiding")
	public static void main(String[] args) {
        String jsonString = "{\"name\":\"Abhinaya\",\"age\":21,\"email\":\"sriabhinaya2003@gmail.com\"}";
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Person person = objectMapper.readValue(jsonString, Person.class);
            System.out.println("JSON is valid. Mapped to Person object.");
            System.out.println("Name: " + person.name);
            System.out.println("Age: " + person.age);
            System.out.println("Email: " + person.email);
        } catch (JsonMappingException e) {
            System.out.println("Invalid JSON structure: " + e.getMessage());
        } catch (JsonProcessingException e) {
            System.out.println("Error processing JSON: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }
}