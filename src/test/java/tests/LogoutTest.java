package tests;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogoutTest extends BaseTest {

    @Epic("Authorization")
    @Feature("User logout")
    @Severity(SeverityLevel.NORMAL)
    @Test (
            description = "Correct logout",
            groups = {"smoke", "regression"}
    )
    public void positiveLogoutTest() {
        Assert.assertEquals(
                loginPage.openPage()
                        .login(user, password)
                        .isPageOpened()
                        .clickLogoutButton()
                        .isPageOpened()
                        .getSuccessfulLogOutMessage(),
                "You have been successfully logged out of the system.",
                "You haven't been logged out of the system.");
    }
}
