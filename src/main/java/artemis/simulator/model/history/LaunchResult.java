package artemis.simulator.model.history;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LaunchResult {

    private final String date;
    private final String missionName;
    private final String launcherName;
    private final String capsuleName;
    private final int boosterCount;
    private final boolean success;
    private final String failureReason;
    private final double totalCost;

    public LaunchResult(String missionName, String launcherName, String capsuleName, int boosterCount, boolean success, String failureReason, double totalCost) {
        this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.missionName = missionName;
        this.launcherName = launcherName;
        this.capsuleName = capsuleName;
        this.boosterCount = boosterCount;
        this.success = success;
        this.failureReason = failureReason != null ? failureReason : "N/A";
        this.totalCost = totalCost;
    }

    public String getDate() {
        return date;
    }

    public String getMissionName() {
        return missionName;
    }

    public String getLauncherName() {
        return launcherName;
    }

    public String getCapsuleName() {
        return capsuleName;
    }

    public int getBoosterCount() {
        return boosterCount;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public double getTotalCost() {
        return totalCost;
    }
}