package tests;

import dto.AddWorkout;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ReportTest extends BaseTest {

    @Epic("Reports")
    @Feature("Athlete workout report")
    @Severity(SeverityLevel.NORMAL)
    @Test(
            description = "View Workout report",
            groups = {"regression", "smoke"}
    )
    public void positiveViewWorkoutReport() {
        AddWorkout quickWorkout = AddWorkout.builder()
                .activityType("Walk")
                .build();
        loginPage.openPage()
                .login(user, password);
        calendarPage.isPageOpened();
        dashboardPage.clickCalendar();
        calendarPage.isPageOpened()
                .addQuickWorkoutWithButton()
                .activityTypeQuickSelect(quickWorkout)
                .clickAddWorkout();
        dashboardPage.clickWorkoutReportPage();
        reportPage.isPageOpened()
                .setStartDate(-1)
                .setEndDate(1)
                .clickViewReport();
        assertTrue(reportPage.reportViewDisplayed(quickWorkout.getActivityType()), "Report doesn't displayed");
    }
}
