package Krossover;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CheckFilters2 {
	static WebDriver driver;

	public static void main(String[] args) throws IOException {
		String exePath = "Chromedriver_path/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", exePath);
		//open chrome browser
		driver = new ChromeDriver();
		driver.get("https://v2-pre-prod-app.krossover.com/intelligence/games/9603/breakdown/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		//check for filter plays option
		int count = driver.findElements(By.linkText("Breakdown")).size();
		if (count > 0) {
			driver.findElement(By.linkText("Filter Plays")).click();
		}
		else
		{
		System.out.println("Element is not present on webpage");
		//take screenshot for failed testcase
		takescreenshot();
		}
		}

	private static void takescreenshot() throws IOException {
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//create a folder in project & save screenshot in it
		FileUtils.copyFile(scrFile, new File("./screenshot/screenshot2.png"));
		System.out.println("screen shot saved");		
	}
	}