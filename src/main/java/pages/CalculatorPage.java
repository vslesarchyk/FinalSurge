package pages;

import com.codeborne.selenide.Selenide;
import dto.Calculator;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

@Log4j2
public class CalculatorPage extends BasePage {

    private static final String CALCULATE_PACES_BUTTON = "#saveButtonSettings";
    private static final String EVENT_TYPE = "#FIVEK";
    private static final String TIME_HH = "#TimeHH";
    private static final String TIME_MM = "#TimeMM";
    private static final String TIME_SS = "#TimeSS";
    private static final By PACE_SPLIT = By.cssSelector(".w-box");
    private static final By ALERT_ERROR = By.cssSelector(".alert.alert-error");

    public CalculatorPage openPage() {
        Selenide.open("/Calendar");
        return this;
    }

    public CalculatorPage isPageOpened() {
        log.info("CalculatorPage is opened");
        switchTo().frame(0);
        {
            $(CALCULATE_PACES_BUTTON).shouldBe(clickable);
            return this;
        }
    }

    @Step("Filling out the calculator")
    public CalculatorPage selectEvent() {
        $(EVENT_TYPE).click();
        log.info("Event successfully was selected");
        return this;
    }

    @Step("Time filling")
    public CalculatorPage inputIntencityCalcTime(Calculator intensityCalc) {
        $(TIME_HH).setValue(intensityCalc.getHours());
        $(TIME_MM).setValue(intensityCalc.getMinutes());
        $(TIME_SS).setValue(intensityCalc.getSeconds());
        log.info("Intencity calculate time was successfully fullfilled");
        return this;
    }

    @Step("Click Calculate paces")
    public CalculatorPage clickCalculatePaces() {
        $(CALCULATE_PACES_BUTTON).click();
        log.info("Workout paces displayed");
        return this;
    }

    @Step("Workout paces displayed")
    public boolean workoutPacesDisplayed() {
        return $(PACE_SPLIT).should(exist).isDisplayed();
    }

    @Step("Error when activity type is not filled in")
    public String getIntencityCalculateError() {
        log.info("Display error");
        return $(ALERT_ERROR).text();
    }
}
