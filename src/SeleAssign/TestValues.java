package SeleAssign;

import PageFactory.ObjFactory;
import static org.assertj.core.api.Assertions.*;
import java.util.concurrent.TimeUnit;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TestValues {
			
		private static WebDriver driver;	
		private static ObjFactory objFactory;
		private static Actions builder;
		private WebDriverWait wait = new WebDriverWait(driver, 10);	
		private static String chromeDriverPath = "C:\\Users\\salvi\\workspace\\BuildSeleAssign\\chromedriver.exe";
		
		@Test				
		public void checkForTotalAmt() throws Exception {	
						
			/* ******* Wait until Home Page Is loaded ******* */			
			wait.until(ExpectedConditions.elementToBeClickable(objFactory.searchBox));
			
			/* ******* Add First Product to cart with Quantity 1 ******* */			
			addFirstProduct("Suede Kohler");
			addTOCart();
			
			/* ******* Add Second Product to cart with Quantity 1 ******* */
			addSecondProduct("Cashmere Kohler k-6626");
			addTOCart();
			
			/* ******* Add Third Product to cart with Quantity 2 ******* */
			addThirdProduct("Kohler K-5180-ST","2");
			wait.until(ExpectedConditions.elementToBeClickable(objFactory.checkoutButton));
			
			/* ******* Checkout The Cart ******* */
			objFactory.checkoutButton.click();
			wait.until(ExpectedConditions.elementToBeClickable(objFactory.guestcheckoutButton));
			
			/* ******* Do a Guest Checkout ******* */
			objFactory.guestcheckoutButton.click();
			wait.until(ExpectedConditions.elementToBeClickable(objFactory.shippingFirstName));
			
			/* ******* Enter The Payment Info ******* */
			enterPaymentInfo("Aditya","Salvi","Cooper St","95192", "San Jose", "California", "United States", "4086282097",
							"aditya@gmail.com","4111111111111111","12", "2021", "Aditya Salvi", "123");
			
			/* ******* Assert the Tax Value and Grand Total ******* */
			try{
				String prod1 = (objFactory.productValue1).getText().replaceAll("[$, ]", "");		
				String prod2 = (objFactory.productValue2).getText().replaceAll("[$, ]", "");
				String prod3 = (objFactory.productValue3).getText().replaceAll("[$, ]", "");							
				String taxAmount = objFactory.taxAmount.getText().replaceAll("[$, ]", "");
				
				String grandTotal = (objFactory.grandTotalAmount.getText()).replaceAll("[$,]", "");

				double prod1TaxVal = calTax(Double.parseDouble(prod1));
				double prod2TaxVal = calTax(Double.parseDouble(prod2));
				double prod3TaxVal = calTax(Double.parseDouble(prod3));
				
				double prodTotalTax = prod1TaxVal + prod2TaxVal + prod3TaxVal;
								
				double prodTotal = Double.parseDouble(prod1) + Double.parseDouble(prod2) + Double.parseDouble(prod3) + prodTotalTax;
								
				assertThat(Double.parseDouble(grandTotal)).isEqualTo(prodTotal);
				assertThat(Double.parseDouble(taxAmount)).isEqualTo(prodTotalTax);
				
			}catch(Exception e){
	    		System.out.println("Register element is not found.");
	    		throw(e);
	    	}
			
		}
		
		@BeforeClass
		public static void beforeTest() {	
			
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		    driver = new ChromeDriver();
		    driver.get("https://www.build.com"); 
			driver.manage().window().maximize();
			
			/* To Handle the Javascript Security Error  */
			Boolean temp = driver.findElements(By.xpath("//*[@id='dCF_input_complete']")).size() > 0;			
			if(temp)
				System.exit(1);
			
			/* Initializing the Objects */
			objFactory = new ObjFactory(driver);
			builder = new Actions(driver);
			
		}
		
		@AfterClass
		public static void afterTest() {
			driver.quit();			
		}		
		
		public void addFirstProduct(String product1)throws Exception{
	    	
	    	try{
	    		objFactory.searchBox.sendKeys(product1);
				
				objFactory.searchButton.click();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
				String url = objFactory.getProductURLOne.getAttribute("href");
						
				Action mouseOverHome = builder
		                .moveToElement(objFactory.getProductURLOne)
		                .build();
				mouseOverHome.perform();
				driver.get(url);
				wait.until(ExpectedConditions.elementToBeClickable(objFactory.searchBox));
	    		
	    	}catch(Exception e){
	    		System.out.println("Register element is not found.");
	    		throw(e);
	    	}
	    	
	    }
		
		public void addSecondProduct(String product2)throws Exception{
	    	
	    	try{
	    		objFactory.searchBox.sendKeys(product2);
				
				objFactory.searchButton.click();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
				String url1 = objFactory.getProductURLTwo.getAttribute("href");
				Action mouseOverHome1 = builder
		                .moveToElement(objFactory.getProductURLTwo)
		                .build();
				mouseOverHome1.perform();
				driver.get(url1);
				wait.until(ExpectedConditions.elementToBeClickable(objFactory.searchBox));
	    		
	    	}catch(Exception e){
	    		System.out.println("Register element is not found.");
	    		throw(e);
	    	}
	    	
	    }
		
		public void addThirdProduct(String product3, String quantity)throws Exception{
	    	
	    	try{
	    		objFactory.searchBox.sendKeys(product3);
				objFactory.searchButton.click();
				
				objFactory.addQuantity.clear();
				objFactory.addQuantity.sendKeys(quantity);
				objFactory.addQuantity.click();
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
				objFactory.addTOCart.click();
					    		
	    	}catch(Exception e){
	    		System.out.println("Register element is not found.");
	    		throw(e);
	    	}
	    	
	    }
		
		public void enterPaymentInfo(String fName, String lName, String address, String postalCode, String city,
				String state, String countryID, String phoneNo,String email, String ccNumber, String ccMonth, String ccYear,
				String ccName, String cvvNo
				)throws Exception{
			
			
	    	try{
	    		objFactory.shippingFirstName.sendKeys(fName);
				objFactory.shippingLastName.sendKeys(lName);
				objFactory.shippingAddress.sendKeys(address);
				objFactory.shippingPostalcode.sendKeys(postalCode);
				objFactory.shippingCity.sendKeys(city);
									
				Select dropdown1 = new Select(objFactory.shippingState);
				dropdown1.selectByVisibleText(state);
				
				Select dropdown2 = new Select(objFactory.shippingCountryID);
				dropdown2.selectByVisibleText(countryID);
				
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				
				String firstThreeDigits = phoneNo.substring(0, 3);
				String secondThreeDigits = phoneNo.substring(3, 6);
				String lastfourDigits = phoneNo.substring(6, 10);

				objFactory.shippingPhoneNumber.sendKeys(firstThreeDigits);
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				
				objFactory.shippingPhoneNumber.sendKeys(secondThreeDigits);
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				
				objFactory.shippingPhoneNumber.sendKeys(lastfourDigits);
				
				objFactory.emailAddress.sendKeys(email);
				
				objFactory.creditCardNumber.sendKeys(ccNumber);
				
				Select dropdown3 = new Select(objFactory.creditCardMonth);
				dropdown3.selectByVisibleText(ccMonth);
				
				Select dropdown4 = new Select(objFactory.creditCardYear);
				dropdown4.selectByVisibleText(ccYear);
				
				objFactory.creditCardName.sendKeys(ccName);
				
				objFactory.creditCardCVV2.sendKeys(cvvNo);
				
				objFactory.creditCardValue.click();
	    		
	    	}catch(Exception e){
	    		System.out.println("Register element is not found.");
	    		throw(e);
	    	}
	    	
	    }
		
		public void addTOCart() throws Exception{
	    	
	    	try{
	    		for(int i = 0; i < 5; i++)
				{
					Boolean temp = driver.findElements(By.xpath("//*[@id='page-content']/div[1]/div[1]/div[1]/div[2]/div/a")).size() > 0;	
					if (temp)
						break;
					else
					{	
						objFactory.addTOCart.click();
						wait.until(ExpectedConditions.elementToBeClickable(objFactory.proceedTOCart));
					}
		    		
				}
	    	}catch(Exception e){
	    		System.out.println("Register element is not found.");
	    		throw(e);
	    	}
			
			
	    }
		
		public double calTax(double taxValue){
			
			double tempTaxValue = taxValue*(7.25d/100.00d);
			double roundOffTaxValue = Math.round(tempTaxValue * 100.00d) / 100.00d;
			return roundOffTaxValue;
		}
		
}
