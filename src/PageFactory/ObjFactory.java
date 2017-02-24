package PageFactory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ObjFactory {
	
	/**

     * All WebElements are identified by @FindBy annotation

     */
	
	public WebDriver driver;

    @FindBy(xpath="//*[@id='search_txt']")

    public WebElement searchBox;
    
    @FindBy(xpath="//*[@id='site-search']/div/button")

    public WebElement searchButton;
    
    @FindBy(xpath="//*[@id='searchResults']/li[26]/div/div[2]/a")
    
    public WebElement getProductURLOne;
    
    @FindBy(xpath="//*[@id='page-content']/div[1]/div[1]/div[1]/div[2]/div/a")
    
    public WebElement proceedTOCart;
    
    @FindBy(xpath="//*[@id='add-to-cart-wrap' and @class='add-to-cart-btn']")
    
    public WebElement addTOCart;
    
    @FindBy(xpath="//*[@id='searchResults']/li[1]/div/div[2]/a")
    
    public WebElement getProductURLTwo;
    
    @FindBy(xpath="//*[@id='main-product-quantity']/div/input")
    
    public WebElement addQuantity;
    
    @FindBy(xpath="//*[@id='cartNav']/a/span")
    
    public WebElement checkoutButton;
    
    @FindBy(xpath="//*[@id='guest-login']/button")
    
    public WebElement guestcheckoutButton;
    
    @FindBy(xpath="//*[@id='shippingfirstname']")
    
    public WebElement shippingFirstName;
    
    @FindBy(xpath="//*[@id='shippinglastname']")
    
    public WebElement shippingLastName;
    
    @FindBy(xpath="//*[@id='shippingaddress1']")
    
    public WebElement shippingAddress;

    @FindBy(xpath="//*[@id='shippingpostalcode']")

    public WebElement shippingPostalcode;
    
    @FindBy(xpath="//*[@id='shippingcity']")

    public WebElement shippingCity;
    
    @FindBy(xpath="//*[@id='shippingstate_1']")

    public WebElement shippingState;
    
    @FindBy(xpath="//*[@id='shippingcountryID']")

    public WebElement shippingCountryID;

    @FindBy(xpath="//*[@id='shippingphonenumber']")

    public WebElement shippingPhoneNumber;
    
    @FindBy(xpath="//*[@id='emailAddress']")

    public WebElement emailAddress;
    
    @FindBy(xpath="//*[@id='creditCardNumber']")

    public WebElement creditCardNumber;
    
    @FindBy(xpath="//*[@id='creditCardMonth']")

    public WebElement creditCardMonth;
    
    @FindBy(xpath="//*[@id='creditCardYear']")

    public WebElement creditCardYear;
    
    @FindBy(xpath="//*[@id='creditcardname']")

    public WebElement creditCardName;
    
    @FindBy(xpath="//*[@id='creditCardCVV2']")

    public WebElement creditCardCVV2;
    
    @FindBy(xpath="//*[@id='creditcard']/div[3]/input")

    public WebElement creditCardValue;
    
    @FindBy(xpath="//*[@id='cart']/tbody/tr[1]/td[4]")

    public WebElement productValue1;
    
    @FindBy(xpath="//*[@id='cart']/tbody/tr[2]/td[4]")

    public WebElement productValue2;
    
    @FindBy(xpath="//*[@id='cart']/tbody/tr[3]/td[4]")

    public WebElement productValue3;
    
    @FindBy(xpath="//*[@id='taxAmount']")

    public WebElement taxAmount;
    
    @FindBy(xpath="//*[@id='grandtotalamount']")

    public WebElement grandTotalAmount;
    
    
    
    public ObjFactory(WebDriver driver){

        this.driver = driver;

        //This initElements method will create all WebElements

        PageFactory.initElements(driver, this);

    }

    public void addTOCart(){
    	
    	for(int i = 0; i < 5; i++)
		{
			Boolean temp = driver.findElements(By.xpath("//*[@id='page-content']/div[1]/div[1]/div[1]/div[2]/div/a")).size() > 0;			
			if (temp)
				break;
			else
			{	
				/*builder.moveToElement(obj.addTOCart)))
				.doubleClick().perform();*/
				driver.findElement(By.xpath("//*[@id='add-to-cart-wrap' and @class='add-to-cart-btn']")).click();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			}
		}
    	
    }

}
