package tests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogoutTest extends BaseTest {

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
                "You have been successfully logged out of the system.");
    }
}
