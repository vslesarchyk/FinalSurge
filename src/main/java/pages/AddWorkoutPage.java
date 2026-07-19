package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import dto.AddWorkout;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class AddWorkoutPage extends BasePage {
    private static final String ADD_WORKOUT = "#saveButton";
    private static final String WORKOUT_NAME = "#Name";
    private static final By CUSTOMIZE_SETTINGS = By.cssSelector("a[href='WorkoutCustomize.cshtml?id=']");
    private static final String DETAILS = ".formSep";
    private static final By FIND_NAME = By.cssSelector("div:nth-of-type(3)");
    private static final By MODAL_VIEW = By.cssSelector(".modal-footer");
    private static final By DELETE_CONFIRM_BUTTON = By.cssSelector("a:nth-of-type(1)");
    private static final String DELETE_BUTTON = "#del-workout";
    private static final String ACTIVITY_TYPE_LOCATOR = "a[data-code='%s']";
    private static final String SUB_TYPE_ACTIVITY_LOCATOR = "//a[text()='%s']";

    public AddWorkoutPage isPageOpened() {
        log.info("Opening WorkoutAdd Page");
        $(CUSTOMIZE_SETTINGS).shouldBe(clickable);
        return this;
    }

    public AddWorkoutPage openPage() {
        Selenide.open("/WorkoutAdd");
        return this;
    }

    @Step("Selecting an activity type {typeActivity}, {subtypeActivity}")
    public AddWorkoutPage activityTypeSelect(String typeActivity, String subtypeActivity) {
        log.info("Selecting activity type: {}", typeActivity);
        $(String.format(ACTIVITY_TYPE_LOCATOR, typeActivity))
                .shouldBe(clickable)
                .click();
        $x(String.format(SUB_TYPE_ACTIVITY_LOCATOR, subtypeActivity))
                .shouldBe(clickable)
                .click();
        return this;
    }

    @Step("Adding a workout title")
    public AddWorkoutPage addWorkoutName(AddWorkout fullWorkout) {
        log.info("Adding Workout Name: {}", fullWorkout.getName());
        $(WORKOUT_NAME).setValue(fullWorkout.getName());
        return this;
    }

    @Step("Click the button Add Workout")
    public AddWorkoutPage clickAddWorkout() {
        log.info("Clicking 'Add Workout' button");
        $(ADD_WORKOUT).click();
        return this;
    }

    @Step("Getting the name of a workout")
    public String getWorkoutName() {
        String workoutName = $(DETAILS).$(FIND_NAME).text();
        log.info("Workout name: {}", workoutName);
        return workoutName;
    }

    @Step("Click the button Delete")
    public void deleteWorkout() {
        log.info("Clicking 'Delete' button");
        $(DELETE_BUTTON).click();
        $(MODAL_VIEW).$(DELETE_CONFIRM_BUTTON).click();
    }
}
