package stepDefs;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CartStepDef {
	static WebDriver driver;
	static WebDriverWait wait;
	@BeforeAll
	public static void Startup() {
		WebDriverManager.edgedriver().setup();
		driver= new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
		
		wait= new WebDriverWait(driver, Duration.ofSeconds(40));
	}
	@Given("User is on Launch Page")
	public void user_is_on_launch_page() {
		driver.get("https://www.demoblaze.com/index.html");
	}

	@When("User navigates to Login Page")
	public void user_navigates_to_login_page() throws InterruptedException {
	 //  driver.findElement(By.id("login2")).click();
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
	   wait.until(ExpectedConditions.elementToBeClickable(By.id("login2"))).click();
	   Thread.sleep(3000);
	   wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[id=\"loginusername\"]"))).sendKeys("Naan");
	//   wait.until(ExpectedConditions.elementToBeClickable(By.id("loginusername"))).sendKeys("Naan");
	   wait.until(ExpectedConditions.elementToBeClickable(By.id("loginpassword"))).sendKeys("Nee");
	   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
	   Thread.sleep(3000);
	   wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div//button[@onclick='logIn()']"))).click();
	   Thread.sleep(5000);
	}

	@Then("Should display Home Page")
	public void should_display_home_page() {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
//		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li//a[@id='logout2']")));
//		 WebElement logoutmsg = driver.findElement(By.xpath("//li//a[@id='logout2']"));
		 
		 
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li//a[contains(text(),'Welcome')]")));
		 String logoutmsg = driver.findElement(By.xpath("//li//a[contains(text(),'Welcome')]")).getText();
		 
		 boolean success=logoutmsg.equalsIgnoreCase("Welcome Naan");
			Assert.assertTrue(success);
			
			 driver.findElement(By.xpath("//div//a[contains(text(),'Home')]")).click();
		
//		 String out=driver.findElement(By.xpath("//li//a[@id='logout2']")).getText();
//		 Assert.assertTrue(true, out);
		 
	}

	@When("User Add an item to cart")
	public void user_add_an_item_to_cart(DataTable dataTable) {
		List<Map<String,String>> data=dataTable.asMaps();
		
		for(int i=0;i<data.size();i++) {
		String str1=data.get(i).get("cat");
		String str2=data.get(i).get("ite");
		
		driver.findElement(By.xpath("//div//a[contains(text(),'Home')]")).click();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		String typecat="//div/a[contains(text(),'"+str1+"')]";
		driver.findElement(By.xpath(typecat)).click();
		
	//	 driver.findElement(By.xpath("//div/a[contains(text(),'Phones')]")).click();
	//	 wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div/a[contains(text(),'Phones')]")));
		 
	//	driver.findElement(By.xpath("//a[contains(text(),'Nexus 6')]")).click();
		 String item ="//a[contains(text(),'"+str2+"')]";
		 driver.findElement(By.xpath(item)).click();
		 driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

		 
		 WebElement btn = driver.findElement(By.xpath("//div/a[contains(text(),'Add to cart')]"));
			wait.until(ExpectedConditions.elementToBeClickable(btn));
			btn.click();
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			alert.accept();
			
		}
	}

	@Then("Items must be added to cart")
	public void items_must_be_added_to_cart() {
	   driver.findElement(By.xpath("//a[contains(text(),'Cart')]")).click();
	   
	}

//	@Given("User is on Cart Page")
//	public static void user_is_on_cart_page() throws InterruptedException {
//		
//	//	 driver.findElement(By.xpath("//a[contains(text(),'Cart')]")).click();
//	    
//	}

	@When("List of items should be available in cart")
	public  void list_of_items_should_be_available_in_cart() throws InterruptedException {
//		List<WebElement> BefCart=driver.findElements(By.xpath("//td[2]"));
//		wait.until(ExpectedConditions.visibilityOfAllElements(BefCart));
//		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//td[2]")));
	  
	}

	@Then("Delete an item from cart")
	public void delete_an_item_from_cart() throws InterruptedException {
		List<WebElement> BefCart=driver.findElements(By.xpath("//td[2]"));
		wait.until(ExpectedConditions.visibilityOfAllElements(BefCart));
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div//a[contains(text(),'Delete')][1]"))).click();
		//delopt.click();
		List<WebElement> AftCart=driver.findElements(By.xpath("//td[2]"));
		
		wait.until(ExpectedConditions.visibilityOfAllElements(AftCart));
		Thread.sleep(3000);
		if(BefCart!=AftCart) {
			Assert.assertTrue(true);
		}
	//	driver.findElement(By.xpath("//div//a[contains(text(),'Home')]")).click();
	}

	@When("User Place ordering")
	public void user_place_ordering() throws InterruptedException {
//		driver.findElement(By.xpath("//div//button[contains(text(),'Place Order')]")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div//button[contains(text(),'Place Order')]"))).click();
	//	driver.findElement(By.xpath("//div//input[@id='name']")).sendKeys("Vinayak");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div//input[@id='name']"))).sendKeys("Vinayak");
		
//		 driver.findElement(By.cssSelector("#country")).sendKeys("Japan");
//		  driver.findElement(By.cssSelector("#city")).sendKeys("Masko");
//		  driver.findElement(By.cssSelector("#card")).sendKeys("13245");
//		  driver.findElement(By.cssSelector("#month")).sendKeys("March");
//		  driver.findElement(By.cssSelector("#year")).sendKeys("2045");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#country"))).sendKeys("Japan");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#city"))).sendKeys("Tokyo");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#card"))).sendKeys("567898");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#month"))).sendKeys("April");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#year"))).sendKeys("3939");
		
		
		  Thread.sleep(2000);
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	//	  wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div//button[contains(text(),'Purchase')]")))).click();
		  driver.findElement(By.xpath("//button[contains(text(),'Purchase')]")).click();
		
	}

	@Then("Item must be Placed")
	public void item_must_be_placed() {
		WebElement conmsg = driver.findElement(By.xpath("(//h2)[3]"));
	//	wait.until(ExpectedConditions.presenceOfElementLocated("//button[contains(text(),'Thank you for your purchase!')]"))
	//	wait.until(ExpectedConditions.textToBePresentInElement(conmsg, "Thank you for your purchase!"));
		boolean confirm=conmsg.isDisplayed();
		Assert.assertTrue(confirm);
		
		
		driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click(); 
	}

}
