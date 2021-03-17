package DemoExcercise;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;

public class NewTest {
 
	WebDriver driver;
	PageClass pg;
	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Sarath Nithyananthan\\Downloads\\chromedriver_win32 (2)\\chromedriver.exe");
		driver = new ChromeDriver();
		pg = new PageClass(driver);
		driver.get("https://todomvc.com/examples/angularjs/#/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
 //Add to-do is working. (Verify if the item is added to the list )
  @Test
  public void Test1() throws InterruptedException {
	  
	  pg.add("Drink water twice");
  }
  
  //Marking an item as complete works. (Verify if the item is crossed out and verify the counter on the bottom-left)
  @Test
  public void Test2() throws InterruptedException {
	  
	  pg.complete();
  }
  
  //Delete a to-do (Verify if the item is removed from the list)
  @Test
  public void Test3() throws InterruptedException {
	  
	  pg.delete("Brush your teeth twice","Excercise regularly");
  }
  
  //Filtering a to-do is working (Click on the Active button and verify if it shows the Active items. Click on Completed and verify if it shows the completed items)
  @Test
  public void Test4() throws InterruptedException {
	  
	  pg.active();
  }
  
  @AfterTest
	public void cleanUp() {
		driver.quit();
	}
}
