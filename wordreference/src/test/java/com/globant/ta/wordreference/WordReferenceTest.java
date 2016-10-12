package com.globant.ta.wordreference;

import java.util.List;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class WordReferenceTest {
	
	private WebDriver driver;
	private final String URL = "http://www.wordreference.com/es/";
	
	@BeforeSuite
	public void init(){
		driver = new ChromeDriver();
		driver.get(URL);
	}
	
	@DataProvider(name = "languagesProvider")
	public Object[][] languagesProvider() {
		return new Object[][] { {"enes"}, {"esen"}, {"esfr"}, {"espt"} };
	}
	
	@Test
	public void selectLanguageByLink(){
		List<WebElement> links = driver.findElements(By.cssSelector(".links a"));
		
		boolean isFoundEnglish = false;
		
		WebElement englishLink = links.stream()
				.filter(l -> l.getText().contains("English")).findFirst()
				.orElse(null);
		if(englishLink != null){
			englishLink.click();
			isFoundEnglish = true;
		}
		
		Assert.assertTrue(isFoundEnglish);
	}
	
	@Test(dataProvider="languagesProvider")
	public void selectLanguageByCombo(String value){
		
		Select select = new Select(driver.findElement(By.id("fSelect")));
		select.selectByValue(value);
	}
	
	@Test
	public void translateWord(){
		Select select = new Select(driver.findElement(By.id("fSelect")));
		select.selectByValue("enes");
		
		WebElement boxTranslate = driver.findElement(By.id("si"));
		
		boxTranslate.sendKeys("Test");
		
		driver.findElement(By.className("submit-button")).click();
		
		Assert.assertNotNull(driver.findElement(By.className("WRD")));
	}
	
	@AfterSuite
	public void close(){
		this.driver.quit();
	}

}
