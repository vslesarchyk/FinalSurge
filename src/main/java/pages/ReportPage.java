package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class ReportPage extends BasePage{
    private static final String VIEW_REPORT = "#saveButton";
    private static final String WORKOUT_START_DATE_FIELD = "#WorkoutDate";
    private static final String WORKOUT_END_DATE_FIELD = "#WorkoutDateEnd";
    private static final String WORKOUT_LINK = "//table//a[text()='%s']";

    public ReportPage openPage() {
        Selenide.open("/WorkoutReport");
        return this;
    }

    public ReportPage isPageOpened() {
        log.info("Opening Report Page");
        $(VIEW_REPORT).shouldBe(clickable);
        return this;
    }

    @Step("Set the date")
    private ReportPage setDate(int daysOffset, String selector) {
        LocalDate targetDate = LocalDate.now().plusDays(daysOffset);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDate = targetDate.format(formatter);
        $(selector).setValue(formattedDate);
        return this;
    }

    @Step("Set the start date")
    public ReportPage setStartDate(int daysOffset) {
        log.info("Set start date");
        setDate(daysOffset, WORKOUT_START_DATE_FIELD);
        return this;
    }

    @Step("Set the end date")
    public ReportPage setEndDate(int daysOffset) {
        log.info("Set end date");
        setDate(daysOffset, WORKOUT_END_DATE_FIELD);
        return this;
    }

    @Step("View Report")
    public void clickViewReport() {
        log.info("View report");
        $(VIEW_REPORT).click();
    }

    @Step("Report is displayed")
    public boolean reportViewDisplayed(String activity) {
        return $x(String.format(WORKOUT_LINK, activity))
                .is(Condition.visible);
    }
}
