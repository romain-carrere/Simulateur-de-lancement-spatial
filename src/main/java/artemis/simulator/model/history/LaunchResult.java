package artemis.simulator.model.history;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LaunchResult {
    private final String launcherId;
    private final String capsuleId;
    private final String missionId;
    private final double fuelUsed;
    private final double totalCost;
    private final boolean success;
    private final LocalDateTime launchTime;
    private final String notes;

    public LaunchResult(String launcherId, String capsuleId, String missionId, 
                       double fuelUsed, double totalCost, boolean success, String notes) {
        this.launcherId = launcherId;
        this.capsuleId = capsuleId;
        this.missionId = missionId;
        this.fuelUsed = fuelUsed;
        this.totalCost = totalCost;
        this.success = success;
        this.launchTime = LocalDateTime.now();
        this.notes = notes;
    }

    public String toJsonString() {
        return String.format("{\"launcher\":\"%s\",\"capsule\":\"%s\",\"mission\":\"%s\"," +
                "\"fuelUsed\":%.2f,\"totalCost\":%.2f,\"success\":%b,\"timestamp\":\"%s\",\"notes\":\"%s\"}",
                launcherId, capsuleId, missionId, fuelUsed, totalCost, success, 
                launchTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME), notes);
    }

    // Getters
    public String getLauncherId() { return launcherId; }
    public String getCapsuleId() { return capsuleId; }
    public String getMissionId() { return missionId; }
    public double getFuelUsed() { return fuelUsed; }
    public double getTotalCost() { return totalCost; }
    public boolean isSuccess() { return success; }
    public LocalDateTime getLaunchTime() { return launchTime; }
    public String getNotes() { return notes; }
}
