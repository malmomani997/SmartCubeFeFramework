package TestScenarios;

import Base.DTOs.SCDTO;
import Base.PagesHelper.PetStoreHomePageHelper;
import Base.TestHelpers.BaseTest;
import Base.dataProviders.DataGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SCTestCases extends BaseTest {

private PetStoreHomePageHelper petStoreHomePageHelper;

    @Test(dataProvider = "testDataProvider" , dataProviderClass = DataGenerator.class)
    public void registrationFlowOnPetStore(SCDTO testData) {

        Assert.assertTrue(getPetStoreHomePageHelper().getLogo().isDisplayed() , "Logo isn't displayed");
        getPetStoreHomePageHelper().getSignInButton().click();
        getPetStoreHomePageHelper().getRegisterNowButton().click();

        Assert.assertTrue(getPetStoreRegistrationPageHelper().getCatalog().isDisplayed() , "Catalog isn't displayed");

        getPetStoreRegistrationPageHelper().fillRegistrationFormAndSubmit(testData.userId , testData.newPassword, testData.firstName, testData.familyName , testData.email,
                testData.addressOne, testData.addressTwo, testData.city , testData.state, testData.zip, testData.country , testData.phoneNumber , testData.languagePreference, testData.favouriteCategory);


    }

    @Test(dataProvider = "testDataProvider" , dataProviderClass = DataGenerator.class)
    public void signInToPetStore(SCDTO testData) {

        Assert.assertTrue(getPetStoreHomePageHelper().getLogo().isDisplayed() , "Logo isn't displayed");

        getPetStoreHomePageHelper().completeLogin(testData.userId , testData.newPassword);

        Assert.assertTrue(getPetStoreHomePageHelper().getWelcomeMessage().isDisplayed() , "Welcome Message isn't displayed");
    }




    @Test(dataProvider = "testDataProvider" , dataProviderClass = DataGenerator.class)
    public void addToCartAndCompleteCheckoutWithALoggedInUser(SCDTO testData) {

        Assert.assertTrue(getPetStoreHomePageHelper().getLogo().isDisplayed() , "Logo isn't displayed");

        getPetStoreHomePageHelper().completeLogin(testData.userId , testData.newPassword);

        Assert.assertTrue(getPetStoreHomePageHelper().getWelcomeMessage().isDisplayed() , "Welcome Message isn't displayed");

        getPetStoreHomePageHelper().getDogCategoryButton().click();

        Assert.assertTrue(getPetStoreHomePageHelper().getCatalog().isDisplayed(), "Catalog isn't displayed");
        Assert.assertTrue(getPetStoreHomePageHelper().getFirstDogOption().isDisplayed(), "First dog option isn't displayed");

        getPetStoreHomePageHelper().getFirstDogOption().click();

        Assert.assertTrue(getPetStoreHomePageHelper().getCatalog().isDisplayed(), "Catalog isn't displayed");


        getPetStoreHomePageHelper().getSelectFirstDogOption().click();

        String productPrice = getPetStoreCheckoutPageHelper().getProductPrice().getText();

        Assert.assertTrue(getPetStoreHomePageHelper().getCatalog().isDisplayed(), "Catalog isn't displayed");
        Assert.assertTrue(getPetStoreCheckoutPageHelper().getProceedToCheckoutButton().isDisplayed(), "Proceed to checkout button isn't displayed");

        getPetStoreCheckoutPageHelper().getProceedToCheckoutButton().click();

        getPetStoreCheckoutPageHelper().getNewOrderButton().click();
        getPetStoreCheckoutPageHelper().getCompleteOrderButton().click();

        Assert.assertTrue(getPetStoreCheckoutPageHelper().getTotalPrice().getText().contains(productPrice), "Total price isn't correct");


    }

}