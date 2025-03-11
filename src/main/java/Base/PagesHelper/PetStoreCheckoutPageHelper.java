package Base.PagesHelper;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class PetStoreCheckoutPageHelper extends AbstractPageHelper{

    WebDriver driver;

    public PetStoreCheckoutPageHelper(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    private @FindBy (xpath = "//div[@id='Cart']//a[text()='Proceed to Checkout']")
    WebElement proceedToCheckoutButton;

    private @FindBy (xpath = "(//div[@id='Cart']//Table//td)[6]")
    WebElement productPrice;

    private @FindBy (css = "input[name='newOrder']")
    WebElement newOrderButton;

    private @FindBy (xpath = "//a[@class='Button']")
    WebElement completeOrderButton;

    private @FindBy (xpath = "(//table)[2]//tr[3]//th")
    WebElement totalPrice;
}
