package pageobjects.login;

import base.Browser;
import org.testng.Assert;
import pageobjects.inbox.LandingPage;

/**
 * @author      Gopinath Kumar <gopi.techz@gmail.com>
 * @version     1.0          (current version number of program)
 */

public class FBLoginPage extends Browser {
    public LandingPage FBLoginPages(String userName,String password){

        try{
            Assert.assertEquals("XXXWelcome to Facebook � Log in, sign up or learn more", driver.getTitle());
        }catch(Throwable t){

        }
        driver.get(CONFIG.getProperty("testSiteLadingPageURL"));
        System.out.println(isElementPresent("//*[@id='email']"));
        input("EMAIL",userName);
        input("PASSWORD",password);
        click("LOGIN_BUTTON");
        if(isElementPresent("create_post"))
            return new LandingPage();
        else
            return null;
    }

}
