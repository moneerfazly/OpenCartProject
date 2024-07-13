package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{

    public AccountRegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@name='firstname']")
    WebElement txtFirstName;
    @FindBy(xpath = "//input[@name='lastname']")
    WebElement txtLastName;
    @FindBy(xpath = "//input[@name='email']")
    WebElement txtEmail;
    @FindBy(xpath = "//input[@name='telephone']")
    WebElement txtTelephone;
    @FindBy(xpath = "//input[@name='password']")
    WebElement txtPassword;
    @FindBy(xpath = "//input[@name='confirm']")
    WebElement txtConfirmPwd;
    @FindBy(xpath = "//input[@name='agree']")
    WebElement chkdPolicy;
    @FindBy(xpath = "//input[@type='submit']")
    WebElement btnSubmit;
    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement txtGetMessage;

    public void setFirstName(String name){
        txtFirstName.sendKeys(name);
    }
    public void setLastName(String lastName){
        txtLastName.sendKeys(lastName);
    }
    public void setEmail(String email){
        txtEmail.sendKeys(email);
    }
    public void setTelephone(String telephone){
        txtTelephone.sendKeys(telephone);
    }
    public void setPwd(String password){
        txtPassword.sendKeys(password);
    }
    public void setConfirmedPwd(String confirmedPwd){
        txtConfirmPwd.sendKeys(confirmedPwd);
    }
    public void clckPolicy(){
        chkdPolicy.click();
    }
    public void clckBtn(){
        btnSubmit.click();
        //btnSubmit.submit();
        //WebDriverWait myWait=new WebDriverWait(driver, Duration.ofSeconds(10));
        //myWait.until(ExpectedConditions.elementToBeClickable(btnSubmit)).click();
    }
    public String getMessage(){

        try{
            return (txtGetMessage.getText());
        }catch (Exception e){
            return (e.getMessage());
        }

    }











}
