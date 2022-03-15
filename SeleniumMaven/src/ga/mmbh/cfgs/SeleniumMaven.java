package ga.mmbh.cfgs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


public class SeleniumMaven {

	private static FirefoxDriver driver;
	
	public static void main(String[] args) {
		
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		
		try {
			// startWikipediaTest();
			startOdooTest();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}

	}
	
	private static void startWikipediaTest() throws InterruptedException {
		driver.get("https://www.wikipedia.com");
		
		WebElement searchElement = driver.findElement(By.id("searchInput"));
		searchElement.sendKeys("java");
		
		WebElement searchButtonElement = driver.findElement(By.cssSelector("button[type=submit]"));
		searchButtonElement.click();
		
		System.out.println(driver.getTitle());
		
		Thread.sleep(10000);
	}
	
	private static void startOdooTest() throws InterruptedException {
		driver.get("https://www.odoo.com/es_ES");
		
		WebElement tryButtonElement = driver.findElement(By.cssSelector("#top_menu > div > a.btn.btn-primary"));
		tryButtonElement.click();
		
		WebElement crmButton = driver.findElement(By.cssSelector("#wrapwrap > main > div > div.subscription-widget-container.bg-white > div > div.row.choose-app-step.o_trial_step_1 > div.choose-app-list.col-12.mt24.o_animate_in_children.o_visible.offset-lg-1.col-lg-10 > div:nth-child(2) > label:nth-child(1) > div"));
		crmButton.click();
		
		System.out.println(driver.getTitle());
		
		Thread.sleep(10000);
	}
	
}
