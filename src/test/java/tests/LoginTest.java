package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Epic("Authorization")
    @Feature("Authorization with valid credentials")
    @Severity(SeverityLevel.NORMAL)
    @Test(
            description = "Verify successful authorization with valid credentials",
            groups = {"smoke", "regression"}
    )
    public void checkLoginWithPositiveCred() {
        loginPage.openPage()
                .login(user, password)
                .isPageOpened();
        assertTrue(startPage.isLogoutButtonDisplayed(),
                "Logout button is not displayed"
        );
    }

    @DataProvider(name = "negativeLoginData Test")
    public Object[][] loginData() {
        return new Object[][]{
                {"testgmail.com", password, "Please enter a valid email address.", "INVALID_EMAIL"},
                {"", password, "Please enter your e-mail address.", "EMPTY_EMAIL"},
                {user, "123", "Invalid login credentials. Please try again.", "INVALID_PASSWORD"},
                {user, "", "Please enter a password.", "EMPTY_PASSWORD"},
        };
    }

    @Epic("Authorization")
    @Feature("Authorization with invalid credentials")
    @Severity(SeverityLevel.NORMAL)
    @Test(
            dataProvider = "negativeLoginData Test",
            groups = "regression"
    )
    public void checkLoginWithNegativeCred(String user, String password, String expectedError, String errorType) {
        loginPage.openPage()
                .loginWithNegativeCred(user, password);
        String actualError;
        switch (errorType) {
            case "INVALID_EMAIL":
            case "EMPTY_EMAIL":
                actualError = loginPage.getErrorEmailMessageText();
                break;
            case "EMPTY_PASSWORD":
                actualError = loginPage.getErrorPasswordMessageText();
                break;
            case "INVALID_PASSWORD":
                actualError = loginPage.getErrorLoginCredentials();
                break;
            default:
                throw new IllegalArgumentException("Unknown error type: " + errorType);
        }
        assertEquals(actualError, expectedError, "Incorrect error message");
    }
}
