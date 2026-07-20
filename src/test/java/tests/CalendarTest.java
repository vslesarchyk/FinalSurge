package tests;

import dto.AddWorkout;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import static dictionary.Elements.ACTIVITY_TYPE_ERROR;
import static org.testng.Assert.*;

public class CalendarTest extends BaseTest {

    @Epic("Workouts")
    @Feature("Add workouts")
    @Severity(SeverityLevel.NORMAL)
    @Test(
            description = "Quickly add a workout using the button",
            groups = {"smoke", "regression"}
    )
    public void positiveAddQuickWorkOutForTodayByButton() {
        AddWorkout quickWorkout = AddWorkout.builder()
                .activityType("Run")
                .build();
        loginPage.openPage()
                .login(user, password);
        startPage.clickCalendar();
        calendarPage.isPageOpened()
                .addQuickWorkoutWithButton()
                .activityTypeQuickSelect(quickWorkout)
                .clickAddWorkout();
        assertTrue(calendarPage.workOutIsDisplayed(), "Workout hasn't been created");
    }

    @Epic("Workouts")
    @Feature("Add workouts")
    @Severity(SeverityLevel.NORMAL)
    @Test(
            description = "Negative check for adding a workout",
            groups = {"smoke", "regression"}
    )
    public void negativeAddQuickWorkout() {
        AddWorkout.builder()
                .activityType("")
                .build();
        loginPage.openPage()
                .login(user, password);
        startPage.clickCalendar();
        calendarPage.isPageOpened()
                .addQuickWorkoutWithButton()
                .clickAddWorkout();
        assertEquals(calendarPage.getActivityTypeError(), ACTIVITY_TYPE_ERROR, "Workout has been created");
    }

    @Epic("Workouts")
    @Feature("Add workouts")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Adding a workout via 'Full Add' button",
            groups = {"smoke", "regression"}
    )
    public void addFullFromCalendar() {
        AddWorkout fullWorkout = AddWorkout.builder()
                .name("Light run")
                .build();
        loginPage.openPage()
                .login(user, password);
        startPage.clickCalendar();
        calendarPage.isPageOpened()
                .clickFullWorkoutFromCalendar();
        addWorkoutPage.activityTypeSelect("run", "Long Run")
                .addWorkoutName(fullWorkout)
                .clickAddWorkout();
        assertEquals(addWorkoutPage.getWorkoutName(), fullWorkout.getName(), "Workout hasn't been created");
        startPage.clickCalendar();
    }

    @Epic("Workouts")
    @Feature("Edit workouts")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Editing a workout",
            groups = {"smoke", "regression"}
    )
    public void editWorkout() {
        AddWorkout quickWorkout = AddWorkout.builder()
                .activityType("Run")
                .build();
        loginPage.openPage()
                .login(user, password);
        startPage.clickCalendar();
        calendarPage.isPageOpened()
                .addQuickWorkoutFromCalendar()
                .activityTypeQuickSelect(quickWorkout)
                .clickAddWorkout();
        assertTrue(calendarPage.workOutIsDisplayed());
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
        assertTrue(calendarPage.workOutIsDisplayed(), "Workout hasn't been changed");
    }

    @Epic("Workouts")
    @Feature("Add workouts")
    @Severity(SeverityLevel.NORMAL)
    @Test(
            description = "Creating a future workout through the dashboard",
            groups = {"smoke", "regression"}
    )
    public void createFutureTrainingFromDashboardPage() {
        AddWorkout quickWorkout = AddWorkout.builder()
                .activityType("Bike")
                .build();
        loginPage.openPage()
                .login(user, password);
        dashboardPage.clickCalendar();
        calendarPage.isPageOpened()
                .addQuickWorkoutWithButton()
                .setWorkoutDate(1)
                .activityTypeQuickSelect(quickWorkout)
                .clickAddWorkout();
        dashboardPage.clickDashboardPage()
                .clickUpcomingWorkouts();
        assertTrue(dashboardPage.upcomingWorkoutsExists(), "Workout hasn't been created");
        dashboardPage.clickFutureWorkoutDetails();
        workoutDetailsPage.isPageOpened();
    }

    @Epic("Workouts")
    @Feature("Add workouts")
    @Severity(SeverityLevel.NORMAL)
    @Test(
            description = "Creating a past workout through the dashboard",
            groups = {"smoke", "regression"}
    )
    public void createPastTrainingFromDashboardPage() {
        AddWorkout quickWorkout = AddWorkout.builder()
                .activityType("Bike")
                .build();
        loginPage.openPage()
                .login(user, password);
        dashboardPage.clickCalendar();
        calendarPage.isPageOpened()
                .addQuickWorkoutWithButton()
                .setWorkoutDate(-1)
                .activityTypeQuickSelect(quickWorkout)
                .clickAddWorkout();
        dashboardPage.clickDashboardPage()
                .clickPastWorkouts();
        assertTrue(dashboardPage.pastWorkoutsExists(), "Workout hasn't been created");
        dashboardPage.clickPastWorkoutDetails();
        workoutDetailsPage.isPageOpened();
    }

    @Epic("Workouts")
    @Feature("Remove workouts")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Remove past workout through the dashboard",
            groups = {"smoke", "regression"}
    )
    public void removePastTrainingFromDashboardPage() {
        String workoutName = "Remove test";
        AddWorkout quickWorkout = AddWorkout.builder()
                .activityType("Walk")
                .name(workoutName)
                .build();
        loginPage.openPage()
                .login(user, password);
        dashboardPage.clickCalendar();
        calendarPage.isPageOpened()
                .addQuickWorkoutWithButton()
                .setWorkoutDate(-1)
                .activityTypeQuickSelect(quickWorkout)
                .clickAddWorkout();
        dashboardPage.clickDashboardPage()
                .clickPastWorkouts();
        assertTrue(dashboardPage.pastWorkoutsExists());
        dashboardPage.clickPastWorkoutDetails();
        workoutDetailsPage.isPageOpened()
                .clickUpdateWorkout();
        addWorkoutPage.deleteWorkout();
        calendarPage.isPageOpened();
        dashboardPage.clickDashboardPage();
        assertFalse(dashboardPage.isWorkoutPresent(workoutName), "Workout hasn't been removed");
    }
}
