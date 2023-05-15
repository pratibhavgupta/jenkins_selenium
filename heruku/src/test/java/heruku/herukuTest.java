package heruku;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class herukuTest {
	WebDriver driver;
	public String url="https://the-internet.herokuapp.com/";
	
	@BeforeTest
	public void tc1() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@Test
	public void tc2() {
		driver.get(url);
		driver.findElement(By.xpath("//a[@href='/windows']")).click();
		
		
		driver.findElement(By.xpath("//a[contains(text(),'Click Here')]")).click();
		
		String s=driver.findElement(By.cssSelector("h3")).getText();
		
		System.out.println(s);
		
		Set<String> id=driver.getWindowHandles();
		Iterator<String> it=id.iterator();
		String pid=it.next();
		driver.switchTo().window(pid);
		
		System.out.println(driver.findElement(By.xpath("//h3[contains(text(),'Opening a new window')]")).getText());
	}
	
	@Test
	public void tc3() {
		driver.get(url);
		driver.findElement(By.xpath("//a[contains(text(),'Frames')]")).click();
		
		driver.findElement(By.xpath("//a[contains(text(),'Nested Frames')]")).click();
		
		driver.switchTo().defaultContent();
		
		WebElement ele=driver.findElement(By.xpath("//frameset[1]/frame[1]"));
		driver.switchTo().frame(ele);
		

		WebElement ele1=driver.findElement(By.xpath("//frameset[1]/frame[2]"));
		driver.switchTo().frame(ele1);
		
		
		System.out.println(driver.findElement(By.id("content")).getText());
		driver.switchTo().defaultContent();
		
		
	}
	
	@Test
	public void tc4() {
		driver.get(url);
		driver.findElement(By.xpath("//a[contains(text(),'Drag and Drop')]")).click();
		
		Actions ac=new Actions(driver);
		WebElement source=driver.findElement(By.id("column-a"));
		WebElement target=driver.findElement(By.id("column-b"));
		
		ac.dragAndDrop(source, target);
		
	}
	
	@Test
	public void tc5() {
		driver.get(url);
		List<WebElement> lists=driver.findElements(By.tagName("a"));
		System.out.println("No. of anchor tag "+lists.size());
		
		driver.findElement(By.xpath("//a[@href='/hovers']")).click();
		
		SoftAssert soft =new SoftAssert();
		soft.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/hovers");
		soft.assertAll();
		
		WebElement ele=driver.findElement(By.xpath("//h5[contains(text(),'name: user1')]/parent::div/parent::div"));
		Actions ac=new Actions(driver);
		ac.moveToElement(ele).build().perform();
		
		driver.findElement(By.xpath("//a[@href='/users/1']")).click();
		
		System.out.println(driver.findElement(By.tagName("h1")).getText());
	
	}
	
	@AfterTest
	public void tc6() {
		driver.quit();
	}
	
	
	
	
	

}
