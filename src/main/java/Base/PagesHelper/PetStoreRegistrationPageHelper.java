package Base.PagesHelper;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

@Getter
public class PetStoreRegistrationPageHelper extends AbstractPageHelper {

    public PetStoreRegistrationPageHelper(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    private @FindBy(id = "Catalog")
    WebElement catalog;

    //User Information

    private @FindBy(css = "input[name='username']")
    WebElement username;


    private @FindBy(css = "input[name='password']")
    WebElement password;


    private @FindBy(css = "input[name='repeatedPassword']")
    WebElement repeatPassword;


    //Account Information

    private @FindBy(css = "input[name='account.firstName']")
    WebElement accountFirstName;


    private @FindBy(css = "input[name='account.lastName']")
    WebElement accountLastName;


    private @FindBy(css = "input[name='account.email']")
    WebElement accountEmail;


    private @FindBy(css = "input[name='account.phone']")
    WebElement accountPhone;


    private @FindBy(css = "input[name='account.address1']")
    WebElement accountAddressOne;


    private @FindBy(css = "input[name='account.address2']")
    WebElement accountAddressTwo;


    private @FindBy(css = "input[name='account.city']")
    WebElement accountCity;


    private @FindBy(css = "input[name='account.state']")
    WebElement accountState;


    private @FindBy(css = "input[name='account.zip']")
    WebElement accountZip;


    private @FindBy(css = "input[name='account.country']")
    WebElement accountCountry;


    //Profile Information

    private @FindBy(css = "select[name='account.languagePreference']")
    WebElement accountLanguagePreference;


    private @FindBy(css = "select[name='account.favouriteCategoryId']")
    WebElement accountFavouriteCategoryId;


    private @FindBy(xpath = "//input[@name='account.listOption']")
    WebElement accountListOption;


    private @FindBy(xpath = "//input[@name='account.bannerOption']")
    WebElement accountBanner;


    private @FindBy(css = "input[name='newAccount']")
    WebElement createAccountButton;


    public void selectOptionFromList(String option, WebElement webElement) {
        Select select = new Select(webElement);
        select.selectByValue(option);
    }


    public void fillRegistrationFormAndSubmit(String userId, String newPassword, String firstName, String familyName, String email, String addressOne, String addressTwo, String city, String state, String zip, String country , String phoneNumber , String languagePreference ,String favouriteCategory) {
        getUsername().sendKeys(userId);
        getPassword().sendKeys(newPassword);
        getRepeatPassword().sendKeys(newPassword);
        getAccountFirstName().sendKeys(firstName);
        getAccountLastName().sendKeys(familyName);
        getAccountEmail().sendKeys(email);
        getAccountAddressOne().sendKeys(addressOne);
        getAccountAddressTwo().sendKeys(addressTwo);
        getAccountCity().sendKeys(city);
        getAccountState().sendKeys(state);
        getAccountZip().sendKeys(zip);
        getAccountCountry().sendKeys(country);
        getAccountPhone().sendKeys(phoneNumber);

        selectOptionFromList(languagePreference , getAccountLanguagePreference());
        selectOptionFromList(favouriteCategory , getAccountFavouriteCategoryId());

        getAccountListOption().click();
        getAccountBanner().click();

        getCreateAccountButton().click();
    }

}
