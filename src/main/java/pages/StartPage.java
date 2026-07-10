package pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class StartPage extends BasePage{
    private static final String QUICK_ADD_TOGGLE = "#QuickAddToggle";
    private static final By LOGOUT_BUTTON = byText("Logout");
    private static final By CALENDAR_PAGE = By.cssSelector("a[href='Calendar.cshtml'].ptip_s");

    public StartPage isPageOpened() {
        log.info("Opening Start Page");
        $(QUICK_ADD_TOGGLE).shouldBe(clickable);
        return this;
    }

    public StartPage openPage() {
        Selenide.open("/Calendar");
        return this;
    }

    @Step("Нажатие на кнопку Login")
    public LogoutPage clickLogoutButton() {
        $(LOGOUT_BUTTON).click();
        return new LogoutPage();
    }

    @Step("Нажатие на календарь")
    public CalendarPage clickCalendar() {
        $(CALENDAR_PAGE).click();
        return new CalendarPage();
    }
}
