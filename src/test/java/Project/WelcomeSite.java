package Project;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.testng.annotations.Test;

public class WelcomeSite {

	public static WebDriver driver;
	
	
	
	@Test(priority=0)
	public void launchTPSite() throws InterruptedException
	{
		ProfilesIni profile = new ProfilesIni();
		FirefoxProfile myprofile = profile.getProfile("MyFirefoxProfile");
		driver= new FirefoxDriver(myprofile);
		
		
		driver.get("http://timespoints.com");
		System.out.println(driver.getTitle());

		
		Project.findBrokenLinks.findBrokenImageLinks(driver);
		
		Thread.sleep(2000);
		
		
	}
	
	@Test(priority=1)
	public void goToSignInScreen() throws InterruptedException
	{
		doLogin dol=new doLogin();
		dol.loginSite(driver);
		Thread.sleep(2000);
		Project.findBrokenLinks.findBrokenImageLinks(driver);
		dol.LoginInDetails(driver);
	}
	
}
