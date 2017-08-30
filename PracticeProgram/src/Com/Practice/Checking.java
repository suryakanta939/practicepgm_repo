package Com.Practice;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;


public class Checking {
	WebDriver driver;
	String baseUrl="http://automationpractice.com/index.php";
	String xpathcolor1="html/body/div[1]/div[2]/div/div[3]/div[1]/div[1]/div[1]/form/div/div[3]/ul/li[";
	String xpathcolor2="]/input";
	String size1check="html/body/div[1]/div[2]/div/div[3]/div[1]/div[1]/div[1]/form/div/div[1]/ul/li[";
	String size2check="]/div/span/input";
	String range1="//span[text()='$16.00 - ";
	String range2="']";
	
//	String key="webdriver.gecko.driver";
	
//	String value="D:\\SELENIUM WEBDRIVER SOFTWARE\\geckodriver\\geckodriver.exe";
	
	
	
@BeforeClass

 public void beforeClass() {
	//System.setProperty(key, value);
	
	driver=new FirefoxDriver();
	
		
	}
  
  @BeforeMethod
  public void beforeMethod() {
	  driver.get(baseUrl);
	  driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }

  @Test
  public void f() throws IOException, InterruptedException {
    WebElement women=driver.findElement(By.xpath("//a[@title='Women']"));
	  Actions act=new Actions(driver);
	  act.moveToElement(women).perform();
	  WebElement dress=driver.findElement(By.xpath("//ul[@class='submenu-container clearfix first-in-line-xs']/li[2]/ul/li[3]/a[text()='Summer Dresses']"));
	  dress.click();
	  
	  /*click on the size*/
	  
	  System.out.println("enter ur size small as 1,medium as 2 & large as 3");
	  Thread.sleep(3000);
	  Scanner scn=new Scanner(System.in);
	  int no=scn.nextInt();
	  driver.findElement(By.xpath(size1check+no+size2check)).click();
	  
	//  Thread.sleep(2000);
	  
	  /*check the size is checked or not*/
	  boolean msg=driver.findElement(By.xpath(size1check+no+size2check)).isSelected();
	  
	  System.out.println(msg);
	  
	  List<WebElement> colors=driver.findElements(By.xpath("html/body/div[1]/div[2]/div/div[3]/div[1]/div[1]/div[1]/form/div/div[3]/ul/li/input"));
	  System.out.println("total no of color availale "+colors.size());
	  //select the color
	  System.out.println("enter ur color from 1 to "+colors.size());
	  
	  Scanner scn1=new Scanner(System.in);
	  int no1=scn1.nextInt();
	  driver.findElement(By.xpath(xpathcolor1+no1+xpathcolor2)).click();
	  Thread.sleep(3000);
	  
//	 WebDriverWait wait=new WebDriverWait(driver,5);
//	 wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@id='enabled_filters']"))));
	  if(msg==true){
		  
		  WebElement image=driver.findElement(By.xpath("//div[@id='enabled_filters']"));
		  Thread.sleep(1000);
		  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", image);
		  
		  captureCropscreenshot(image);
	  }
	  
    //select the price range
	  
	  String availableRange=driver.findElement(By.xpath("//span[text()='$16.00 - $32.00']")).getText();
	  
	  System.out.println("the available range is "+availableRange);
	  System.out.println("Enter ur range");
	  Scanner ran=new Scanner(System.in);
	  String range=ran.next();
	  WebElement bar=driver.findElement(By.xpath("//div[@id='layered_price_slider']"));
	  int width=bar.getSize().getWidth();
	  WebElement slide=driver.findElement(By.xpath("//div[@id='layered_price_slider']/a[2]"));
	  System.out.println("The width of the slider is "+width);
	  for(int i=0;i<width;i++){
		  act.dragAndDropBy(slide,-i+1,0).build().perform();
		  WebDriverWait wait=new WebDriverWait(driver,10);
		  wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@id='layered_price_range']"))));
		  String msg1=driver.findElement(By.xpath("//span[@id='layered_price_range']")).getText();
		  if(msg1.contains(range)){
			  
			  break;
			  
		  }
		  
		  }
  
	  Thread.sleep(3000);
	  
    WebDriverWait wait=new WebDriverWait(driver,10);
    wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("html/body/div[1]/div[2]/div/div[3]/div[2]/ul/li/div/div[1]/div/a[1]/img"))));
	  
	  //select one dress and add it to cart
	  List<WebElement> elements=driver.findElements(By.xpath("html/body/div[1]/div[2]/div/div[3]/div[2]/ul/li/div/div[1]/div/a[1]/img"));
	  
	  
	  System.out.println("enter ur selected item no as the total item is "+elements.size());
	  Scanner itemno=new Scanner(System.in);
	  int nos=itemno.nextInt();
	   
	  for(int i=0;i<elements.size();i++){
		  
		  Actions act1=new Actions(driver);
		  
		  act1.moveToElement(elements.get(i)).perform();
		  
		    //click on the add to cart of the selected no
		  
		  if(nos==i+1){
				   List<WebElement> cart=driver.findElements(By.xpath("//span[text()='Add to cart']"));
				   cart.get(i).click();
				   
			   }
	  } 
		   
		  //read the price and add to cart the selected price
	
	  //check out
	  
	  driver.findElement(By.xpath("//a[@title='Proceed to checkout']")).click();
	  
	   Thread.sleep(3000);
	   //driver.findElement(By.xpath("//span[@title='Close window']")).click();
	   
	   driver.findElement(By.xpath("//input[@id='pwd']")).sendKeys("welkfgmo");
	   
  }
  
  @AfterMethod
  public void afterMethod() {
	  
  }

  

  @AfterClass
  public void afterClass() {
	//  driver.close();
  }
  
 public void captureCropscreenshot(WebElement element) throws IOException{
	  
	 java.io.File screen=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

	 /*get the height and width of the image*/
	 int height=element.getSize().getHeight();
	 
	 int width=element.getSize().getWidth();
	 
	 /*get the xchord and ychord*/
	 
	 Point point=element.getLocation();
	 
	 int xChord=point.getX();
	 
	 int yChord=point.getY();
	
	 //READ FULL IMAGE SCREENSHOT
	 BufferedImage img=ImageIO.read(screen);
	 
	 //cut Image using height, width and x y coordinates parameter
	 
	 BufferedImage dest = img.getSubimage(xChord, yChord, width, height);
	 
	  ImageIO.write(dest, "png", screen);
	  
	//Used FileUtils class of apache.commons.io.
	  
	  //save Image screenshot In D: drive.
	  
	  FileUtils.copyFile(screen, new File("E:\\SELENIUM_PROGRAM\\PracticeProgram\\Image\\crop.png"));
	  
	  
	  
 }
  

}
