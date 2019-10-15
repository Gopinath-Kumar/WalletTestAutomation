package pageobjects.inbox;

import base.Browser;

/**
 * @author      Gopinath Kumar <gopi.techz@gmail.com>
 * @version     1.0          (current version number of program)
 * @About  This class contains all the implementation for the corresponding methods created in the base class
 */

public class LandingPage extends Browser {

    public void update(String status) {
        updateStatus("place_holder", status);
        postStatus("post_button");
    }

    public void selectAndHover(){
        driver.get(CONFIG.getProperty("testInsurancePage"));
        selectStar("Star_No4","Star_4");
    }

    public void chooseDropDownOption(){
        selectDropDown("drop_down", "css_text", "Drop_Down_Text");
    }

    public void writeReview(String reviewText){
        reviewText("Write_review", reviewText);
    }

    public void submit(){
        submitReview("Submit_Button");
    }

    public void reviewProfile(String profileUrl){
        continueReview("review_Comment", profileUrl);
    }

    public void validateReviewText(String Review_text){
        validateReview("Profile_review",Review_text);
    }

    public void closeBrowser() throws InterruptedException {
        quitBrowser();
    }
}
