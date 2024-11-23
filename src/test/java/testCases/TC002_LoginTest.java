package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC002_LoginTest extends BaseClass {

	@Test(groups= {"Sanity", "Master"})
	public void verifyLogin() {
		logger.info("****Starting TC002_LoginTest*****");

		try {
			HomePage homePage = new HomePage(driver);
			homePage.clickMyAccount();
			homePage.clickLogin();

			LoginPage loginPage = new LoginPage(driver);
			loginPage.setEmail(prop.getProperty("email"));
			loginPage.setPassword(prop.getProperty("password"));
			loginPage.clickLogin();
			
			
			MyAccountPage myAccPage = new MyAccountPage(driver);
			boolean targetPage = myAccPage.isMyAccountExist();

			Assert.assertEquals(targetPage, true, "Login Failed");
		} catch (Exception ex) {
			Assert.fail();
		}

		logger.info("****Finished TC002_LoginTest*****");
	}
}
