package Base.PagesHelper;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class PetStoreHomePageHelper extends AbstractPageHelper {

    WebDriver driver;

    public PetStoreHomePageHelper(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private @FindBy(id = "LogoContent")
    WebElement logo;

    private @FindBy(xpath = "//div[@id='MenuContent']//a[.='Sign In']")
    WebElement signInButton;

    private @FindBy(xpath = "//div[@id='Catalog']//a[text()='Register Now!']")
    WebElement registerNowButton;

    private @FindBy(css = "input[name='username']")
    WebElement username;

    private @FindBy(css = "input[name='password']")
    WebElement password;

    private @FindBy(css = "input[name='signon']")
    WebElement loginButton;

    private @FindBy(xpath = "//div[@id='SidebarContent']//a[2]")
    WebElement DogCategoryButton;

    private @FindBy(id = "Catalog")
    WebElement Catalog;

    private @FindBy(xpath = "(//Table//td//a[1])[1]")
    WebElement FirstDogOption;

    private @FindBy(xpath = "(//Table//td[5])[1]")
    WebElement SelectFirstDogOption;

    private @FindBy(id = "WelcomeContent")
    WebElement WelcomeMessage;


    public void completeLogin(String username, String password) {
        getSignInButton().click();
        waitForElementToAppearWithFindElement(getUsername());
        getUsername().sendKeys(username);
        getPassword().clear();
        getPassword().sendKeys(password);
        getLoginButton().click();
    }

}
