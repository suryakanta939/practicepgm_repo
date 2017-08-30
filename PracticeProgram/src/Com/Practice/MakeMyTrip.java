package Com.Practice;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class MakeMyTrip {
	WebDriver driver;
	String BaseUrl="https://www.yatra.com/";
	//html/body/div[14]/ul/div[1]/div
  @Test
  public void f() throws InterruptedException 
  {
	  driver=new FirefoxDriver();
	  driver.get(BaseUrl);
	  driver.manage().window().maximize();
	  //click on the origin
	  driver.findElement(By.xpath("//input[@id='BE_flight_origin_city']")).click();
	  //raed all the data
	  List<WebElement> elements=driver.findElements(By.xpath("html/body/div[14]/ul/div[2]/div/li"));
	  int size=elements.size();
	  System.out.println("The total no of elements "+size);
	  WebElement scroll=driver.findElement(By.xpath("html/body/div[14]/ul/div[1]"));
	  System.out.println("the height of the scroll bar is "+scroll.getSize().getHeight());
	  WebElement bar=driver.findElement(By.xpath("html/body/div[14]/ul/div[1]/div"));
	  System.out.println("the height of the bar is "+bar.getSize().getHeight());
	  Actions act=new Actions(driver);
	  for(int i=0;i<size;i++){
		  String msg=elements.get(i).getText();
		  System.out.println(msg);
		  act.dragAndDropBy(bar, 0, 18).perform();
		  Thread.sleep(1000);
	  }
	  
	  //html/body/div[14]/ul/div[2]/div/li[1]/div[1]/p
  }
}
