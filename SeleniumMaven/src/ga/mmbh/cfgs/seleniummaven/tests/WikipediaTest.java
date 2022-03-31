package ga.mmbh.cfgs.seleniummaven.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WikipediaTest {

	@Test
	public void imageTest() {
		WebDriverManager.chromedriver().setup();
		
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://es.wikipedia.org/wiki/Pablo_Picasso");
		
		String selector = "#mw-content-text > div.mw-parser-output > table > tbody > tr:nth-child(2) > td > a > img";
		boolean existsImage = driver.findElements(By.cssSelector(selector)).size() > 0;
		
		assertTrue(existsImage);
	}

	
	@Test
	public void pabloPicassoTest() {
		WebDriverManager.chromedriver().setup();
		
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://es.wikipedia.org/wiki/IES_Pablo_Picasso");
		
		String selector = "#noarticletext > div:nth-child(1)";
		assertTrue(driver.findElement(By.cssSelector(selector)).getText().contains("Wikipedia todavía no tiene una página llamada"));
	}
}
