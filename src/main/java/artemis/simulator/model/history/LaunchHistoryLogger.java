package artemis.simulator.model.history;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LaunchHistoryLogger {
    private static final String HISTORY_FILE = "launch_history.json";
    private final List<LaunchResult> history;

    public LaunchHistoryLogger() {
        this.history = new ArrayList<>();
        loadHistory();
    }

    public void recordLaunch(LaunchResult result) {
        history.add(result);
        saveHistory();
    }

    public void saveHistory() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(HISTORY_FILE))) {
            writer.println("[");
            for (int i = 0; i < history.size(); i++) {
                writer.print("  " + history.get(i).toJsonString());
                if (i < history.size() - 1) {
                    writer.println(",");
                } else {
                    writer.println();
                }
            }
            writer.println("]");
        } catch (IOException e) {
            System.err.println("Error saving launch history: " + e.getMessage());
        }
    }

    public void loadHistory() {
        File file = new File(HISTORY_FILE);
        if (!file.exists()) {
            return;
        }

        try {
            String content = new String(Files.readAllBytes(Paths.get(HISTORY_FILE)));
            // Simple JSON parsing - in production, use a proper JSON library
            // This is a basic implementation
        } catch (IOException e) {
            System.err.println("Error loading launch history: " + e.getMessage());
        }
    }

    public List<LaunchResult> getHistory() {
        return new ArrayList<>(history);
    }

    public void clearHistory() {
        history.clear();
        new File(HISTORY_FILE).delete();
    }
}
