package pages;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class LoginPage extends BasePage {
    private static final By INPUT_EMAIL_FIELD = By.cssSelector("#login_name");
    private static final By INPUT_PASSWORD_FIELD = By.cssSelector("#login_password");
    private static final By LOGIN_BUTTON = By.cssSelector("button[type='submit']");
    private static final By  ERROR_MESSAGE_EMAIL = By.cssSelector("label[class='error'][for='login_name']");
    private static final By  ERROR_MESSAGE_PASSWORD = By.cssSelector("label[class='error'][for='login_password']");
    private static final By  ERROR_LOGIN_CREDENTIALS = By.cssSelector(".alert.alert-error");

    public LoginPage openPage() {
        Selenide.open("/login");
        return this;
    }

    public LoginPage isPageOpened() {
        log.info("Opening Login Page");
        Selenide.open("/login");
        return this;
    }

    @Step("Authorization")
    public StartPage login(String user, String password) {
        $(INPUT_EMAIL_FIELD).setValue(user);
        $(INPUT_PASSWORD_FIELD).setValue(password);
        $(LOGIN_BUTTON).click();
        log.info("User successfully log in");
        return new StartPage();
    }

    @Step("Authorization with negative credits")
    public LoginPage loginWithNegativeCred(String user, String password){
        $(INPUT_EMAIL_FIELD).setValue(user);
        $(INPUT_PASSWORD_FIELD).setValue(password);
        $(LOGIN_BUTTON).click();
        log.info("User doesn't log in");
        return this;
    }

    @Step("Receiving login errors")
    public String getErrorEmailMessageText() {
        return $(ERROR_MESSAGE_EMAIL).text();
    }

    @Step("Receiving password errors")
    public String getErrorPasswordMessageText() {
        return $(ERROR_MESSAGE_PASSWORD).text();
    }

    @Step("Receiving authorization errors")
    public String getErrorLoginCredentials() {
        return $(ERROR_LOGIN_CREDENTIALS).text();
    }
}
