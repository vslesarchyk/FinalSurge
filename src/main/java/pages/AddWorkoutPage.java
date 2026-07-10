package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import dto.AddWorkout;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Selenide.$;

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

    public AddWorkoutPage isPageOpened() {
        log.info("Opening WorkoutAdd Page");
        $(CUSTOMIZE_SETTINGS).shouldBe(clickable);
        return this;
    }

    public AddWorkoutPage openPage() {
        Selenide.open("/WorkoutAdd");
        return this;
    }

    @Step("Выбор типа активности {typeActivity}, {subtypeActivity}")
    public AddWorkoutPage activityTypeSelect(String typeActivity, String subtypeActivity) {
        $(By.cssSelector("a[data-code='" + typeActivity + "']")).shouldBe(clickable).click();
        $(By.xpath("//a[text()='" + subtypeActivity + "']")).shouldBe(clickable).click();
        return this;
    }

    @Step("Добавление названия тренировки")
    public AddWorkoutPage addWorkoutName(AddWorkout fullWorkout) {
        $(WORKOUT_NAME).setValue(fullWorkout.getName());
        return this;
    }

    @Step("Нажать на кнопку Add Workout")
    public AddWorkoutPage clickAddWorkout() {
        $(ADD_WORKOUT).click();
        return this;
    }

    @Step("Получение названия тренировки")
    public String getWorkoutName() {
        return $(DETAILS).$(FIND_NAME).text();
    }

    @Step("Нажать на кнопку Delete")
    public void deleteWorkout() {
        $(DELETE_BUTTON).click();
        $(MODAL_VIEW).$(DELETE_CONFIRM_BUTTON).click();
    }
}
