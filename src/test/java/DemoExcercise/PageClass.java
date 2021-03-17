package DemoExcercise;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import org.testng.Assert;

public class PageClass {
	WebDriver driver;
	JavascriptExecutor js;
	
    @FindBy(xpath="//input[@placeholder='What needs to be done?']")
    WebElement addtodo;
    
    @FindBy(xpath="//input[@placeholder='What needs to be done?']")
    WebElement entertodo;
    
    @FindBy(xpath="//input[@ng-model='todo.completed']")
    WebElement markcomplete;
    
    @FindBy(xpath="//label[text()='Brush your teeth twice']//following-sibling::button")
    WebElement delete;
    
    @FindBy(xpath="//a[text()='Active']")
    WebElement active;
    
    @FindBy(xpath="//a[text()='Completed']")
    WebElement completed;
	
    public PageClass(WebDriver driver)
    {
   	 this.driver = driver;
   	 PageFactory.initElements(driver, this);
    }
    
    @Test
    public void add(String todo) throws InterruptedException
    {
   	 addtodo.sendKeys(todo);
   	 entertodo.sendKeys(Keys.ENTER);
   	 Thread.sleep(2000);
   	 String expected = "Drink water twice";
   	 String actual = driver.findElement(By.xpath("//label[@class='ng-binding']")).getText();
   	 Assert.assertEquals(actual, expected);
   	 System.out.println("Item is successfully added to the list");
    }
    
    @Test
    public void complete() throws InterruptedException
    {
     markcomplete.click();
     Thread.sleep(2000);
   	 Assert.assertTrue(driver.findElement(By.xpath("//li[@class='ng-scope completed']")).isDisplayed());
   	 System.out.println("Item is crossed out after marking as completed");
     String expected = "0";
     String actual = driver.findElement(By.xpath("//strong[@class='ng-binding']")).getText();
     Assert.assertEquals(actual, expected);
     System.out.println("counter is decremented to 0 from 1 sucessfully after marking item as completed");
    }
    
    @Test
    public void delete(String todo1, String todo2) throws InterruptedException
    {
     addtodo.sendKeys(todo1);
     entertodo.sendKeys(Keys.ENTER);	
     addtodo.sendKeys(todo2);
     entertodo.sendKeys(Keys.ENTER);
     System.out.println("successfully added two items");
     Thread.sleep(2000);
     js = (JavascriptExecutor)driver;
     js.executeScript("arguments[0].click();", delete);
     Thread.sleep(2000);
   	 String expected = "1";
     String actual = driver.findElement(By.xpath("//strong[@class='ng-binding']")).getText();
     Assert.assertEquals(actual, expected);
     System.out.println("Item is deleted successfully and counter is decremented to 1");
    }

    @Test
    public void active() throws InterruptedException
    {
     active.click();
     Assert.assertTrue(driver.findElement(By.xpath("//li[@class='ng-scope']")).isDisplayed());
     System.out.println("Active items showed successfully");
   	 Thread.sleep(2000);
   	 completed.click();
   	 Assert.assertTrue(driver.findElement(By.xpath("//li[@class='ng-scope completed']")).isDisplayed());
   	System.out.println("Completed items showed successfully");
  	 Thread.sleep(2000);
    }
}
