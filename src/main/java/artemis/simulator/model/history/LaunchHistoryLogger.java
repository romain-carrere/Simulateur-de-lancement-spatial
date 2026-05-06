package artemis.simulator.model.history;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class LaunchHistoryLogger {

    private static final String FILE_PATH = "launch_history.json";

    public static void saveLaunch(LaunchResult result) {
        Path path = Paths.get(FILE_PATH);
        List<String> lines = new ArrayList<>();

        try {
            if (Files.exists(path)) {
                lines = Files.readAllLines(path);
                if (!lines.isEmpty() && lines.get(lines.size() - 1).trim().equals("]")) {
                    lines.remove(lines.size() - 1);
                    if (lines.size() > 1) {
                        String lastLine = lines.remove(lines.size() - 1);
                        lines.add(lastLine + ",");
                    }
                }
            } else {
                lines.add("[");
            }

            lines.add("  {");
            lines.add("    \"date\": \"" + result.getDate() + "\",");
            lines.add("    \"missionName\": \"" + result.getMissionName() + "\",");
            lines.add("    \"launcherName\": \"" + result.getLauncherName() + "\",");
            lines.add("    \"capsuleName\": \"" + result.getCapsuleName() + "\",");
            lines.add("    \"boosterCount\": " + result.getBoosterCount() + ",");
            lines.add("    \"success\": " + result.isSuccess() + ",");
            lines.add("    \"failureReason\": \"" + result.getFailureReason() + "\",");
            lines.add("    \"totalCost\": " + result.getTotalCost());
            lines.add("  }");
            lines.add("]");

            Files.write(path, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        } catch (IOException e) {
            System.err.println("Error writing history: " + e.getMessage());
        }
    }

    public static List<String> loadFormattedHistory() {
        List<String> history = new ArrayList<>();
        Path path = Paths.get(FILE_PATH);
        
        if (!Files.exists(path)) {
            return history;
        }

        try {
            String content = new String(Files.readAllBytes(path));
            String[] objects = content.split("\\{");
            
            for (int i = 1; i < objects.length; i++) {
                String obj = objects[i];
                String date = extractValue(obj, "\"date\"");
                String mission = extractValue(obj, "\"missionName\"");
                String launcher = extractValue(obj, "\"launcherName\"");
                String successStr = extractValue(obj, "\"success\"");
                String cost = extractValue(obj, "\"totalCost\"");
                String reason = extractValue(obj, "\"failureReason\"");
                
                boolean success = "true".equals(successStr);
                String status = success ? "SUCCESS" : "FAILURE (" + reason + ")";
                
                history.add(date + " | " + launcher + " | " + mission + " | " + status + " | Cost: " + cost + " M€");
            }
        } catch (IOException e) {
            System.err.println("Error reading history: " + e.getMessage());
        }
        return history;
    }

    private static String extractValue(String jsonPart, String key) {
        int keyIndex = jsonPart.indexOf(key);
        if (keyIndex == -1) return "N/A";
        
        int colonIndex = jsonPart.indexOf(":", keyIndex);
        int commaIndex = jsonPart.indexOf(",", colonIndex);
        int endObjIndex = jsonPart.indexOf("}", colonIndex);
        
        int end = (commaIndex != -1 && commaIndex < endObjIndex) ? commaIndex : endObjIndex;
        if (end == -1) end = jsonPart.length();
        
        String value = jsonPart.substring(colonIndex + 1, end).trim();
        if (value.startsWith("\"") && value.endsWith("\"")) {
            value = value.substring(1, value.length() - 1);
        }
        return value;
    }
}