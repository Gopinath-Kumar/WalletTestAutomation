package pageobjects.login;

import base.Browser;
import org.testng.Assert;
import pageobjects.inbox.LandingPage;

public class WalletLoginPage extends Browser {
    public LandingPage WalletLoginPages(String useremail, String userpassword){

        try{
            Assert.assertEquals("Login", driver.getTitle());
        }catch(Throwable t){

        }
        driver.get(CONFIG.getProperty("testWalletLadingPageURL"));
        input("USER_EMAIL",useremail);
        input("USER_PASSWORD",userpassword);
        click("Login_Button");
        if(isElementPresent("My_Wallet"))
            return new LandingPage();
        else
            return null;
    }
}
