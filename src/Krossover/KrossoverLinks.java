package Krossover;
	import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


	import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.hamcrest.core.Is;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.security.UserAndPassword;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import sun.security.krb5.internal.crypto.Aes128;


	public class KrossoverLinks {

		public static void main(String[] args) throws IOException, InterruptedException {
		String exePath = "Chromedriver_path/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", exePath);
		//open chrome browser
		WebDriver driver = new ChromeDriver();
		ArrayList<String> al1 = new ArrayList<String>();
		int j=0;
		driver.get("https://www.krossover.com/basketball/");
		driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); 
			//Find total No of links on page and print In console. 
			List<WebElement> total_links = driver.findElements(By.tagName("a")); 
			System.out.println("Total Number of links found on page = " + total_links.size()); 		
			//for loop to open all links one by one to check response code. boolean isValid = false; 
			for (int i = 0; i < total_links.size(); i++)
			{ 
				String url = total_links.get(i).getAttribute("href"); 
			boolean isValid;
			if (url != null) 
			 //Call getResponseCode function for each URL to check response code.
			{
				isValid = getResponseCode(url); 
			
			if (isValid) 
			{ 
				String vald = "Valid Link:" + url;
				al1.add(vald);
			}
			else 
			{ 
				String broken = "Broken Link ------> " + url;
				al1.add(broken);
			}
			}
			for (String strs : al1)
			{
				if(strs.contains("Broken"))
						{
							j++;
						}
			}
			if (j!=0)
			{
				System.out.println("Test Fail");
			}
			else
			{
				System.out.println("All test cases are passed successfully");
			}
}
		driver.close();
	}
		public static boolean getResponseCode(String chkurl) 
		{ 
			boolean validResponse = false; 
		try { 
			//Get response code of URL
			HttpResponse urlresp = new DefaultHttpClient().execute(new HttpGet(chkurl)); 
			int resp_Code = urlresp.getStatusLine().getStatusCode();
			System.out.println("Response Code Is : "+resp_Code); 
			if (resp_Code == 404)
			{
				validResponse = false;
				} 
			else 
			{
				validResponse = true; 
				}
			} 
		catch (Exception e)
		{ } 
		return validResponse; } 
	}