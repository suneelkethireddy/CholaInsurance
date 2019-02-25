package HomePage;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ElementsInHomePage {

	WebDriver driver = null;

	@Parameters({"browser"})
	@Test
	public void launchBrowser(String browser) {

		if (browser.equalsIgnoreCase("Chrome")) {
			File file = new File("F:/chromedriver.exe");
			System.setProperty("webdriver.chrome.driver",
					file.getAbsolutePath());
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("Firefox")) {
			driver = new HtmlUnitDriver();
		} else if (browser.equalsIgnoreCase("safari")) {
			driver = new HtmlUnitDriver();
		}
	}

	@Test(dependsOnMethods={"launchBrowser"})
	public void launchWebSite() {

		driver.get("https://www.cholainsurance.com/");

	}

	@Test(dependsOnMethods={"launchWebSite"})
	public void checkForAllObjects() {
		try {
		//	driver.findElement(By.id("ucTopHeader_hplHeaderImage"));
			String getWindowHandle=driver.getWindowHandle();
			System.out.println("the latest is:"+getWindowHandle);
			Set<String> handles=driver.getWindowHandles();
			
			Iterator<String> itr = handles.iterator();
			System.out.println("Traversing through the set");
			while (itr.hasNext()) {
	            System.out.println(itr.next());
	        }

			String number1 = driver.findElement(By.className("tool-phone"))
					.getText();
			assertEquals(number1, "1800-200-5544 (Accessible from India only)");
			ArrayList<String> tabs = new ArrayList<String>();
			tabs.add("Health Insurance");
			tabs.add("Car Insurance");
			tabs.add("Travel Insurance");
			tabs.add("Home Insurance");
			tabs.add("Personal Accident");
			tabs.add("Crop Insurance");
			String array[] = new String[tabs.size()];
			for (int j = 0; j < tabs.size(); j++) {
				array[j] = tabs.get(j);
			}
			// System.out.println(array[]);
			for (int i = 0; i < 6; i++) {
				String actualValue = driver
						.findElement(
								By.xpath("/html/body/form/div[3]/div[1]/div[3]/div[1]/div[1]/ul/li["
										+ (i + 1) + "]/a")).getText();
				assertEquals(array[i], actualValue);
				System.out.println(actualValue);
			}
			/**
			 * String healthInsu=driver.findElement(By.xpath(
			 * "/html/body/form/div[3]/div[1]/div[3]/div[1]/div[1]/ul/li[1]/a"
			 * )).getText(); System.out.println( healthInsu);
			 * assertEquals(healthInsu, "Health Insurance"); String
			 * carInsu=driver.findElement(By.xpath(
			 * "/html/body/form/div[3]/div[1]/div[3]/div[1]/div[1]/ul/li[2]/a"
			 * )).getText(); System.out.println( carInsu); assertEquals(carInsu,
			 * "Car Insurance"); String travInsu=driver.findElement(By.xpath(
			 * "/html/body/form/div[3]/div[1]/div[3]/div[1]/div[1]/ul/li[3]/a"
			 * )).getText(); System.out.println( travInsu);
			 * assertEquals(travInsu, "Travel Insurance"); String
			 * homeInsu=driver.findElement(By.xpath(
			 * "/html/body/form/div[3]/div[1]/div[3]/div[1]/div[1]/ul/li[4]/a"
			 * )).getText(); System.out.println( homeInsu);
			 * assertEquals(homeInsu, "Home Insurance"); String
			 * perInsu=driver.findElement(By.xpath(
			 * "/html/body/form/div[3]/div[1]/div[3]/div[1]/div[1]/ul/li[5]/a"
			 * )).getText(); System.out.println( perInsu); assertEquals(perInsu,
			 * "Personal Accident"); String
			 * cropInsu=driver.findElement(By.xpath(
			 * "/html/body/form/div[3]/div[1]/div[3]/div[1]/div[1]/ul/li[6]/a"
			 * )).getText(); System.out.println(cropInsu);
			 * assertEquals(cropInsu, "Crop Insurance");
			 */

			String inputName = driver
					.findElement(
							By.xpath("/html/body/form/div[3]/div[1]/div[4]/div[1]/div[2]/div/div[2]/div/ul/li[1]/label"))
					.getText();
			System.out.println(inputName);
			assertEquals("Name *", inputName);
		

			WebElement ele = driver.findElement(By.id("Name"));
			System.out.println(ele);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
