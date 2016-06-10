package Project;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class findBrokenLinks {
	public static WebDriver driver;
	public static int invalidImageCount;
	public static int invalidLinkCount;

	public static void findBrokenImageLinks(WebDriver driver) throws InterruptedException{


		System.out.println("Find Broken Links Code");

		List<WebElement> allLinks = driver.findElements(By.tagName("a"));
		List<WebElement> allImages = driver.findElements(By.tagName("img"));

		Thread.sleep(2000);


		//Checking Broken Images Right Now
		System.out.println("Checking the Broken Images Now...");
		try {
			invalidImageCount = 0;

			System.out.println("Total no. of images are = " + allImages.size());


			for(int i=0;i<allImages.size();i++)
			{		
				if (allImages.get(i) != null) {
					verifyImageActive(allImages.get(i));
				}
			}
			System.out.println("Total no. of invalid images are = "	+ invalidImageCount);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}


		//Checking Broken Links Now
		System.out.println("Checking the Broken Links Now...");
		try {
			invalidImageCount = 0;

			System.out.println("Total no. of Links are = " + allLinks.size());
			for(int j=0;j<allLinks.size();j++)
			{

				if (allLinks.get(j)!= null || allLinks.get(j).getText() != "") {
					String url = (allLinks.get(j).getAttribute("href"));

					boolean urlValue = url.contains("javascript");
					if(urlValue)
					{						
						j++;
					}

					else
					{
						System.out.println(allLinks.get(j).getText());
						verifyLinksActive(allLinks.get(j));
						//System.out.println("URL: " + allLinks.get(j).getAttribute("href")+ " returned " + isLinkBroken(new URL(allLinks.get(j).getAttribute("href"))));

					}

				}
			}
			System.out.println("Total no. of invalid Links are = "	+ invalidLinkCount);
			
/*			//Taking Screenshot Code
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String path = System.getProperty("user.dir")+ "\\screenshots";
			System.out.println(path);
			long dateNow = new Date().getTime();
			System.out.println(dateNow);
			//The below method will save the screen shot in d drive with name "screenshot.png"
			FileUtils.copyFile(scrFile, new File(path+"\\screenshot_"+dateNow+".png"));
*/


		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			Assert.fail("Broken Links Code Failure");
		}



	}


	public static void verifyImageActive(WebElement link) {
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet( link.getAttribute("src"));
			HttpResponse response = client.execute(request);
			// verifying response code he HttpStatus should be 200 if not,
			// increment as invalid images count
			if (response.getStatusLine().getStatusCode() != 200)
			{
				//Taking Screenshot Code
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				String path = System.getProperty("user.dir")+ "\\screenshots";
				
				long dateNow = new Date().getTime();
				
				//The below method will save the screen shot in d drive with name "screenshot.png"
				FileUtils.copyFile(scrFile, new File(path+"\\screenshot_"+dateNow+".png"));

				invalidImageCount++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void verifyLinksActive(WebElement links) {
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(links.getAttribute("href"));
			HttpResponse response = client.execute(request);
			// verifying response code he HttpStatus should be 200 if not,
			// increment as invalid images count
			if (response.getStatusLine().getStatusCode() != 200)
			{
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				String path = System.getProperty("user.dir")+ "\\screenshots";
				
				long dateNow = new Date().getTime();
				
				//The below method will save the screen shot in d drive with name "screenshot.png"
				FileUtils.copyFile(scrFile, new File(path+"\\screenshot_"+dateNow+".png"));
				invalidLinkCount++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String isLinkBroken(URL url) throws Exception

	{

		//url = new URL("http://yahoo.com");

		String response = "";

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		try

		{

			connection.connect();

			response = connection.getResponseMessage();	        

			connection.disconnect();

			return response;

		}

		catch(Exception exp)

		{

			return exp.getMessage();


		}
	}  				

}
