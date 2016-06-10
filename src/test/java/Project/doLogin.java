package Project;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class doLogin {
	
	public File file;
	Properties prop;
	public InputStream fileInput;
	
	
	public void loginSite(WebDriver driver) throws InterruptedException
	{
		System.out.println("Logging into the site");
		
		driver.findElement(By.linkText("Sign In")).click();
		//findBrokenLinks.findBrokenImageLinks(driver.getCurrentUrl());
		
		
	}

	
	
	public void LoginInDetails(WebDriver driver) throws InterruptedException
	{
		
		InputStream input = getClass().getClassLoader().getResourceAsStream("details.properties");
		prop = new Properties();
		try {
			prop.load(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		driver.findElement(By.id("emailId")).sendKeys(prop.getProperty("loginID"));
		Thread.sleep(1000);
		try{
			driver.findElement(By.xpath("//input[@class='btn']")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("password")).sendKeys(prop.getProperty("password"));
			driver.findElement(By.xpath("//input[@class='btn']")).click();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	
	public void mySite() throws InterruptedException
	{
		System.out.println("Skipping this my Site method");
		//findBrokenLinks.findBrokenImageLinks(driver.getCurrentUrl());
	}


	
}
