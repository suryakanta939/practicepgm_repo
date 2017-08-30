package Com.Practice;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class NewTest {
	String range1="//span[text()='$16.00 - ";
	String range2="']";
  @Test
  public void f() throws InterruptedException
  {
	  WebDriver driver=new FirefoxDriver();
	  driver.get("http://automationpractice.com/index.php?id_category=11&controller=category#/");
	  driver.manage().window().maximize();

	  Actions act=new Actions(driver);
	  
 String availaleRange=driver.findElement(By.xpath("//span[text()='$16.00 - $32.00']")).getText();
	  
	  System.out.println("the available range is "+availaleRange);
	  System.out.println("Enter ur range");
	  Scanner ran=new Scanner(System.in);
	  String range=ran.next();
	  WebElement bar=driver.findElement(By.xpath("//div[@id='layered_price_slider']"));
	  
	  int width=bar.getSize().getWidth();
	  WebElement slide=driver.findElement(By.xpath("//div[@id='layered_price_slider']/a[2]"));
	  System.out.println("The width of the slider is "+width);
	  
	 // driver.findElement(By.xpath(range1+range+range2));
	  
				  for(int i=0;i<width;i++){
				  act.dragAndDropBy(slide,-i+1,0).build().perform();
				  WebDriverWait wait=new WebDriverWait(driver,10);
				  wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@id='layered_price_range']"))));
				  String msg=driver.findElement(By.xpath("//span[@id='layered_price_range']")).getText();
				  if(msg.contains(range)){
					  
					  break;
				  }
				  
				  }
	  
	  
		  
  }
  
}
	
  
	 
	  
  

