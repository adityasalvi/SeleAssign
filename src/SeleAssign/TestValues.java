package SeleAssign;

import PageFactory.ObjFactory;
import junit.framework.Assert;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


public class TestValues {
			
		private static WebDriver driver;	
		private static ObjFactory objFactory;
		private static Actions builder;
		
		@Test				
		public void AddFirstProduct() {	
					
			objFactory.searchBox.sendKeys("Suede Kohler");
			
			objFactory.searchButton.click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			String url = objFactory.getProductURLOne.getAttribute("href");
					
			Action mouseOverHome = builder
	                .moveToElement(objFactory.getProductURLOne)
	                .build();
			mouseOverHome.perform();
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			
			objFactory.addTOCart();
							
			objFactory.searchBox.sendKeys("Cashmere Kohler k-6626");
			
			objFactory.searchButton.click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			String url1 = objFactory.getProductURLTwo.getAttribute("href");
			Action mouseOverHome1 = builder
	                .moveToElement(objFactory.getProductURLTwo)
	                .build();
			mouseOverHome1.perform();
			driver.get(url1);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			objFactory.addTOCart();
			
			objFactory.searchBox.sendKeys("Kohler K-5180-ST");
			objFactory.searchButton.click();
			
			objFactory.addQuantity.clear();
			objFactory.addQuantity.sendKeys("2");
			objFactory.addQuantity.click();
			
			objFactory.addTOCart.click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			
			objFactory.checkoutButton.click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			objFactory.guestcheckoutButton.click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
									
			objFactory.shippingFirstName.sendKeys("Aditya");
			objFactory.shippingLastName.sendKeys("Aditya");
			objFactory.shippingAddress.sendKeys("Cooper St");
			objFactory.shippingPostalcode.sendKeys("76013");
			objFactory.shippingCity.sendKeys("Arlington");
								
			Select dropdown1 = new Select(objFactory.shippingState);
			dropdown1.selectByVisibleText("Texas");
			
			Select dropdown2 = new Select(objFactory.shippingCountryID);
			dropdown2.selectByVisibleText("United States");
			
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
						
			objFactory.shippingPhoneNumber.sendKeys("408");
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			
			objFactory.shippingPhoneNumber.sendKeys("628");
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			
			objFactory.shippingPhoneNumber.sendKeys("2097");
			
			objFactory.emailAddress.sendKeys("salviaditya1986@gmail.com");
			
			objFactory.creditCardNumber.sendKeys("4111111111111111");
			
			Select dropdown3 = new Select(objFactory.creditCardMonth);
			dropdown3.selectByVisibleText("12");
			
			Select dropdown4 = new Select(objFactory.creditCardYear);
			dropdown4.selectByVisibleText("2021");
			
			objFactory.creditCardName.sendKeys("Aditya Salvi");
			
			objFactory.creditCardCVV2.sendKeys("123");
			
			objFactory.creditCardValue.click();
			
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			
			String prod1 = (objFactory.productValue1).getText().replaceAll("[$,]", "");		
			String prod2 = (objFactory.productValue2).getText().replaceAll("[$,]", "");
			String prod3 = (objFactory.productValue3).getText().replaceAll("[$,]", "");
							
			String taxAmount = objFactory.taxAmount.getText();
			String grandTotal = (objFactory.grandTotalAmount.getText()).replaceAll("[$,]", "");
							
			float prodTotal = Float.parseFloat(prod1) + Float.parseFloat(prod2) + Float.parseFloat(prod3);
			Assert.assertEquals(Float.parseFloat(grandTotal), prodTotal, 0.0f);
			Assert.assertEquals("$0.00", taxAmount);
		}
		
		@BeforeClass
		public static void beforeTest() {	
			
			System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver_win32\\chromedriver.exe");
		    driver = new ChromeDriver();  
		    driver.get("https://www.build.com"); 
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			objFactory = new ObjFactory(driver);
			builder = new Actions(driver);
			
		}		
		/*@AfterClass
		public static void afterTest() {
			driver.quit();			
		}*/		
		
}
