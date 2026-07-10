package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import  static com.codeborne.selenide.Condition.clickable;
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
    private static final String PAST_WORKOUTS_DETAILS = ".minor";
    private static final String PAST_WORKOUTS_VIEW = "div[data-label='past-workouts'] .w-box-header";

    public DashboardPage openPage() {
        Selenide.open("/Default");
        return this;
    }

    public DashboardPage isPageOpened() {
        log.info("Opening Dashboard Page");
        $(VIEW_CALENDAR_BUTTON).shouldBe(clickable);
        return this;
    }

    @Step("Открыть страницу Календарь")
    public CalendarPage clickCalendar() {
        $(CALENDAR_PAGE).click();
        return new CalendarPage();
    }

    @Step("Нажать на кнопку 'Dashboard'")
    public DashboardPage clickDashboardPage() {
        $(DASHBOARD_BUTTON).click();
        return this;
    }

    @Step("Нажать на кнопку 'Upcoming Workouts'")
    public DashboardPage clickUpcomingWorkouts() {
        $(UPCOMING_WORKOUTS_VIEW).$(UPCOMING_WORKOUTS).click();
        return this;
    }

    public boolean upcomingWorkoutsExists() {
        return $(UPCOMING_WORKOUTS_VIEW).$(WORKOUT_DETAILS_VIEW).exists();
    }

    public DashboardPage clickPastWorkoutDetails() {
        $(PAST_WORKOUTS)
                .$(WORKOUT_DETAILS_PAGE)
                .click();
        return this;
    }

    @Step("Выбрать будущую тренировку")
    public DashboardPage clickFutureWorkoutDetails() {
        $(FUTURE_WORKOUTS)
                .$(WORKOUT_DETAILS_PAGE)
                .click();
        return this;
    }

    @Step("Нажать на кнопку Recent Past Workouts")
    public void clickPastWorkouts() {
        $(PAST_WORKOUTS_VIEW).click();
    }

    public boolean pastWorkoutsExists() {
        return $(PAST_WORKOUTS).$(WORKOUT_DETAILS_VIEW).exists();
    }

       public boolean isWorkoutPresent(String workoutName) {
        return $$(WORKOUT_DETAILS_PAGE)
                .findBy(Condition.text(workoutName))
                .exists();
    }
}
