package pages;

import com.codeborne.selenide.Selenide;
import dto.AddWorkout;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class CalendarPage extends BasePage {

    private static final String QUICK_ADD_BUTTON = "#QuickAddToggle";
    private static final String ACTIVITY_TYPE = "#ActivityType";
    private static final String ADD_WORKOUT = "#saveButton";
    private static final By TODAY_DATA_DAY = By.cssSelector("[class*='fc-today']");
    private static final By DAY_CONTENT = By.cssSelector(".fc-day-content");
    private static final By EVENT_ACTIVITY_TITLE = By.cssSelector(".fc-event-activity-title");
    private static final By ALERT_ERROR = By.cssSelector(".alert.alert-error");
    private static final String PLUS_ON_THE_CALENDAR = ".icon-plus";
    private static final String FULL_ADD_FROM_THE_CALENDAR = ".full-add";
    private static final String QUICK_ADD_FROM_THE_CALENDAR = "a.quick-add";
    private static final By FULL_VIEW_BUTTON = By.cssSelector("a.full-view");
    private static final String DATA_FIELD = "#WorkoutDate";

    public CalendarPage openPage() {
        Selenide.open("/Calendar");
        return this;
    }

    public CalendarPage isPageOpened() {
        log.info("Opening Calendar Page");
        $(QUICK_ADD_BUTTON).shouldBe(clickable);
        return this;
    }

    @Step("Adding a workout via a button Quick Add")
    public CalendarPage addQuickWorkoutWithButton() {
        $(QUICK_ADD_BUTTON).click();
        log.info("Workout successfully added");
        return this;
    }

    @Step("Selecting an activity type")
    public CalendarPage activityTypeQuickSelect(AddWorkout quickWorkout) {
        $(ACTIVITY_TYPE).selectOption(quickWorkout.getActivityType());
        log.info("Activity type was successfully selected");
        return this;
    }

    @Step("Click the button Add Workout")
    public CalendarPage clickAddWorkout() {
        log.info("Clicking 'Add Workout' button");
        $(ADD_WORKOUT).click();
        return this;
    }

    @Step("The created workout is displayed on the calendar")
    public boolean workOutIsDisplayed() {
        log.info("Workout successfully displayed on the calendar");
        return $(TODAY_DATA_DAY).$(DAY_CONTENT).$(EVENT_ACTIVITY_TITLE).isDisplayed();
    }

    @Step("Error on unfilled activity type")
    public String getActivityTypeError() {
        return $(ALERT_ERROR).getText();
    }

    @Step("Adding a workout via a button 'Full Add'")
    public CalendarPage clickFullWorkoutFromCalendar() {
        log.info("Opening 'Full Add' workout form from Calendar");
        $(TODAY_DATA_DAY).$(PLUS_ON_THE_CALENDAR).hover().click();
        $(TODAY_DATA_DAY).$(FULL_ADD_FROM_THE_CALENDAR).shouldBe(visible).hover().click();
        return this;
    }

    @Step("Quick adding a workout via calendar")
    public CalendarPage addQuickWorkoutFromCalendar() {
        log.info("Opening 'Quick Add' workout form from Calendar");
        $(TODAY_DATA_DAY).$(PLUS_ON_THE_CALENDAR).hover().click();
        $(TODAY_DATA_DAY).$(QUICK_ADD_FROM_THE_CALENDAR).shouldBe(visible).hover().click();
        return this;
    }

    @Step("Editing workout")
    public CalendarPage editTraining() {
        log.info("Opening workout for editing");
        $(EVENT_ACTIVITY_TITLE).click();
        $(FULL_VIEW_BUTTON).click();
        return this;
    }

    @Step("Setting the training date")
    public CalendarPage setWorkoutDate(int daysOffset) {
        LocalDate targetDate = LocalDate.now().plusDays(daysOffset);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yyyy");
        String formattedDate = targetDate.format(formatter);
        $(DATA_FIELD).setValue(formattedDate);
        return this;
    }
}
