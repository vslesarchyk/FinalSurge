package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Log4j2
public class DashboardPage extends BasePage {
    private static final String VIEW_CALENDAR_BUTTON = "#workout-add";
    private static final By CALENDAR_PAGE = By.cssSelector("a[href='Calendar.cshtml'].ptip_s");
    private static final By DASHBOARD_BUTTON = By.cssSelector("a[href='Default.cshtml'].ptip_s");
    private static final By UPCOMING_WORKOUTS_VIEW = By.cssSelector(".w-box.w-box-green.hideable");
    private static final String UPCOMING_WORKOUTS = ".w-box-header";
    private static final By FUTURE_WORKOUTS = By.cssSelector("div[data-label='future-workouts']");
    private static final String WORKOUT_DETAILS_VIEW = ".dont-break-out";
    private static final By WORKOUT_DETAILS_PAGE = By.cssSelector("a[href^='WorkoutDetails.cshtml?id=']");
    private static final String PAST_WORKOUTS = "div[data-label='past-workouts']";
    private static final String PAST_WORKOUTS_VIEW = "div[data-label='past-workouts'] .w-box-header";
    private static final By CALCULATOR_PAGE = By.cssSelector("a[href='#'].ptip_s");
    private static final By WORKOUT_REPORT_PAGE = By.cssSelector("a[href='WorkoutReport.cshtml'].ptip_s");

    public DashboardPage openPage() {
        Selenide.open("/Default");
        return this;
    }

    public DashboardPage isPageOpened() {
        log.info("Opening Dashboard Page");
        $(VIEW_CALENDAR_BUTTON).shouldBe(clickable);
        return this;
    }

    @Step("Open the Calendar page")
    public CalendarPage clickCalendar() {
        log.info("Opening Calendar page");
        $(CALENDAR_PAGE).click();
        return new CalendarPage();
    }

    @Step("Click the button 'Dashboard'")
    public DashboardPage clickDashboardPage() {
        log.info("Clicking 'Dashboard'");
        $(DASHBOARD_BUTTON).click();
        return this;
    }

    @Step("Click the button 'Upcoming Workouts'")
    public DashboardPage clickUpcomingWorkouts() {
        log.info("Clicking 'Upcoming Workouts'");
        $(UPCOMING_WORKOUTS_VIEW).$(UPCOMING_WORKOUTS).click();
        return this;
    }

    @Step("View upcoming workouts")
    public boolean upcomingWorkoutsExists() {
        return $(UPCOMING_WORKOUTS_VIEW).$(WORKOUT_DETAILS_VIEW).exists();
    }

    @Step("Click on past workouts details")
    public DashboardPage clickPastWorkoutDetails() {
        log.info("Clicking past workouts");
        $(PAST_WORKOUTS)
                .$(WORKOUT_DETAILS_PAGE)
                .click();
        return this;
    }

    @Step("Select a future workout")
    public DashboardPage clickFutureWorkoutDetails() {
        log.info("Clicking on future workout");
        $(FUTURE_WORKOUTS)
                .$(WORKOUT_DETAILS_PAGE)
                .click();
        return this;
    }

    @Step("Click on button Recent Past Workouts")
    public void clickPastWorkouts() {
        log.info("Click past workouts");
        $(PAST_WORKOUTS_VIEW).click();
    }

    @Step("Past workout exist")
    public boolean pastWorkoutsExists() {
        return $(PAST_WORKOUTS).$(WORKOUT_DETAILS_VIEW).exists();
    }

    @Step("Workout exist")
    public boolean isWorkoutPresent(String workoutName) {
        return $$(WORKOUT_DETAILS_PAGE)
                .findBy(Condition.text(workoutName))
                .exists();
    }

    @Step("Open 'Calculator'")
    public void clickCalculator() {
        log.info("Click on calculator");
        $(CALCULATOR_PAGE).click();
    }

    @Step("Oper page 'Workout Report'")
    public void clickWorkoutReportPage() {
        log.info("Opening workout report page");
        $(WORKOUT_REPORT_PAGE).click();
    }
}
