package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//input[@id='input-email']")
    WebElement userEmail;
    @FindBy(xpath = "//input[@id='input-password']")
    WebElement userPassword;
    @FindBy(xpath = "//input[@value='Login']")
    WebElement clckLogin;

    public void setUserEmail(String usrEmail){
        userEmail.sendKeys(usrEmail);
    }
    public void setUserPassword(String ursPassword){
        userPassword.sendKeys(ursPassword);
    }
    public void setClckLogin(){
        clckLogin.click();
    }

}
