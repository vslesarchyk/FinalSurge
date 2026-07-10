package tests;

import dto.AddWorkout;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CalendarTest extends BaseTest {
    private static final String ACTIVITY_TYPE_ERROR = "×\n" + "Please fix the following errors:\n" + "*Please select a valid Activity Type.";
    private static final String INFO_FROM_DASHBOARD = "You have no past workouts within the last 14 days.";

    @Test ( description = "Quickly add a workout using the button",
            groups = {"smoke", "regression"}
    )
    public void positiveAddQuickWorkOutForTodayByButton() {
        AddWorkout quickWorkout = AddWorkout.builder()
                .activityType("Run")
                .build();
        loginPage.openPage()
                .login(USER, PASSWORD);
        startPage.clickCalendar();
        calendarPage.isPageOpened()
                .addQuickWorkoutWithButton()
                .activityTypeQuickSelect(quickWorkout)
                .clickAddWorkout();
        Assert.assertTrue(calendarPage.workOutIsDisplayed());
    }

    @Test ( description = "Negative check for adding a workout",
            groups = {"smoke", "regression"}
    )
    public void negativeAddQuickWorkout() {
        AddWorkout.builder()
                .activityType("")
                .build();
        loginPage.openPage()
                .login(USER, PASSWORD);
        startPage.clickCalendar();
        calendarPage.isPageOpened()
                .addQuickWorkoutWithButton()
                .clickAddWorkout();
        Assert.assertEquals(calendarPage.getActivityTypeError(), ACTIVITY_TYPE_ERROR);
    }

    @Test(description = "Adding a workout via 'Full Add' button",
            groups = {"smoke", "regression"}
    )
    public void addFullFromCalendar() {
        AddWorkout fullWorkout = AddWorkout.builder()
                .name("Light run")
                .build();
        loginPage.openPage()
                .login(USER, PASSWORD);
        startPage.clickCalendar();
        calendarPage.isPageOpened()
                .clickFullWorkoutFromCalendar();
        addWorkoutPage.activityTypeSelect("run", "Long Run")
                .addWorkoutName(fullWorkout)
                .clickAddWorkout();
        Assert.assertEquals(addWorkoutPage.getWorkoutName(), fullWorkout.getName());
        startPage.clickCalendar();
    }

    @Test (description = "Editing a workout",
            groups = {"smoke", "regression"}
    )
    public void editWorkout() {
        AddWorkout quickWorkout = AddWorkout.builder()
                .activityType("Swim")
                .build();
        loginPage.openPage()
                .login(USER, PASSWORD);
        startPage.clickCalendar();
        calendarPage.isPageOpened()
                .addQuickWorkoutFromCalendar()
                .activityTypeQuickSelect(quickWorkout)
                .clickAddWorkout();
        Assert.assertTrue(calendarPage.workOutIsDisplayed());
        calendarPage.editTraining();
        workoutDetailsPage.isPageOpened()
                .clickUpdateWorkout();
        AddWorkout editWorkout = AddWorkout.builder()
                .timeOfDay("8:00 PM")
                .name("workout edit test")
                .description("workout edit test")
                .showPlannedDistance(true)
                .plannedDistance("1")
                .plannedDistanceType("km")
                .plannedDuration("00:45:00")
                .distance("1.200")
                .distanceType("km")
                .duration("00:45:00")
                .paceType("min/km")
                .perceivedEffort("4 (Moderate)")
                .howIFelt("Good")
                .caloriesBurned("400")
                .saveToLibrary(true)
                .build();
        workoutDetailsPage.editWorkout(editWorkout);
        workoutDetailsPage.clickSaveUpdateDWorkout();
        workoutDetailsPage.isPageOpened();
        startPage.clickCalendar();
        Assert.assertTrue(calendarPage.workOutIsDisplayed());
    }

    @Test (description = "Creating a future workout through the dashboard",
            groups = {"smoke", "regression"}
    )
    public void createFutureTrainingFromDashboardPage() {
        AddWorkout quickWorkout = AddWorkout.builder()
                .activityType("Bike")
                .build();
        loginPage.openPage()
                .login(USER, PASSWORD);
        dashboardPage.clickCalendar();
        calendarPage.isPageOpened()
                .addQuickWorkoutWithButton()
                .setWorkoutDate(1)
                .activityTypeQuickSelect(quickWorkout)
                .clickAddWorkout();
        dashboardPage.clickDashboardPage()
                .clickUpcomingWorkouts();
        Assert.assertTrue(dashboardPage.upcomingWorkoutsExists());
        dashboardPage.clickFutureWorkoutDetails();
        workoutDetailsPage.isPageOpened();
    }

    @Test (description = "Creating a past workout through the dashboard",
            groups = {"smoke", "regression"}
    )
    public void createPastTrainingFromDashboardPage() {
        AddWorkout quickWorkout = AddWorkout.builder()
                .activityType("Bike")
                .build();
        loginPage.openPage()
                .login(USER, PASSWORD);
        dashboardPage.clickCalendar();
        calendarPage.isPageOpened()
                .addQuickWorkoutWithButton()
                .setWorkoutDate(-1)
                .activityTypeQuickSelect(quickWorkout)
                .clickAddWorkout();
        dashboardPage.clickDashboardPage()
                .clickPastWorkouts();
        Assert.assertTrue(dashboardPage.pastWorkoutsExists());
        dashboardPage.clickPastWorkoutDetails();
        workoutDetailsPage.isPageOpened();
    }

    @Test (description = "Remove past workout through the dashboard",
            groups = {"smoke", "regression"}
    )
    public void removePastTrainingFromDashboardPage() {
        String workoutName = "Remove test";
        AddWorkout quickWorkout = AddWorkout.builder()
                .activityType("Walk")
                .name(workoutName)
                .build();
        loginPage.openPage()
                .login(USER, PASSWORD);
        dashboardPage.clickCalendar();
        calendarPage.isPageOpened()
        .addQuickWorkoutWithButton()
        .setWorkoutDate(-1)
        .activityTypeQuickSelect(quickWorkout)
        .clickAddWorkout();
        dashboardPage.clickDashboardPage()
        .clickPastWorkouts();
        Assert.assertTrue(dashboardPage.pastWorkoutsExists());
        dashboardPage.clickPastWorkoutDetails();
        workoutDetailsPage.isPageOpened()
        .clickUpdateWorkout();
        addWorkoutPage.deleteWorkout();
        calendarPage.isPageOpened();
        dashboardPage.clickDashboardPage();
        Assert.assertFalse(dashboardPage.isWorkoutPresent(workoutName));
    }
}
