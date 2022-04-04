package ga.mmbh.cfgs.seleniummaven;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumMaven {
	
	private static ChromeDriver driver;
	
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		try {
			startWikipediaTest();
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
		
		Thread.sleep(3000);
	}
	
	private static void startOdooTest() throws InterruptedException {
		driver.get("https://www.odoo.com/es_ES");
		
		WebElement tryButtonElement = driver.findElement(By.cssSelector("#top_menu > div > a.btn.btn-primary"));
		tryButtonElement.click();
		
		Thread.sleep(1000);
		
		WebElement crmButton = driver.findElement(By.cssSelector("#wrapwrap > main > div > div.subscription-widget-container.bg-white > div > div.row.choose-app-step.o_trial_step_1 > div.choose-app-list.col-12.mt24.o_animate_in_children.o_visible.offset-lg-1.col-lg-10 > div:nth-child(2) > label:nth-child(1) > div"));
		crmButton.click();
		
		Thread.sleep(1000);
		
		WebElement continueButton = driver.findElement(By.cssSelector("#wrapwrap > main > div > div.subscription-widget-container.bg-white > div > div.row.choose-app-step.o_trial_step_1 > div.choose-app-info-panel.col-lg-3.col-12.mt32 > div > div > button"));
		continueButton.click();
		
		Thread.sleep(1000);
		
		WebElement usernameInput = driver.findElement(By.cssSelector("#username"));
		usernameInput.sendKeys("Mohamed");
		
		WebElement emailInput = driver.findElement(By.cssSelector("#email"));
		emailInput.sendKeys("mohamed@gmail.com");
		
		WebElement companyInput = driver.findElement(By.cssSelector("#company-name"));
		companyInput.sendKeys("Mohamed S.L");
		
		WebElement phoneInput = driver.findElement(By.cssSelector("#phone"));
		phoneInput.sendKeys("12345678");
		
		WebElement countrySelect = driver.findElement(By.cssSelector("#country-id"));
		countrySelect.sendKeys("EspaÃ±a");
		
		WebElement languageSelect = driver.findElement(By.cssSelector("#wrapwrap > main > div > div.subscription-widget-container.bg-white > div > div.fill-info-step.o_trial_step_2 > div > div.col-lg-7.mt48.o_animate_in_children.o_visible > div > div.col-12.mb-4.mb-md-5 > form > div:nth-child(6) > div:nth-child(2) > div > select"));
		languageSelect.sendKeys("EspaÃ±ol");
		
		WebElement companySizeSelect = driver.findElement(By.cssSelector("#wrapwrap > main > div > div.subscription-widget-container.bg-white > div > div.fill-info-step.o_trial_step_2 > div > div.col-lg-7.mt48.o_animate_in_children.o_visible > div > div.col-12.mb-4.mb-md-5 > form > div:nth-child(7) > div:nth-child(1) > div > select"));
		Select select = new Select(companySizeSelect);
		select.selectByIndex(1);
		
		WebElement planSelect = driver.findElement(By.cssSelector("#plan"));
		select = new Select(planSelect);
		select.selectByIndex(1);
		
		Thread.sleep(1000);
		
		WebElement submitButton = driver.findElement(By.cssSelector("#wrapwrap > main > div > div.subscription-widget-container.bg-white > div > div.fill-info-step.o_trial_step_2 > div > div.col-lg-7.mt48.o_animate_in_children.o_visible > div > div.col-12.mb-4.mb-md-5 > form > div.row.mt24 > div.col-auto.text-right > button"));
		submitButton.click();
		
		Thread.sleep(6000);
	}
	
	
	
}
