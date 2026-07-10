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

    @Step("Добавление тренировки через кнопку Quick Add")
    public CalendarPage addQuickWorkoutWithButton() {
        $(QUICK_ADD_BUTTON).click();
        return this;
    }

    @Step("Выбор типа активности")
    public CalendarPage activityTypeQuickSelect(AddWorkout quickWorkout) {
        $(ACTIVITY_TYPE).selectOption(quickWorkout.getActivityType());
        return this;
    }

    @Step("Нажать на кнопку Add Workout")
    public CalendarPage clickAddWorkout() {
        $(ADD_WORKOUT).click();
        return this;
    }

    @Step("Созданная тренировка отображается на календаре")
    public boolean workOutIsDisplayed() {
        return $(TODAY_DATA_DAY).$(DAY_CONTENT).$(EVENT_ACTIVITY_TITLE).isDisplayed();
    }

    @Step("Ошибка на незаполненный тип активности")
    public String getActivityTypeError() {
        return $(ALERT_ERROR).getText();
    }

    @Step("Добавление тренировки черз кнопку 'Full Add'")
    public CalendarPage clickFullWorkoutFromCalendar() {
        $(TODAY_DATA_DAY).$(PLUS_ON_THE_CALENDAR).hover().click();
        $(TODAY_DATA_DAY).$(FULL_ADD_FROM_THE_CALENDAR).shouldBe(visible).hover().click();
        return this;
    }

    @Step("Добавление тренировки через календарь")
    public CalendarPage addQuickWorkoutFromCalendar() {
        $(TODAY_DATA_DAY).$(PLUS_ON_THE_CALENDAR).hover().click();
        $(TODAY_DATA_DAY).$(QUICK_ADD_FROM_THE_CALENDAR).shouldBe(visible).hover().click();
        return this;
    }

    @Step("Редактирование тренировки")
        public CalendarPage editTraining() {
        $(EVENT_ACTIVITY_TITLE).click();
        $(FULL_VIEW_BUTTON).click();
            return this;
    }

    @Step("Установка даты тренировки")
    public CalendarPage setWorkoutDate(int daysOffset) {
        LocalDate targetDate = LocalDate.now().plusDays(daysOffset);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yyyy");
        String formattedDate = targetDate.format(formatter);
        $(DATA_FIELD).setValue(formattedDate);
        return this;
    }
}
