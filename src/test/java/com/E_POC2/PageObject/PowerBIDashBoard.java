package com.E_POC2.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.E_POC2.TestBase.TestBase;

public class PowerBIDashBoard extends TestBase {
	
	WebDriver driver;
	
	@FindBy(xpath = "//input[@id='email']")
	WebElement emailId;
	
	@FindBy(xpath = "//button[@id='submitBtn']")
	WebElement emailIdFrameSubmit;
	
	@FindBy(id = "i0118")
	WebElement password;
	
	@FindBy(xpath = "//input[@id='idSIButton9']")
	WebElement passwordAndStaySignInButton;

	
	public PowerBIDashBoard(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);

	}

	public WebElement getEmailId() {
		return emailId;
	}

	public WebElement getEmailIdFrameSubmit() {
		return emailIdFrameSubmit;
	}

	public WebElement getPassword() {
		return password;
	}

	public WebElement getPasswordAndStaySignInButton() {
		return passwordAndStaySignInButton;
	}
		

}
