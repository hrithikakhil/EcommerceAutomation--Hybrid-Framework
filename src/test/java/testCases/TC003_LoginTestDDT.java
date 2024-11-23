package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

/*  Valid Data
	1. Login success --> Test Passed --> In this case logout
	2. Login Fail --> Test Failed
	
	InValid Data
	1. Login success --> Test Failed --> In this case logout
	2. Login Fail --> Test Failed
	
*/
public class TC003_LoginTestDDT extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups="Datadriven")
	public void verifyLoginDDT(String email, String password, String expResult) {
		
		logger.info("***** stating TC_003_LoginTestDDT ******");
		
		try {
			HomePage homePage = new HomePage(driver);
			homePage.clickMyAccount();
			homePage.clickLogin();

			LoginPage loginPage = new LoginPage(driver);
			loginPage.setEmail(email);
			loginPage.setPassword(password);
			loginPage.clickLogin();

			MyAccountPage myAccPage = new MyAccountPage(driver);
			boolean targetPage = myAccPage.isMyAccountExist();

			if(expResult.equalsIgnoreCase("Valid")) {
				if (targetPage == true) {
					Assert.assertTrue(true);
					myAccPage.clickLogout();
				} else {
					Assert.assertTrue(false);
				}
			}else if(expResult.equalsIgnoreCase("Invalid")) {
				if (targetPage == true) {
					Assert.assertTrue(false);
					myAccPage.clickLogout();
				} else {
					Assert.assertTrue(true);
				}
			}
		}catch(Exception ex) {
			Assert.fail();
		}
		
		
		logger.info("***** Finished TC_003_LoginTestDDT ******");

	}
}
