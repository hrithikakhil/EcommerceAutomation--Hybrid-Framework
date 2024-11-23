package testCases;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegisterationPage;
import pageObjects.HomePage;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups= {"Regression", "Master"})
	public void verifyAccountRegistration() {

		logger.info("*****Starting TC001_AccountRegistrationTest*****");
		try {

			HomePage homePage = new HomePage(driver);
			homePage.clickMyAccount();
			logger.info("Clicked on My Account Link");
			homePage.clickRegister();
			logger.info("Clicked Registeration Link");

			AccountRegisterationPage accountRegisterationPage = new AccountRegisterationPage(driver);
			logger.info("Providing Customer Details");
			accountRegisterationPage.setFirstName(randomString().toUpperCase());
			accountRegisterationPage.setLastName(randomString().toUpperCase());
			accountRegisterationPage.setEmail(randomString() + "@gmail.com");
			accountRegisterationPage.setTelephone(randomNumber());

			String password = randomAlphaNumeric();
			accountRegisterationPage.setPassword(password);
			accountRegisterationPage.setConfirmPassword(password);

			accountRegisterationPage.setPrivacyPolicy();
			accountRegisterationPage.clickContinue();

			logger.info("Validating expected Message");
			String confirmMsg = accountRegisterationPage.getConfirmationMsg();
			assertEquals(confirmMsg, "Your Account Has Been Created!");

		} catch (Exception ex) {

			logger.error("Test Failed");
			logger.debug("Debug Logs");
			Assert.fail();
		}

		logger.info("*****Finished TC001_AccountRegistrationTest*****");
	}
}
