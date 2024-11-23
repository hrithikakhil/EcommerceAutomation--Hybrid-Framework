package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h2[text()='My Account']") // MyAccount Page heading
	WebElement msgHeading;

	@FindBy(xpath = "//div[@class='list-group']//a[text()='Logout']")
	WebElement lnkLogout;

	public boolean isMyAccountExist() {
		try {
			return msgHeading.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public void clickLogout() {
		lnkLogout.click();
	}
}
