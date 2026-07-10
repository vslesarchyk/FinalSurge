package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AddWorkout {
    @Builder.Default
    private String workoutDate = "7/4/2026";
    @Builder.Default
    private String timeOfDay = "05:45 AM";
    @Builder.Default
    private String activityType="Run";
    @Builder.Default
    private String name = "Test";
    @Builder.Default
    private String description = "Test";
    @Builder.Default
    private boolean showPlannedDistance = true;
    @Builder.Default
    private String plannedDistance = "10";
    @Builder.Default
    private String plannedDistanceType = "mi";
    @Builder.Default
    private String plannedDuration = "10:10:10";
    @Builder.Default
    private String distance = "10";
    @Builder.Default
    private String distanceType = "mi";
    @Builder.Default
    private String duration = "10:10:10";
    @Builder.Default
    private String paceType = "10:10";
    @Builder.Default
    private boolean markAsRace = true;
    @Builder.Default
    private String overallPlace;
    @Builder.Default
    private String ageGroupPlace = "10";
    @Builder.Default
    private String howIFelt = "Great";
    @Builder.Default
    private String perceivedEffort = "1 (Very Light)";
    @Builder.Default
    private String minHr = "10";
    @Builder.Default
    private String avgHr = "10";
    @Builder.Default
    private String maxHr = "10";
    @Builder.Default
    private String caloriesBurned = "100";
    @Builder.Default
    private String results = "good";
    @Builder.Default
    private boolean saveToLibrary = true;
}
