package testcases;

import base.Browser;
import org.testng.annotations.Test;
import pageobjects.inbox.LandingPage;
import pageobjects.login.WalletLoginPage;

/**
 * @author      Gopinath Kumar <gopi.techz@gmail.com>
 * @version     1.0          (current version number of program)
 * @since      15/10/2019   (the version of the package this class was first added to)
 * @About This is test case for Scenario 2 (using Wallet Hub account)
 */
public class WalletLoginTest {
    @Test
    public void loginWallet() {

        try {
            WalletLoginPage loginHome = new WalletLoginPage();
            //Step 1 - Go to this URL: http://wallethub.com/profile/test_insurance_company/
            // (for User name & Password and other Locators, Please visit CONFIG.PROPERTIES & OR.PROPERTIES File)
            loginHome.WalletLoginPages(WalletLoginPage.CONFIG.getProperty("defaultUserEmail"), WalletLoginPage.CONFIG.getProperty("defaultUserPassword"));
            LandingPage selectStar = new LandingPage();

            //Step 2 - On the reviews section of the page, hover over the stars and click on the fourth star
            selectStar.selectAndHover();

            //Step 3 - click on the Policy dropdow and change the value to “Health Insurance
            selectStar.chooseDropDownOption();

            //Step 4 -  Click on the link “Write a review” and write some random text
            selectStar.writeReview(WalletLoginPage.OR.getProperty("Review_text"));

            //Step 5 - Press submit.
            selectStar.submit();

            //Step 6 & 7 - assertTrue() that you can see the review with the text you entered on the previous page.
            selectStar.reviewProfile(WalletLoginPage.CONFIG.getProperty("profile_url"));
            selectStar.validateReviewText(WalletLoginPage.OR.getProperty("Review_text"));
            selectStar.closeBrowser();
        }catch(Exception e){
            System.out.println("Test case for FB Login");
            e.printStackTrace();
        }
    }
}
