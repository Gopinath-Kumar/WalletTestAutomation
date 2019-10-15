package testcases;


import org.testng.annotations.Test;
import pageobjects.inbox.LandingPage;
import pageobjects.login.FBLoginPage;

/**
 * @author      Gopinath Kumar <gopi.techz@gmail.com>
 * @version     1.0          (current version number of program)
 * @since      15/10/2019   (the version of the package this class was first added to)
 * @About This is test case for Scenario 1 (using facebook Login account)
 */

public class FBLoginTest {

    @Test
    public void FBLogin() {
        try {
            FBLoginPage login = new FBLoginPage();
            //Step 1 -  Login to Facebook. Username and Password
            // (for User name & Password and other Locators, Please visit CONFIG.PROPERTIES & OR.PROPERTIES File)
            login.FBLoginPages(FBLoginPage.CONFIG.getProperty("defaultUserName"), FBLoginPage.CONFIG.getProperty("defaultPassword"));
            LandingPage statusUpdate = new LandingPage();

            //Step 2 - Post a status message "Hello World"
            statusUpdate.update(LandingPage.OR.getProperty("profile_status"));
            statusUpdate.closeBrowser();
        }catch(Exception e){
            System.out.println("Test case for FB Login");
            e.printStackTrace();
        }
    }
}
