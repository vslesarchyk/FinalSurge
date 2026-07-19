package tests;

import dto.Calculator;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import static dictionary.Elements.INTENSITY_CALC_ERROR_MESSAGE;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CalculatorTest extends BaseTest {

    @Epic("Workout calculators")
    @Feature("Intensity calculator")
    @Severity(SeverityLevel.NORMAL)
    @Test(
            description = "Calculate paces using Intensity calculator",
            groups = {"regression", "smoke"}
    )
    public void positiveIntensityCalculatorTest() {
        Calculator intensityCalc = Calculator.builder()
                .hours("00")
                .minutes("20")
                .seconds("10")
                .build();
        loginPage.openPage()
                .login(user, password);
        calendarPage.isPageOpened();
        dashboardPage.clickCalculator();
        calculatorPage.isPageOpened()
        .selectEvent()
        .inputIntencityCalcTime(intensityCalc)
        .clickCalculatePaces();
        assertTrue(calculatorPage.workoutPacesDisplayed(), "Workout paces isn't displayed");
    }

    @Epic("Workout calculators")
    @Feature("Intensity calculator")
    @Severity(SeverityLevel.NORMAL)
    @Test(
            description = "Negative check for calculation paces using Intensity calculator",
            groups = "regression"
    )
    public void negativeIntensityCalculatorTest() {
        Calculator intensityCalc = Calculator.builder()
                .hours(" ")
                .minutes("10")
                .seconds(" ")
                .build();
        loginPage.openPage()
                .login(user, password);
        calendarPage.isPageOpened();
        dashboardPage.clickCalculator();
        calculatorPage.isPageOpened()
                .selectEvent()
                .inputIntencityCalcTime(intensityCalc)
                .clickCalculatePaces();
        assertEquals(calculatorPage.getIntencityCalculateError(), INTENSITY_CALC_ERROR_MESSAGE, "Incorrect error message");
    }
}
