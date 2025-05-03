package pack;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.nio.file.*;
public class IPL {
    public static void main(String[] args) {
        String jsonFilePath = "ipl_matches.json";
        String csvFilePath = "ipl_matches.csv";
        processJSONData(jsonFilePath);
        processCSVData(csvFilePath);
    }
    public static void processJSONData(String jsonFilePath) {
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(jsonFilePath));
            @SuppressWarnings("unused")
			ObjectMapper objectMapper = new ObjectMapper();
            JSONArray jsonArray = new JSONArray(new String(jsonData));
            JSONArray sanitizedData = new JSONArray();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject match = jsonArray.getJSONObject(i);
                String team1 = match.getString("team1").replaceAll("([a-zA-Z])", "$1*");
                String team2 = match.getString("team2").replaceAll("([a-zA-Z])", "$1*");
                String playerOfMatch = "REDACTED";
                JSONObject sanitizedMatch = new JSONObject();
                sanitizedMatch.put("match_id", match.getInt("match_id"));
                sanitizedMatch.put("team1", team1);
                sanitizedMatch.put("team2", team2);
                sanitizedMatch.put("score", match.getJSONObject("score"));
                sanitizedMatch.put("winner", match.getString("winner"));
                sanitizedMatch.put("player_of_match", playerOfMatch);
                sanitizedData.put(sanitizedMatch);
            }
            try (FileWriter file = new FileWriter("sanitized_ipl_matches.json")) {
                file.write(sanitizedData.toString(4));  
            }
            System.out.println("Censorship applied and saved to sanitized_ipl_matches.json");
       } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void processCSVData(String csvFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean isHeader = true; 
            StringBuilder sanitizedCSVData = new StringBuilder();
            while ((line = reader.readLine()) != null) {
               if (line.trim().isEmpty()) {
                    continue;
                }

                if (isHeader) {
                    sanitizedCSVData.append(line).append("\n");
                    isHeader = false;
                    continue;
                }
                String[] fields = line.split(",");
                if (fields.length < 7) {
                    System.out.println("Skipping invalid row: " + line);
                    continue;
                }
                String matchId = fields[0].trim();
                String team1 = fields[1].trim();
                String team2 = fields[2].trim();
                String scoreTeam1 = fields[3].trim();
                String scoreTeam2 = fields[4].trim();
                String winner = fields[5].trim();
                String playerOfMatch = fields[6].trim();
                team1 = team1.replaceAll("([a-zA-Z])", "$1*"); 
                team2 = team2.replaceAll("([a-zA-Z])", "$1*");  
                playerOfMatch = "REDACTED"; 
                sanitizedCSVData.append(String.join(",", matchId, team1, team2, scoreTeam1, scoreTeam2, winner, playerOfMatch)).append("\n");
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("sanitized_ipl_matches.csv"))) {
                writer.write(sanitizedCSVData.toString());
            }
            System.out.println("Censorship applied and saved to sanitized_ipl_matches.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}