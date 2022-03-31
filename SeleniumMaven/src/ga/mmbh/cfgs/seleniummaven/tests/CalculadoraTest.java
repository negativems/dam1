package ga.mmbh.cfgs.seleniummaven.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CalculadoraTest {

	private static ChromeDriver driver;
	private static WebElement firstNumberElement, secondNumberElement, operationElement,
							  calculateButtonElement, integersOnlyElement, clearButtonElement,
							  answerElement;
	private static Select operationSelect;

	@BeforeAll
	public static void initialize() {
		WebDriverManager.chromedriver().setup();

		try {
			driver = new ChromeDriver();
			driver.get("https://testsheepnz.github.io/BasicCalculator.html");
			
			Thread.sleep(900);
			firstNumberElement = driver.findElement(By.id("number1Field"));
			secondNumberElement = driver.findElement(By.id("number2Field"));
			calculateButtonElement = driver.findElement(By.id("calculateButton"));
			integersOnlyElement = driver.findElement(By.id("integerSelect"));
			clearButtonElement = driver.findElement(By.id("clearButton"));
			answerElement = driver.findElement(By.id("numberAnswerField"));
			
			operationElement = driver.findElement(By.id("selectOperationDropdown"));
			operationSelect = new Select(operationElement);
			
			Thread.sleep(500);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Order(1)
	public void testSuma() {
		System.out.println("Test suma");
		try {
			Thread.sleep(500);
			firstNumberElement.sendKeys("1");
			Thread.sleep(500);
			secondNumberElement.sendKeys("1");
			Thread.sleep(500);
			calculateButtonElement.click();
			
			Thread.sleep(500);
			assertEquals(answerElement.getAttribute("value"), "2");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(2)
	public void testResta() {
		System.out.println("Test resta");
		try {
			Thread.sleep(500);
			operationSelect.selectByIndex(1);
			
			clearNumbers();
			
			firstNumberElement.sendKeys("1");
			Thread.sleep(500);
			secondNumberElement.sendKeys("1");
			Thread.sleep(500);
			calculateButtonElement.click();
			
			Thread.sleep(500);
			assertEquals(answerElement.getAttribute("value"), "0");
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(2)
	public void testMultiplicacion() {
		System.out.println("Test multiplición");
		try {
			Thread.sleep(500);
			operationSelect.selectByIndex(2);
			
			clearNumbers();
			
			firstNumberElement.sendKeys("1");
			Thread.sleep(500);
			secondNumberElement.sendKeys("1");
			Thread.sleep(500);
			calculateButtonElement.click();
			
			Thread.sleep(500);
			assertEquals(answerElement.getAttribute("value"), "1");
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(3)
	public void testDivision() {
		System.out.println("Test division");
		try {
			Thread.sleep(500);
			operationSelect.selectByIndex(3);
			
			clearNumbers();
			
			firstNumberElement.sendKeys("1");
			Thread.sleep(500);
			secondNumberElement.sendKeys("1");
			Thread.sleep(500);
			calculateButtonElement.click();
			
			Thread.sleep(500);
			assertEquals(answerElement.getAttribute("value"), "1");
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(4)
	public void testConcatenar() {
		System.out.println("Test concatenar");
		try {
			Thread.sleep(500);
			operationSelect.selectByIndex(4);
			
			clearNumbers();
			
			firstNumberElement.sendKeys("1");
			Thread.sleep(500);
			secondNumberElement.sendKeys("1");
			Thread.sleep(500);
			calculateButtonElement.click();
			
			Thread.sleep(500);
			assertEquals(answerElement.getAttribute("value"), "11");
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void clearNumbers() {
		firstNumberElement.click();
		firstNumberElement.clear();
		secondNumberElement.click();
		secondNumberElement.clear();
	}
	
	@AfterAll()
	public static void afterAll() {
		driver.close();
	}
}
