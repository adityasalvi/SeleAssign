package SeleAssign;

import PageFactory.ObjFactory;
import static org.assertj.core.api.Assertions.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 
 * @author Aditya Salvi
 * @description Build.com Selenium Assignment
 * NOTE: - To handle the java script validation, i have added a small code which will
 * 		   quit the execution and will let you enter the CAPTCHA.
 * 		   Once the validation is passed please restart the script.
 */
public class TestValues {
			
		private static WebDriver driver;	
		private static ObjFactory objFactory;
		private static Actions builder;
		private WebDriverWait wait = new WebDriverWait(driver, 10);	
		private static String chromeDriverPath = "./chromedriver.exe";
		
		/**
		 * TEST CASE to validate the Total Tax Amount for California and Grand Total for the products purchased.
		 * @throws Exception
		 */
		@Test				
		public void validateTotalTaxAmt() throws Exception {	
									
			wait.until(ExpectedConditions.elementToBeClickable(objFactory.searchBox));//Wait until Home Page Is loaded
				
			addFirstProduct("Suede Kohler K66266U");//Add First Product to cart with Quantity 1
			
			addSecondProduct("Cashmere Kohler K66266U");//Add Second Product to cart with Quantity 1
			
			addThirdProduct("Kohler K-5180-ST","2");//Add Third Product to cart with Quantity 2
			
			objFactory.checkoutButton.click();//Checkout The Cart
			wait.until(ExpectedConditions.elementToBeClickable(objFactory.guestcheckoutButton));
			
			objFactory.guestcheckoutButton.click();//Do a Guest Checkout
			wait.until(ExpectedConditions.elementToBeClickable(objFactory.shippingFirstName));
			
			enterPaymentInfo("Aditya","Salvi","Cooper St","95192", "San Jose", "California", 
					"United States", "4086282097","aditya@gmail.com","4111111111111111",
					"12", "2021", "Aditya Salvi", "123");//Enter The Payment Info
			
			try{
				String prod1 		= (objFactory.productValue1).getText().replaceAll("[$, ]", ""); //Get First Product Price		
				String prod2 		= (objFactory.productValue2).getText().replaceAll("[$, ]", ""); //Get Second Product Price	
				String prod3 		= (objFactory.productValue3).getText().replaceAll("[$, ]", ""); //Get Third Product Price								
				String taxAmount 	= objFactory.taxAmount.getText().replaceAll("[$, ]", ""); //Get the Calculated Tax Amount
				
				String grandTotal 	= (objFactory.grandTotalAmount.getText()).replaceAll("[$,]", "");//Get the Grand Total

				double prod1TaxVal 	= calTax(Double.parseDouble(prod1));
				double prod2TaxVal 	= calTax(Double.parseDouble(prod2));
				double prod3TaxVal 	= calTax(Double.parseDouble(prod3));				
				double prodTotalTax = prod1TaxVal + prod2TaxVal + prod3TaxVal;								
				double prodTotal 	= Double.parseDouble(prod1) + Double.parseDouble(prod2) + 
									  Double.parseDouble(prod3) + prodTotalTax;
				
				assertThat(Double.parseDouble(grandTotal)).isEqualTo(prodTotal); //Assert Grand Total 
				
				assertThat(Double.parseDouble(taxAmount)).isEqualTo(prodTotalTax); //Assert Tax Value
				
			}catch(Exception e){
	    		System.out.println("Register element is not found.");
	    		throw(e);
	    	}
			
		}

		/**
		 * Initialize the Chrome Driver and Launch Web Page Before Test
		 */
		@BeforeClass
		public static void beforeTest() {	
			
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		    driver = new ChromeDriver();
		    driver.get("https://www.build.com"); 
			driver.manage().window().maximize();
			
			Boolean temp = driver
						   .findElements(By.xpath("//*[@id='dCF_input_complete']"))
						   .size() > 0; //To Handle the Javascript Security Error			
			if(temp)
				System.exit(1);
			
			objFactory = new ObjFactory(driver); //Initializing Object Factory
			builder = new Actions(driver); //Initializing Actions driver
			
		}
		
		/**
		 * Exit After Test
		 */
		@AfterClass
		public static void afterTest() {
			driver.quit();			
		}		
		
		/**
		 * Enter Product 1 to the cart
		 * @param product1
		 * @throws Exception
		 */
		public void addFirstProduct(String product1)throws Exception{
	    	
	    	try{
	    		
	    		objFactory.searchBox.sendKeys(product1);				
				objFactory.searchButton.click();
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Kohler K-6626-6U-20')]")));
				
				builder
		        .moveToElement(objFactory.addTOCart)
		        .build().perform();
								
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[contains(@src,'/k-6626-6usuede.jpg') and @alt='Suede']")));
				builder
		        .moveToElement(driver.findElement(By.xpath("//img[contains(@src,'/k-6626-6usuede.jpg') and @alt='Suede']")))
		        .build().perform();				

				for(int i=0;i<5;i++)
				{					
					WebElement temp1 = driver.findElement(By.xpath("//img[contains(@src,'/k-6626-6usuede.jpg') and @alt='Suede']"));							
					temp1.click();
					if(driver.findElements(By.xpath("//*[contains(@class,'1868060 selected')]")).size() > 0)
					{
						addTOCart();
						break;
					}
							
				}

	    	}catch(Exception e){
	    		System.out.println("Register element is not found.");
	    		throw(e);
	    	}
	    	
	    }
		
