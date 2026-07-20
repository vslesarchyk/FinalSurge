package tests;

import dto.Shoes;
import dto.ShoesFactory;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

import static dictionary.Elements.SHOE_NAME_ERROR;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class ShoesTest extends BaseTest {

    @Epic("Shoes")
    @Feature("Add shoes")
    @Severity(SeverityLevel.NORMAL)
    @Test(
            description = "Verify that shoes successfully added",
            groups = {"regression", "smoke"}
    )
    public void positiveAddShoesTest() {
        Shoes shoes = Shoes.builder()
                .shoeName("Sport shoes")
                .build();
        loginPage.openPage()
                .login(user, password);
        calendarPage.isPageOpened();
        shoesPage.clickShoesPage()
                .isPageOpened()
                .quickAddShoesInput(shoes)
                .clickAddShoesButton();
        Shoes actualShoes = shoesPage.getShoesNameFromPage();
        shoesPage.addShoesWait();
        assertEquals(actualShoes.getShoeName(), shoes.getShoeName(), "The name of the shoes does not match the expected one.");
    }

    @Epic("Shoes")
    @Feature("Add shoes")
    @Severity(SeverityLevel.MINOR)
    @Test(
            description = "Verify that a shoe cannot be added with an empty Shoe Name",
            groups = {"regression", "smoke"}
    )
    public void negativeAddShoesTest() {
        Shoes shoes = Shoes.builder()
                .shoeName(" ")
                .build();
        loginPage.openPage()
                .login(user, password);
        calendarPage.isPageOpened();
        shoesPage.clickShoesPage()
                .isPageOpened()
                .quickAddShoesInput(shoes)
                .clickAddShoesButton();
        shoesPage.addShoesWait();
        assertEquals(shoesPage.getShoeNameError(), SHOE_NAME_ERROR, "Incorrect error message");
    }

    @Epic("Shoes")
    @Feature("Remove shoes")
    @Severity(SeverityLevel.NORMAL)
    @Test(
            description = "Verify that shoes successfully removed",
            groups = {"regression", "smoke"}
    )
    public void deleteShoesTest() {
        String shoeName = "Sport shoes " + System.currentTimeMillis();
        Shoes shoes = Shoes.builder()
                .shoeName(shoeName)
                .build();
        loginPage.openPage()
                .login(user, password);
        calendarPage.isPageOpened();
        shoesPage.clickShoesPage()
                .isPageOpened()
                .quickAddShoesInput(shoes)
                .clickAddShoesButton()
                .clickEditButton();
        shoesPage.deleteShoes(shoes.getShoeName());
        assertFalse(
                shoesPage.isShoePresent(shoes.getShoeName()),
                "Shoe was not deleted"
        );
    }

    @Epic("Shoes")
    @Feature("Edit shoes")
    @Severity(SeverityLevel.NORMAL)
    @Test(
            description = "Edit shoes",
            groups = {"regression", "smoke"}
    )
    public void positiveEditShoesTest() {
        Shoes shoes = ShoesFactory.getShoes();
        loginPage.openPage()
                .login(user, password);
        calendarPage.isPageOpened();
        shoesPage.clickShoesPage()
                .isPageOpened()
                .quickAddShoesInput(shoes)
                .clickAddShoesButton()
                .clickEditButton();
        shoesPage.isPageOpened();
        Shoes editAddshoes = Shoes.builder()
                .shoeName("Nike")
                .brand("adidas")
                .model("Test")
                .cost("100.00")
                .datePurchased("7/17/2026")
                .size("7")
                .startDistance("1")
                .startDistanceType("km")
                .alertDistance("5")
                .alertDistanceType("km")
                .notes("successful test")
                .build();
        shoesPage.editDetailsShoes(editAddshoes);
        shoesPage.clickAddShoesButton();
        shoesPage.clickEditButton();
        Shoes actualShoes = shoesPage.getInfoFromPage();
        shoesPage.addShoesWait();
        assertEquals(actualShoes, editAddshoes, "The values do not match the expected ones.");
    }
}
