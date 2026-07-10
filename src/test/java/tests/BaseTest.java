package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import pages.*;
import utils.PropertyReader;

import java.util.HashMap;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseTest {
    LoginPage loginPage;
    StartPage startPage;
    CalendarPage calendarPage;
    AddWorkoutPage addWorkoutPage;
    WorkoutDetailsPage workoutDetailsPage;
    DashboardPage dashboardPage;

    String USER = System.getProperty("user", PropertyReader.getProperty("user"));
    String PASSWORD= System.getProperty("password", PropertyReader.getProperty("password"));

    @BeforeMethod(alwaysRun = true, description = "Browser settings")
    public void setUp(@Optional("chrome") String browser) {
        Configuration.browser =  browser;
        Configuration.baseUrl = "https://log.finalsurge.com/";
        Configuration.timeout = 20000;
        Configuration.clickViaJs = true;
        Configuration.browserSize = "1920x1080";
        Configuration.headless = Boolean.parseBoolean(
                System.getProperty("headless", "false")
        );

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();

            HashMap<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("credentials_enable_service", false);
            chromePrefs.put("profile.password_manager_enabled", false);

            options.setExperimentalOption("prefs", chromePrefs);
            options.addArguments("--incognito");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-infobars");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");

            Configuration.browserCapabilities = options;
        } else if (browser.equalsIgnoreCase("edge")) {

            EdgeOptions options = new EdgeOptions();

            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");

            Configuration.browserCapabilities = options;
        }
        loginPage = new LoginPage();
        startPage = new StartPage();
        calendarPage = new CalendarPage();
        addWorkoutPage = new AddWorkoutPage();
        workoutDetailsPage = new WorkoutDetailsPage();
        dashboardPage = new DashboardPage();

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
        );
    }
    @AfterMethod (alwaysRun = true, description = "Closing the browser")
        public void tearDown() {
        getWebDriver().quit();
    }
}
