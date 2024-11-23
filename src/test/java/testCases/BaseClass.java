package testCases;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public Logger logger;

	public static WebDriver driver;

	public Properties prop;
	
	@BeforeClass(groups= {"Sanity", "Regression", "Master", "Datadriven"})
	@Parameters({ "os", "browser" })
	public void setup(String os, String br) throws IOException {
		
		
		FileReader fileReader = new FileReader("./src//test//resources//config.properties");
		prop = new Properties();
		prop.load(fileReader);
		
		logger = LogManager.getLogger(this.getClass());
		
		switch (br.toLowerCase()) {

			case "chrome":
				driver = new ChromeDriver();
				break;
	
			case "edge":
				driver = new EdgeDriver();
				break;
	
			case "fox":
				driver = new FirefoxDriver();
				break;
	
			default:
				System.out.println("Invalid browser");
				return;
		}
		
		driver = new ChromeDriver();
//		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(prop.getProperty("appUrl"));
		driver.manage().window().maximize();
	}

	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void tearDown() {
		driver.quit();
	}

	public String randomString() {
		String randomString = RandomStringUtils.randomAlphabetic(6);
		return randomString;
	}

	public String randomNumber() {
		String randomNum = RandomStringUtils.randomNumeric(10);
		return randomNum;
	}

	public String randomAlphaNumeric() {
		String randomPass = RandomStringUtils.randomAlphanumeric(8);
		return randomPass;
	}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}
}
