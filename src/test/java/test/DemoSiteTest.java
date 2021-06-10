package test;

import static org.junit.Assert.assertEquals;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import pages.AddUser;
import pages.HomePage;
import pages.Login;

public class DemoSiteTest {

	static WebDriver driver;

	@BeforeClass
	public static void init() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().fullscreen();
	}

	@Before
	public void setup() throws TimeoutException {
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.get(HomePage.getUrl());
	}

	@AfterClass
	public static void teardown() {
		driver.quit();
	}

	@Test
	public void test() throws InterruptedException {
		final String userName = "tigist";
		final String passWord = "test";

		HomePage nav = PageFactory.initElements(driver, HomePage.class);

		AddUser use = PageFactory.initElements(driver, AddUser.class);

		Login log = PageFactory.initElements(driver, Login.class);

		nav.navAddAUser();
		Thread.sleep(5000);

		use.uName(userName, passWord);
		Thread.sleep(5000);

		nav.navLogin();
		Thread.sleep(5000);

		log.login(userName, passWord);
		Thread.sleep(5000);

		assertEquals("**Successful Login**",
				driver.findElement(By.xpath("//b[contains(text(), 'Successful')]")).getText());
		Thread.sleep(5000);
	}

}
