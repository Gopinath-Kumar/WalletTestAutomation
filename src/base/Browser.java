package base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.io.FileInputStream;

/**
 * @author      Gopinath Kumar <gopi.techz@gmail.com>
 * @version     1.0          (current version number of program)
 * @since      15/10/2019   (the version of the package this class was first added to)
 * @About This is base class which contains all the helper methods for creating test cases
 */

public class Browser {
    public static WebDriver driver = null;
    public static Properties CONFIG = null;
    public static Properties OR =null;

    public Browser(){
        if(driver==null){
            CONFIG= new Properties();
            OR = new Properties();
            try{
                FileInputStream fs = new FileInputStream("C:\\Users\\Arun\\IdeaProjects\\WalletTestAutomation\\src\\config\\Config.properties");
                CONFIG.load(fs);
                fs = new FileInputStream("C:\\Users\\Arun\\IdeaProjects\\WalletTestAutomation\\src\\config\\OR.properties");
                OR.load(fs);
            }catch(Exception e){
                return;
            }

            System.out.println(CONFIG.getProperty("browser"));
            if(CONFIG.getProperty("browser").equalsIgnoreCase("Chrome")){
                Map<String, Object> prefs = new HashMap<String, Object>();
                prefs.put("profile.default_content_setting_values.notifications", 2);
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", prefs);
                System.setProperty("webdriver.chrome.driver", "D:\\GOPINATH\\chromedriver.exe");
                 driver = new ChromeDriver(options);
                 driver.manage().window().maximize();
            }
            else if(CONFIG.getProperty("browser").equalsIgnoreCase("IE")){
                driver =  new InternetExplorerDriver();
            }
            else if(CONFIG.getProperty("browser").equalsIgnoreCase("Mozilla")){
                driver = new FirefoxDriver();
            }
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
    }
    public void click(String xpathKey){
        try{
            driver.findElement(By.xpath(OR.getProperty(xpathKey))).click();
        }catch(Exception e){
            System.out.println("error");
            e.printStackTrace();
        }
    }
    public void input(String xpathKey, String text){
        try{
            driver.findElement(By.xpath(OR.getProperty(xpathKey))).sendKeys(text);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void updateStatus(String xpathKey, String status){
        try{
            driver.findElement(By.xpath(OR.getProperty(xpathKey))).click();
            driver.findElement(By.xpath(OR.getProperty(xpathKey))).sendKeys(status);
        }catch(Exception e){
            System.out.println("Updating Status error");
            e.printStackTrace();
        }
    }

    public void postStatus(String xpathKey){
        try{
            driver.findElement(By.xpath(OR.getProperty(xpathKey))).click();
        }catch(Exception e){
            System.out.println("Posting Status error");
            e.printStackTrace();
        }
    }

    public boolean isElementPresent(String xpathKey){
        try{
            driver.findElement(By.xpath(OR.getProperty(xpathKey)));
        }catch(Exception e){
            return false;
        }

        return true;
    }

    public void selectStar(String xpathKey, String xpathKeys){
        try{
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,600)","");
            Actions action = new Actions(driver);
            WebElement star = driver.findElement(By.cssSelector(OR.getProperty(xpathKey)));
            Action mouseHover = action.moveToElement(star).build();
            Thread.sleep(3000);
            String bgColor = star.getCssValue("fill");
            System.out.println("After hover: " + bgColor);
            Assert.assertTrue(bgColor.equals("rgb(74, 224, 225)"), "Your color in the star in Green");
            mouseHover.perform();
            driver.findElement(By.xpath(OR.getProperty(xpathKeys))).click();
        }catch(Exception e){
            System.out.println("Rating Star exception error");
            e.printStackTrace();
        }
    }

    public void selectDropDown(String xpathKey, String css, String xpathVal){
        try{
           WebElement dropDown = driver.findElement(By.xpath(OR.getProperty(xpathKey)));
           dropDown.click();
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty(css))));
            driver.findElement(By.xpath(OR.getProperty(xpathVal))).click();
            Thread.sleep(2000);
        }catch(Exception e){
            System.out.println("Drop Down Selection error");
            e.printStackTrace();
        }
    }

    public void reviewText(String xpathKey, String text){
        try{
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(xpathKey))));
            WebElement reviewArea = driver.findElement(By.xpath(OR.getProperty(xpathKey)));
            reviewArea.sendKeys(text);
            Thread.sleep(2000);
        }catch(Exception e){
            System.out.println("Review Text error");
            e.printStackTrace();
        }
    }

    public void submitReview(String xpathKey){
        try{
            driver.findElement(By.xpath(OR.getProperty(xpathKey))).click();
        }catch(Exception e){
            System.out.println("Submit Button error");
            e.printStackTrace();
        }
    }

    public void continueReview(String xpathKey, String url){
        try{
            String Title = driver.getTitle()  ;
            if(Title.contains("Test Insurance Company Reviews:")){
                driver.navigate().to(url);
                Thread.sleep(5000);
                driver.findElement(By.xpath(OR.getProperty(xpathKey))).click();
            }
        }catch(Exception e){
            System.out.println("Review Submission error");
            e.printStackTrace();
        }
    }
    public void validateReview(String xpathKey, String expectedText){
        try{
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,600)","");
           WebElement Status_text = driver.findElement(By.xpath(OR.getProperty(xpathKey)));
           String actualText = Status_text.getText();
           Assert.assertTrue(actualText.equals(expectedText),"Your Message Matches!");
        }catch(Exception e){
            System.out.println("Validating Review error");
            e.printStackTrace();
        }
    }
    public void quitBrowser() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }
}
