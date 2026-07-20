package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import dto.Shoes;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Log4j2
public class ShoesPage extends BasePage {
    private static final By ADD_SHOE_BUTTON = By.cssSelector("#saveButton");
    private static final String ADD_SHOE_NAME = "#ShoeName";
    private static final By SHOES_PAGE = By.cssSelector("a[href='EquipmentShoes.cshtml']");
    private static final By EQUIPMENT_PAGE = By.cssSelector("a[href='Equipment.cshtml']");
    private static final By TABLE_DATE_SELECTION = By.xpath("//table//tr//td[2]");
    private static final By EDIT_BUTTON = By.cssSelector(".btn.btn-mini");
    private static final String ERROR_MESSAGE = ".error";
    private static final By MODAL_VIEW = By.cssSelector(".modal-footer");
    private static final By DELETE_CONFIRM_BUTTON =  By.xpath(".//*[self::a or self::button][normalize-space()='OK']");
    private static final By DELETE_BUTTON = By.cssSelector("a[title='Delete'][href*='delete=']");
    private static final String BRAND = "#ShoeBrand";
    private static final String MODEL = "#ShoeModel";
    private static final String COST = "#ShoeCost";
    private static final String DATE_PURCHASED = "#ShoeDate";
    private static final String SIZE = "#ShoeSize";
    private static final String START_DISTANCE = "#StartDist";
    private static final String START_DISTANCE_TYPE = "#DistType";
    private static final String ALERT_DISTANCE = "#DistAlert";
    private static final String ALERT_DISTANCE_TYPE = "#DistAlertType";
    private static final String SELECT_SHOE_BRAND = "#s2id_ShoeBrand";

    public ShoesPage openPage() {
        Selenide.open("/EquipmentShoes");
        return this;
    }

    public ShoesPage isPageOpened() {
        log.info("Opening Shoes Page");
        $(ADD_SHOE_BUTTON).shouldBe(clickable);
        return this;
    }

    @Step("Navigate to Shoes page")
    public ShoesPage clickShoesPage() {
        log.info("Navigating to Shoes page");
        $(EQUIPMENT_PAGE).hover();
        $(SHOES_PAGE).click();
        return this;
    }

    @Step("Enter shoe name")
    public ShoesPage quickAddShoesInput(Shoes quickAddshoes) {
        log.info("Entering shoe name");
        $(ADD_SHOE_NAME).setValue(quickAddshoes.getShoeName());
        return this;
    }

    @Step("Click on 'Add Shoe Button'")
    public ShoesPage clickAddShoesButton() {
        log.info("Clicking on 'Add Shoe Button'");
        $(ADD_SHOE_BUTTON).click();
        return this;
    }

    @Step("Get {ShoeName} data from page")
    public Shoes getShoesNameFromPage() {
        String shoeNameFromPage = $(TABLE_DATE_SELECTION).getText();
        return Shoes.builder()
                .shoeName(shoeNameFromPage)
                .build();
    }

    @Step("Check whether shoe '{shoeName}' is present")
    public boolean isShoePresent(String shoeName) {
        return $$(TABLE_DATE_SELECTION)
                .findBy(Condition.exactText(shoeName))
                .exists();
    }

    @Step("Wait until Edit button should be clickable")
    public ShoesPage addShoesWait() {
        $(EDIT_BUTTON).shouldBe(clickable);
        return this;
    }


    @Step("Get Shoe Name error message")
    public String getShoeNameError() {
        return $(ERROR_MESSAGE)
                .shouldBe(visible)
                .getText();
    }

    @Step("Removing shoes")
    public ShoesPage deleteShoes(String shoeName) {
        log.info("Removing shoes");
        clickDeleteButton(shoeName);
        $(MODAL_VIEW).$(DELETE_CONFIRM_BUTTON).shouldBe(clickable).click();
        return this;
    }

    @Step("Click on the button 'Edit'")
    public void clickEditButton() {
        log.info("Clicking on button 'Edit'");
        $(EDIT_BUTTON).click();
    }

    @Step("Click on the button 'Delete'")
    public void clickDeleteButton(String shoeName) {
        log.info("Clicking on delete button");
        $(DELETE_BUTTON).click();
    }

    @Step("Get data from a page")
    public Shoes getInfoFromPage() {
        Shoes resultAddShoes = Shoes.builder()
                .brand($(BRAND).getText())
                .model($(MODEL).getValue())
                .cost($(COST).getValue())
                .datePurchased($(DATE_PURCHASED).getValue())
                .size($(SIZE).getText())
                .startDistance($(START_DISTANCE).getValue())
                .startDistanceType($(START_DISTANCE_TYPE).getText())
                .alertDistance($(ALERT_DISTANCE).getValue())
                .alertDistanceType($(ALERT_DISTANCE_TYPE).getText())
                .build();
        return resultAddShoes;
    }

    @Step("Editing shoes and adding new information")
    public ShoesPage editDetailsShoes(Shoes editAddshoes) {
        $(SELECT_SHOE_BRAND).click();
        $(BRAND).selectOption(editAddshoes.getBrand());
        $(MODEL).setValue(editAddshoes.getModel());
        $(COST).setValue(editAddshoes.getCost());
        $(DATE_PURCHASED).setValue(editAddshoes.getDatePurchased());
        $(SIZE).selectOption(editAddshoes.getSize());
        $(START_DISTANCE).setValue(editAddshoes.getStartDistance());
        $(START_DISTANCE_TYPE).selectOption(editAddshoes.getStartDistanceType());
        $(ALERT_DISTANCE).setValue(editAddshoes.getAlertDistance());
        $(ALERT_DISTANCE_TYPE).selectOption(editAddshoes.getAlertDistanceType());
        return this;
    }
}
