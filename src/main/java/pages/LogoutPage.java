package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class LogoutPage extends BasePage {

    private static final By LOGOUT_MESSAGE = By.cssSelector(".alert.alert-success");

    public LogoutPage isPageOpened() {
        log.info("Opening Logout Page");
        $(LOGOUT_MESSAGE).shouldBe(visible);
        return this;
    }

    public LogoutPage openPage() {
        Selenide.open("/logout");
        return this;
    }

    @Step("Успешный выход из приложения")
    public String getSuccessfulLogOutMessage() {
        return $(LOGOUT_MESSAGE).getText();
    }
}
