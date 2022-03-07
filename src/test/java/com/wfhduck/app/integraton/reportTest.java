package com.wfhduck.app.integraton;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class reportTest {
	WebDriver driver;


	@Given("^Open the Chrome and launch the application$")
	public void open_the_chrome_and_launch_the_application() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\HP\\Documents\\WFHDuck\\target\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8080/");
	}


	@And("^Welcome to the index page$")
	public void verify_welcome_page() throws Throwable {
		String actualString = driver.findElement(By.className("heading-section")).getText();
		assertTrue(actualString.contains("Login"));
	}

	@When("^login to interface page$")
	public void enter_the_Username_and_Password() throws Throwable {
		driver.get("http://localhost:8080/index");
	}


	@Then("^user input and login$")
	public void enter_and_submi_form() throws Throwable {
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("admin");
		driver.findElement(By.id("submit")).click();
	}


	@Then("^User will see interface page$")
	public void verify_redurect_to_interface__listpage() throws Throwable {
		String actualString = driver.findElement(By.xpath("/html/body/section/div/div[2]/div/div/h3/p")).getText();
		assertTrue(actualString.contains("Admin"));
		driver.close();
	}
	
	@Then("^User will see interface page and click to report problem$")
	public void verify_redurect_to_interface_report__listpage() throws Throwable {
		String actualString = driver.findElement(By.xpath("//*[@id=\"profile\"]")).getText();
		assertTrue(actualString.contains("VIEW"));
		driver.findElement(By.id("reportProblem")).click();
	}
	
	@Then("^User will see report problem page$")
	public void verify_redurect_to_report_problem__listpage() throws Throwable {
		String actualString = driver.findElement(By.xpath("/html/body/section/div/div[2]/div/div/h3")).getText();
		assertTrue(actualString.contains("Report"));
		driver.findElement(By.id("category")).sendKeys("Camera");
		driver.findElement(By.id("description")).sendKeys("camera broken");
		driver.findElement(By.xpath("//*[@id=\"submit\"]")).click();
	}
	
	@Then("^User will verify report problem page$")
	public void verify_redurect_to_submitted_problem__listpage() throws Throwable {
		String actualString = driver.findElement(By.xpath("/html/body/section/div/div[2]/div/div/h3")).getText();
		assertTrue(actualString.contains("handled"));
		driver.findElement(By.xpath("//*[@id=\"submit\"]")).click();
		driver.close();
	}
}