		/**
		 * Add second product to the cart
		 * @param product2
		 * @throws Exception
		 */
		public void addSecondProduct(String product2)throws Exception{
	    	
	    	try{
	    		objFactory.searchBox.sendKeys(product2);				
				objFactory.searchButton.click();
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Kohler K-6626-6U-20')]")));
				
	    		builder
		        .moveToElement(objFactory.addTOCart)
		        .build().perform();

				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[contains(@src,'/K-6626-6U-ca.jpg') and @alt='Cashmere']")));
				builder
		        .moveToElement(driver.findElement(By.xpath("//img[contains(@src,'/K-6626-6U-ca.jpg') and @alt='Cashmere']")))
		        .build().perform();				

				for(int i=0;i<5;i++)
				{					
					WebElement temp1 = driver.findElement(By.xpath("//img[contains(@src,'/K-6626-6U-ca.jpg') and @alt='Cashmere']"));							
					temp1.click();
					if(driver.findElements(By.xpath("//*[contains(@class,'173133 selected')]")).size() > 0)
					{
						addTOCart();
						break;
					}

				}
	    		
	    	}catch(Exception e){
	    		System.out.println("Register element is not found.");
	    		throw(e);
	    	}
	    	
	    }
		
		/**
		 * Add Product 3 to the cart
		 * @param product3
		 * @param quantity
		 * @throws Exception
		 */
		public void addThirdProduct(String product3, String quantity)throws Exception{
	    	
	    	try{	    		
		    		objFactory.searchBox.sendKeys(product3);
					objFactory.searchButton.click();				
					objFactory.addQuantity.clear();
					objFactory.addQuantity.sendKeys(quantity);
					objFactory.addQuantity.click();
					for(int i = 0; i < 5; i++)
					{
						Boolean temp = driver.findElements(By.xpath("//*[@id='cartNav']/a/span")).size() > 0;	
						if (temp)
							break;
						else
						{	
							Action mouseOverHome1 = builder
					                .moveToElement(objFactory.addTOCart)
					                .build();
							mouseOverHome1.perform();
							objFactory.addTOCart.click();
							wait.until(ExpectedConditions.elementToBeClickable(objFactory.checkoutButton));						
						}
					}
					    		
		    	}catch(Exception e){
		    		System.out.println("Register element is not found.");
		    		throw(e);
		    	}
	    	
	    }
		
		/**
		 * Function to enter Payment Details
		 * @param fName
		 * @param lName
		 * @param address
		 * @param postalCode
		 * @param city
		 * @param state
		 * @param countryID
		 * @param phoneNo
		 * @param email
		 * @param ccNumber
		 * @param ccMonth
		 * @param ccYear
		 * @param ccName
		 * @param cvvNo
		 * @throws Exception
		 */
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
										
					String firstThreeDigits = phoneNo.substring(0, 3);
					String secondThreeDigits = phoneNo.substring(3, 6);
					String lastfourDigits = phoneNo.substring(6, 10);
	
					objFactory.shippingPhoneNumber.sendKeys(firstThreeDigits);
					
					objFactory.shippingPhoneNumber.sendKeys(secondThreeDigits);
					
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
		
		/**
		 * 
		 * @throws Exception
		 */
		public void addTOCart() throws Exception{
	    	
	    	try{
	    		for(int i = 0; i < 5; i++)
				{
					Boolean temp = driver.findElements(By.xpath("//a[contains(@href,'page=cart:cart') and @data-loading-text='Proceeding to Cart...']")).size() > 0;	
					if (temp)
						break;
					else
					{	
						Action mouseOverHome1 = builder
				                .moveToElement(objFactory.addTOCart)
				                .build();
						mouseOverHome1.perform();
						objFactory.addTOCart.click();
						wait.until(ExpectedConditions.elementToBeClickable(objFactory.proceedTOCart));
						
					}
		    		
				}
	    	}catch(Exception e){
	    		System.out.println("Register element is not found.");
	    		throw(e);
	    	}
						
	    }
		
		/**
		 * Calculate Tax Value
		 * @param taxValue
		 * @return
		 */
		public double calTax(double taxValue){
			
			double tempTaxValue = taxValue*(7.25d/100.00d);
			double roundOffTaxValue = Math.round(tempTaxValue * 100.00d) / 100.00d;
			return roundOffTaxValue;
		}
		
}
