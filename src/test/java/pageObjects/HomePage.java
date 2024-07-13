package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

     @FindBy(xpath = "//span[normalize-space()='My Account']")
     WebElement InkMyAccount;
     @FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a [text()='Register']")
     WebElement InkRegister;
     @FindBy(linkText = "Login")
     WebElement InkLogin;

    public void clickAccount(){
        InkMyAccount.click();
    }
    public void clickRegister(){
        InkRegister.click();
    }
    public void clickLogin(){
        InkLogin.click();
    }
}
